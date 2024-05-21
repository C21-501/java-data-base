package database.system.core.structures.schemes;

import database.system.core.constraints.interfaces.Constraint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public record TableScheme(String tableName) {
    private static final Map<String, FieldScheme> fields = new HashMap<>();

    public TableScheme {
        if (tableName == null)
            throw new NullPointerException("`tableName` parameter is null");
    }

    public boolean contains(String columnName) {
        return fields.containsKey(columnName);
    }

    public FieldScheme getField(String columnName) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        return fields.get(columnName);
    }

    public void createField(String columnName, FieldScheme fieldScheme) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (fieldScheme == null)
            throw new NullPointerException("parameter `field` is null");
        fields.put(columnName, fieldScheme);
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
        FieldScheme removedFieldScheme = fields.remove(oldColumnName);
        if (removedFieldScheme == null)
            throw new RuntimeException(STR."column \{oldColumnName} not found");
        fields.put(newColumnName, removedFieldScheme);
    }

    public void updateField(String columnName, FieldScheme newFieldScheme) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (newFieldScheme == null)
            throw new NullPointerException("parameter `newField` is null");
        if (fields.replace(columnName, newFieldScheme) == null)
            throw new RuntimeException(STR."column \{columnName} does not exist");
    }

    public void addConstraint(String columnName, Constraint constraint) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        FieldScheme fieldScheme = fields.get(columnName);
        if (fieldScheme == null)
            throw new RuntimeException(STR."column '\{columnName}' does not exist");
        fieldScheme.addConstraint(constraint);
    }

    public void dropConstraint(String columnName, Constraint constraint) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        FieldScheme fieldScheme = fields.get(columnName);
        if (fieldScheme == null)
            throw new RuntimeException(STR."column \{columnName} does not exist");
        fieldScheme.removeConstraint(constraint);
    }

    public boolean containsValues(Object value) {
        return fields.containsValue((FieldScheme) value);
    }

    public List<FieldScheme> getFields() {
        return fields.values().stream().toList();
    }

    public long columnsNumber() {
        return getFields().size();
    }
}
