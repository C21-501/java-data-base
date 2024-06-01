package database.system.core.structures;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.types.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.function.Predicate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Table extends DatabaseStructure {
    private Map<String, Column> columns = new HashMap<>();
    private Set<Constraint> constraintSet = new HashSet<>();

    public Column getColumn(String columnName) {
        validateColumnName(columnName, columns);
        return columns.get(columnName);
    }

    public Table createColumn(String columnName, DataType type) {
        validateNonNull(columnName);
        columns.put(columnName, new Column(type));
        return this;
    }

    public Table createColumn(String columnName, Column column) {
        validateNonNull(columnName);
        validateNonNull(column);
        columns.put(columnName, column);
        return this;
    }

    public void dropColumn(String columnName) {
        validateColumnName(columnName, columns);
        columns.remove(columnName);
    }

    public Table renameField(String oldColumnName, String newColumnName) {
        validateColumnName(oldColumnName, columns);
        validateNonNull(newColumnName);
        Column removedFieldScheme = columns.remove(oldColumnName);
        columns.put(newColumnName, removedFieldScheme);
        return this;
    }

    public Table updateField(String columnName, Column newFieldScheme) {
        validateColumnName(columnName, columns);
        validateNonNull(newFieldScheme);
        columns.replace(columnName, newFieldScheme);
        return this;
    }

    public Table addConstraint(String columnName, Constraint constraint) {
        validateColumnName(columnName, columns);
        validateNonNull(constraint);
        Column column = columns.get(columnName);
        column.getFieldScheme().addConstraint(constraint);
        return this;
    }

    public Table dropConstraint(String columnName, Constraint constraint) {
        validateColumnName(columnName, columns);
        validateNonNull(constraint);
        Column column = columns.get(columnName);
        column.removeConstraint(constraint);
        return this;
    }

    public boolean containsValues(Object value) {
        return columns.containsValue((Column) value);
    }

    public long getObjectsNumber() {
        return columns.size();
    }

    public void renameColumn(String oldColumnName, String newColumnName) {
        validateColumnName(oldColumnName, columns);
        validateNonNull(newColumnName);
        Column removed = columns.remove(oldColumnName);
        columns.put(newColumnName, removed);
    }

    public Table updateColumn(String columnName, Column column) {
        validateColumnName(columnName, columns);
        validateNonNull(column);
        columns.replace(columnName, column);
        return this;
    }

    public Table insert(String columnName, Object value) {
        validateColumnName(columnName, columns);
        columns.get(columnName).insert(value);
        return this;
    }

    public Response select(String tableName, List<String> columnNames) {
        Response response = new Response(tableName);
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
        Response response = new Response(tableName);
        for (String columnName : columnNames) {
            Column column = columns.get(columnName);
            if (column == null) {
                throw new RuntimeException(String.format("Error: column '%s' does not exist in table '%s'", columnName, tableName));
            }
            if (filteredColumnName.equals(columnName))
                response.set(filteredColumnName, column.select(filter));
            response.set(columnName, column.selectOther(response.get(filteredColumnName)));
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
        return this;
    }

    public Table update(Object value, String condition) {
        String[] parts = parseCondition(condition);
        validateConditionFormat(parts);
        String columnName = parts[0].trim();
        String operator = parts[1].trim();
        String filteredValue = parts[2].trim();
        Predicate<Object> filter = createFilter(columnName, operator, filteredValue);
        Column column = columns.get(columnName);
        column.update(value, filter);
        return this;
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
            case "=" -> obj -> compareValues(column.getFieldScheme().getType(), obj, value) == 0;
            case "<" -> obj -> compareValues(column.getFieldScheme().getType(), obj, value) < 0;
            case ">" -> obj -> compareValues(column.getFieldScheme().getType(), obj, value) > 0;
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
}
