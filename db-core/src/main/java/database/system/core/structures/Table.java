package database.system.core.structures;

import database.system.core.constraints.interfaces.Constraint;

import java.util.HashMap;
import java.util.Map;


public record Table(String tableName) {
    private static final Map<String, Field> fields = new HashMap<>();

    public Table{
        if (tableName == null)
            throw new NullPointerException("`tableName` parameter is null");
    }

    public boolean contains(String columnName) {
        return fields.containsKey(columnName);
    }

    public Field getField(String columnName) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        return fields.get(columnName);
    }

    public void createField(String columnName, Field field) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (field == null)
            throw new NullPointerException("parameter `field` is null");
        fields.put(columnName,field);
    }

    public void dropField(String columnName) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (fields.remove(columnName) == null)
            throw new RuntimeException(STR."column with name \{columnName} not found");
    }

    public void renameField(String oldColumnName, String newColumnName) {
        if (oldColumnName == null)
            throw new NullPointerException("parameter `oldColumnName` is null");
        if (newColumnName == null)
            throw new NullPointerException("parameter `newColumnName` is null");
        Field removedField = fields.remove(oldColumnName);
        if (removedField == null)
            throw new RuntimeException(STR."column \{oldColumnName} not found");
        fields.put(newColumnName,removedField);
    }

    public void updateField(String columnName, Field newField) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (newField == null)
            throw new NullPointerException("parameter `newField` is null");
        if (fields.replace(columnName, newField) == null)
            throw new RuntimeException(STR."column \{columnName} does not exist");
    }

    public void addConstraint(String columnName, Constraint constraint) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        Field field = fields.get(columnName);
        if (field == null)
            throw new RuntimeException(STR."column '\{columnName}' does not exist");
        field.addConstraint(constraint);
    }

    public void dropConstraint(String columnName, Constraint constraint) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        Field field = fields.get(columnName);
        if (field == null)
            throw new RuntimeException(STR."column \{columnName} does not exist");
        field.removeConstraint(constraint);
    }

    public boolean containsKey(Object key) {
        return fields.containsKey(key.toString());
    }

    public boolean containsValues(Object value) {
        return fields.containsValue((Field) value);
    }

    public Iterable<? extends Field> getFields() {
        return fields.values();
    }
}
