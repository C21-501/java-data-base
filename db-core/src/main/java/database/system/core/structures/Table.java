package database.system.core.structures;

import database.system.core.constraints.interfaces.Constraint;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


public record Table(String tableName) {
    @Getter
    private static final Map<String, Field> columnHashMap = new HashMap<>();

    public Table{
        if (tableName == null)
            throw new NullPointerException("`tableName` parameter is null");
    }

    public boolean contains(String columnName) {
        return columnHashMap.containsKey(columnName);
    }

    public Field getField(String columnName) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        return columnHashMap.get(columnName);
    }

    public void addField(String columnName, Field field) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (field == null)
            throw new NullPointerException("parameter `field` is null");
        columnHashMap.put(columnName,field);
    }

    public void dropField(String columnName) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (columnHashMap.remove(columnName) == null)
            throw new RuntimeException(STR."column with name \{columnName} not found");
    }

    public void renameField(String oldColumnName, String newColumnName) {
        if (oldColumnName == null)
            throw new NullPointerException("parameter `oldColumnName` is null");
        if (newColumnName == null)
            throw new NullPointerException("parameter `newColumnName` is null");
        Field removedField = columnHashMap.remove(oldColumnName);
        if (removedField == null)
            throw new RuntimeException(STR."column \{oldColumnName} not found");
        columnHashMap.put(newColumnName,removedField);
    }

    public void updateField(String columnName, Field newField) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (newField == null)
            throw new NullPointerException("parameter `newField` is null");
        if (columnHashMap.replace(columnName, newField) == null)
            throw new RuntimeException(STR."column \{columnName} does not exist");
    }

    public void addConstraint(String columnName, Constraint constraint) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        Field field = columnHashMap.get(columnName);
        if (field == null)
            throw new RuntimeException(STR."column \{columnName} does not exist");
        field.addConstraint(constraint);
    }

    public void dropConstraint(String columnName, Constraint constraint) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        Field field = columnHashMap.get(columnName);
        if (field == null)
            throw new RuntimeException(STR."column \{columnName} does not exist");
        field.removeConstraint(constraint);
    }

    public boolean containsKey(Object key) {
        return columnHashMap.containsKey(key.toString());
    }

    public boolean containsValues(Object value) {
        return columnHashMap.containsValue((Field) value);
    }
}
