package database.system.core.structures;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class DatabaseStructure implements Serializable {
    protected static final long serialVersionUID = 1494292840007224912L;
    
    protected void validateTableName(String tableName) {
        if (tableName == null || tableName.isEmpty()) {
            throw new NullPointerException("Error: Parameter 'tableName' is null or empty.");
        }
    }

    protected void validateColumnNames(String[] columnNames) {
        if (columnNames == null || columnNames.length == 0) {
            throw new IllegalArgumentException("Error: Column names array is null or empty.");
        }
    }

    protected void validateColumnNames(List<String> columnNames) {
        if (columnNames == null || columnNames.isEmpty()) {
            throw new IllegalArgumentException("Error: Column names array is null or empty.");
        }
    }

    protected void validateValues(Object[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Error: Values array is null or empty.");
        }
    }

    protected void validateConditionFormat(String[] parts) {
        if (parts == null || parts.length != 3) {
            throw new IllegalArgumentException("Error: Invalid condition format. Expected format: <column> <operator> <value>");
        }
    }

    protected void validatePredicate(Predicate<Object> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Error: Predicate is null.");
        }
    }
    protected void validateColumnName(String columnName, Map<String, Column> columns) {
        if (columnName == null || !columns.containsKey(columnName)) {
            throw new IllegalArgumentException(String.format("Error: Column '%s' does not exist.", columnName));
        }
    }

    protected void validateNonNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException(String.format("Error: Parameter is '%s'", obj));
        }
    }
}
