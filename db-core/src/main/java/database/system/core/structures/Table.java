package database.system.core.structures;

import database.system.core.constraints.ConstraintFactory;
import database.system.core.constraints.Constraint;
import database.system.core.constraints.DefaultConstraint;
import database.system.core.types.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@EqualsAndHashCode(callSuper = true)
@Data
public class Table extends DatabaseStructure {
    private AtomicInteger rowId = new AtomicInteger(0);
    private Set<Integer> rowIds = new TreeSet<>();
    private Map<String, Column> columns = new TreeMap<>();

    public Table(){}

    public Table(Table other) {
        if (!this.equals(other)){
            // Deep copy columns
            for (Map.Entry<String, Column> entry : other.columns.entrySet()) {
                String key = entry.getKey();
                Column originalColumn = entry.getValue();
                Column copiedColumn = new Column(originalColumn); // Assuming Column class has a copy constructor
                this.columns.put(key, copiedColumn);
            }
            // Deep copy constraintSet
            this.rowId = other.rowId;
            this.rowIds = new TreeSet<>(other.rowIds);
        }
    }


    public Column getColumn(String columnName) {
        validateColumnName(columnName, columns);
        return columns.get(columnName);
    }

    public void create(String columnName, Column column) {
        validateNonNull(columnName);
        validateNonNull(column);
        columns.put(columnName, column);
    }

    public void create(String columnName, String columnType) {
        validateNonNull(columnName);
        validateNonNull(columnType);
        if (DataType.validate(columnType)) {
            columns.put(columnName, new Column(DataType.valueOf(columnType)));
        } else {
            throw new IllegalArgumentException(String.format("Error: Invalid data type: %s", columnType));
        }
    }

    public void create(String columnName, String columnType, String[] constraints) {
        validateNonNull(columnName);
        validateNonNull(columnType);
        if (DataType.validate(columnType)) {
            Column column = new Column(DataType.valueOf(columnType));
            for (String constraint : constraints) {
                Constraint constraintObj = ConstraintFactory.createConstraint(column, columnName, constraint);
                column.addConstraint(constraintObj);
            }
            columns.put(columnName, column);
        } else {
            throw new IllegalArgumentException(String.format("Error: Invalid data type: %s", columnType));
        }
    }


    public void dropColumn(String columnName) {
        validateColumnName(columnName, columns);
        columns.remove(columnName);
    }

    public Table insert(List<String> columnNames, Object[] valueArray) {
        for (String columnName : columnNames) {
            validateColumnName(columnName, columns);
        }
        for (Map.Entry<String, Column> entry : columns.entrySet()) {
            String columnName = entry.getKey();
            Column column = entry.getValue();
            int columnIndex = columnNames.indexOf(columnName);
            if (columnIndex != -1) {
                DataType expectedType = column.getColumnScheme().getType();
                Object value = valueArray[columnIndex];
                DataType actualType = DataType.map(value);
                if (expectedType != actualType && value != null) {
                    throw new IllegalArgumentException(String.format(
                            "Error: Invalid data type for column '%s'. Expected: %s, but got: %s",
                            columnName, expectedType, actualType)
                    );
                }
                column.insert(value, rowId.get());
            } else {
                column.insert(column.getColumnScheme().getValueByDefault(), rowId.get());
            }
        }
        rowIds.add(rowId.get());
        rowId.incrementAndGet();
        return this;
    }


    public Response select(String tableName, List<String> columnNames) {
        Response response = new Response(tableName, columnNames);
        for (String columnName : columnNames) {
            Column column = columns.get(columnName);
            if (column == null) {
                throw new RuntimeException(String.format("Error: column '%s' does not exist in table '%s'", columnName, tableName));
            }
            response.set(columnName, column.selectAll());
        }
        return response;
    }

    public Response select(String tableName, List<String> columnNames, String condition) {
        String[] parts = parseCondition(condition);
        validateConditionFormat(parts);
        String filteredColumnName = parts[0].trim();
        String operator = parts[1].trim();
        String value = parts[2].trim();
        // Получаем предикат для фильтрации строк
        Predicate<Object> filter = createFilter(filteredColumnName, operator, value);
        Response response = new Response(tableName, columnNames);
        for (String columnName : columnNames) {
            Column column = columns.get(columnName);
            if (column == null) {
                throw new RuntimeException(String.format("Error: column '%s' does not exist in table '%s'", columnName, tableName));
            }
            if (filteredColumnName.equals(columnName))
                response.set(filteredColumnName, column.select(filter));
            response.set(columnName, column.selectOther(response.getIds(filteredColumnName)));
        }
        return response;
    }

    public Table delete(String condition) {
        String[] parts = parseCondition(condition);
        validateConditionFormat(parts);
        String filteredColumnName = parts[0].trim();
        String operator = parts[1].trim();
        String value = parts[2].trim();
        // Получаем предикат для фильтрации строк
        Predicate<Object> filter = createFilter(filteredColumnName, operator, value);
        Column filteredColumn = columns.get(filteredColumnName);
        List<Value> deletedObjects = filteredColumn.select(filter);
        for (String columnName : columns.keySet()) {
            Column column = columns.get(columnName);
            if (column == null) {
                throw new RuntimeException(String.format("Error: column '%s' does not exist", columnName));
            }
            if (filteredColumnName.equals(columnName))
                column.delete(filter);
            column.deleteOther(deletedObjects);
        }
        Set<Integer> deletedIds = deletedObjects.stream()
                .map(Value::getId)
                .collect(Collectors.toSet());
        rowIds.removeIf(deletedIds::contains);
        return this;
    }

    public void update(List<String> values, String condition) {
        String[] conditionParts = parseCondition(condition);
        validateConditionFormat(conditionParts);
        String filteredColumnName = conditionParts[0].trim();
        String operator = conditionParts[1].trim();
        String value = conditionParts[2].trim();
        Predicate<Object> filter = createFilter(filteredColumnName, operator, value);
        Column filteredColumn = columns.get(filteredColumnName);
        List<Integer> valuesId = filteredColumn.select(filter).stream().map(Value::getId).toList();
        for (String updateValue : values) {
            String[] updateParts = updateValue.split("=");
            if (updateParts.length != 2) {
                throw new IllegalArgumentException(String.format("Invalid update value format: %s",updateValue));
            }
            String columnName = updateParts[0].trim();
            Column column = columns.get(columnName);
            if (column == null) {
                throw new RuntimeException(String.format("Error: column '%s' does not exist", columnName));
            }
            Object newValue = column.convertValue(updateParts[1].trim());
            column.update(newValue, valuesId);
        }
    }



    private String[] parseCondition(String condition) {
        String[] parts = condition.split(" ", 3);
        validateConditionFormat(parts);
        if (parts.length >= 3) {
            parts[2] = parts[2].replace("'", ""); // Удаление одиночных кавычек из третьего элемента
        }
        return parts;
    }

    private Predicate<Object> createFilter(String columnName, String operator, Object value) {
        validateColumnName(columnName, columns);
        Column column = columns.get(columnName);

        return switch (operator) {
            case "=" -> obj -> compareValues(column.getColumnScheme().getType(), obj, value) == 0;
            case "<" -> obj -> compareValues(column.getColumnScheme().getType(), obj, value) < 0;
            case ">" -> obj -> compareValues(column.getColumnScheme().getType(), obj, value) > 0;
            default -> throw new IllegalArgumentException(String.format("Error: Unsupported operator '%s'", operator));
        };
    }

    private int compareValues(DataType type, Object obj1, Object obj2) {
        return switch (type) {
            case INTEGER -> {
                int val1 = obj1 instanceof Integer ? (Integer) obj1 : Integer.parseInt(obj1.toString());
                int val2 = Integer.parseInt(obj2.toString());
                yield Integer.compare(val1, val2);
            }
            case STRING -> obj1.toString().compareTo(obj2.toString());
            case REAL -> {
                float val1 = obj1 instanceof Float ? (Float) obj1 : Float.parseFloat(obj1.toString());
                float val2 = Float.parseFloat(obj2.toString());
                yield Float.compare(val1, val2);
            }
            case BOOLEAN -> {
                boolean val1 = obj1 instanceof Boolean ? (Boolean) obj1 : Boolean.parseBoolean(obj1.toString());
                boolean val2 = Boolean.parseBoolean(obj2.toString());
                yield Boolean.compare(val1, val2);
            }
        };
    }

    public boolean contains(String columnName) {
        validateNonNull(columnName);
        return columns.containsKey(columnName);
    }

    public void modify(String columnName, String newColumnType) {
        if (contains(columnName)) {
            Column column = getColumn(columnName);
            if (!column.getFieldBody().objectList.isEmpty())
                throw new RuntimeException(String.format("Error: can't change column type to '%s' because column it isn't empty", newColumnType));
            else
                column.getColumnScheme().setType(DataType.valueOf(newColumnType));
        } else {
            throw new RuntimeException(String.format("Error: column '%s' doesn't exist", columnName));
        }
    }

    public void modify(String columnName,  String[] constraints) {
        validateColumnNames(List.of(columnName));
        Column column = getColumn(columnName);
        if (!column.getFieldBody().objectList.isEmpty())
            throw new RuntimeException(String.format("Error: can't add constraints to column '%s' because column it isn't empty", columnName));
        else
            for (String constraint : constraints) {
                Constraint constraintObj = ConstraintFactory.createConstraint(column, columnName, constraint);
                column.addConstraint(constraintObj);
            }
    }

    public void drop(String columnName, String[] constraintNames) {
        validateColumnNames(List.of(columnName));
        Column column = getColumn(columnName);
        for (String constraint: constraintNames){
            validateConstraints(constraint, column);
            column.removeConstraint(constraint);
        }
    }
}