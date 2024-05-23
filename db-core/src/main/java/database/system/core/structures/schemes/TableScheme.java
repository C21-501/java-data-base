package database.system.core.structures.schemes;

import database.system.core.constraints.interfaces.Constraint;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class TableScheme implements Scheme{
    private static final Map<String, FieldScheme> fields = new HashMap<>();

    public boolean contains(String columnName) {
        return fields.containsKey(columnName);
    }

    public FieldScheme getField(String columnName) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        return fields.get(columnName);
    }

    public TableScheme createField(String columnName, FieldScheme fieldScheme) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (fieldScheme == null)
            throw new NullPointerException("parameter `field` is null");
        fields.put(columnName, fieldScheme);
        return this;
    }

    public TableScheme dropField(String columnName) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (fields.remove(columnName) == null)
            throw new RuntimeException(STR."column with name \{columnName} not found");
        return this;
    }

    public TableScheme renameField(String oldColumnName, String newColumnName) {
        if (oldColumnName == null)
            throw new NullPointerException("parameter `oldColumnName` is null");
        if (newColumnName == null)
            throw new NullPointerException("parameter `newColumnName` is null");
        FieldScheme removedFieldScheme = fields.remove(oldColumnName);
        if (removedFieldScheme == null)
            throw new RuntimeException(STR."column \{oldColumnName} not found");
        fields.put(newColumnName, removedFieldScheme);
        return this;
    }

    public TableScheme updateField(String columnName, FieldScheme newFieldScheme) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (newFieldScheme == null)
            throw new NullPointerException("parameter `newField` is null");
        if (fields.replace(columnName, newFieldScheme) == null)
            throw new RuntimeException(STR."column \{columnName} does not exist");
        return this;
    }

    public TableScheme addConstraint(String columnName, Constraint constraint) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        FieldScheme fieldScheme = fields.get(columnName);
        if (fieldScheme == null)
            throw new RuntimeException(STR."column '\{columnName}' does not exist");
        fieldScheme.addConstraint(constraint);
        return this;
    }

    public TableScheme dropConstraint(String columnName, Constraint constraint) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        FieldScheme fieldScheme = fields.get(columnName);
        if (fieldScheme == null)
            throw new RuntimeException(STR."column \{columnName} does not exist");
        fieldScheme.removeConstraint(constraint);
        return this;
    }

    public TableScheme build(){
        return this;
    }

    public boolean containsValues(Object value) {
        return fields.containsValue((FieldScheme) value);
    }

    public List<FieldScheme> getFields() {
        return fields.values().stream().toList();
    }

    @Override
    public long getObjectsNumber() {
        return getFields().size();
    }
}
