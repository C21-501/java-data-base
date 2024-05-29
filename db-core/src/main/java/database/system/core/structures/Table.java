package database.system.core.structures;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.types.DataType;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@Data
public class Table implements DatabaseStructure {
    private Map<String, Column> columns = new HashMap<>();
    private Set<Constraint> constraintSet = new HashSet<>();

    private void validateColumnName(String columnName) {
        if (columnName == null || !columns.containsKey(columnName)) {
            throw new IllegalArgumentException(String.format("Error: Column '%s' does not exist.", columnName));
        }
    }

    private void validateNonNull(Object obj, String paramName) {
        if (obj == null) {
            throw new NullPointerException(String.format("Error: Parameter '%s' is null.", paramName));
        }
    }

    private void validateConditionFormat(String[] parts) {
        if (parts.length != 3) {
            throw new IllegalArgumentException("Error: Invalid condition format. Expected format: <column> <operator> <value>");
        }
    }

    public Column getColumn(String columnName) {
        validateColumnName(columnName);
        return columns.get(columnName);
    }

    public Table createColumn(String columnName, DataType type) {
        validateNonNull(columnName, "columnName");
        columns.put(columnName, new Column(type));
        return this;
    }

    public Table createColumn(String columnName, Column column) {
        validateNonNull(columnName, "columnName");
        validateNonNull(column, "column");
        columns.put(columnName, column);
        return this;
    }

    public void dropColumn(String columnName) {
        validateColumnName(columnName);
        columns.remove(columnName);
    }

    public Table renameField(String oldColumnName, String newColumnName) {
        validateColumnName(oldColumnName);
        validateNonNull(newColumnName, "newColumnName");
        Column removedFieldScheme = columns.remove(oldColumnName);
        columns.put(newColumnName, removedFieldScheme);
        return this;
    }

    public Table updateField(String columnName, Column newFieldScheme) {
        validateColumnName(columnName);
        validateNonNull(newFieldScheme, "newField");
        columns.replace(columnName, newFieldScheme);
        return this;
    }

    public Table addConstraint(String columnName, Constraint constraint) {
        validateColumnName(columnName);
        validateNonNull(constraint, "constraint");
        Column column = columns.get(columnName);
        column.getFieldScheme().addConstraint(constraint);
        return this;
    }

    public Table dropConstraint(String columnName, Constraint constraint) {
        validateColumnName(columnName);
        validateNonNull(constraint, "constraint");
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
        validateColumnName(oldColumnName);
        validateNonNull(newColumnName, "newColumnName");
        Column removed = columns.remove(oldColumnName);
        columns.put(newColumnName, removed);
    }

    public Table updateColumn(String columnName, Column column) {
        validateColumnName(columnName);
        validateNonNull(column, "newField");
        columns.replace(columnName, column);
        return this;
    }

    public Table insert(String columnName, Object value) {
        validateColumnName(columnName);
        columns.get(columnName).insert(value);
        return this;
    }

    public Table delete(String condition) {
        String[] parts = parseCondition(condition);
        validateConditionFormat(parts);
        String columnName = parts[0].trim();
        String operator = parts[1].trim();
        String value = parts[2].trim();
        // Получаем предикат для фильтрации строк
        Predicate<Object> filter = createFilter(columnName, operator, value);
        Column column = columns.get(columnName);
        column.delete(filter);
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
        return parts;
    }

    private Predicate<Object> createFilter(String columnName, String operator, Object value) {
        validateColumnName(columnName);
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
        validateNonNull(columnName, "columnName");
        return columns.containsKey(columnName);
    }
}
