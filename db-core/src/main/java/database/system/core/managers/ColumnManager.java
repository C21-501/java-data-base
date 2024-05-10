package database.system.core.managers;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.interfaces.Listener;
import database.system.core.structures.Field;
import database.system.core.types.DataType;
import lombok.Data;


import java.util.HashMap;
import java.util.Map;

@Data
public class ColumnManager {
    private final Map<String, Field> columns;
    private final Map<ConstraintEnum, Listener> constraintListeners;

    public ColumnManager(){
        this.columns = new HashMap<>();
        this.constraintListeners = new HashMap<>();
    }

    public void createColumn(String name, DataType type) {
        if (columns.containsKey(name)) {
            throw new IllegalArgumentException(STR."Column with name \{name} already exists");
        }
        columns.put(name, new Field(type));
    }

    public void dropColumn(String name) {
        columns.remove(name);
    }

    public void addConstraint(String columnName, Constraint constraint) {
        Field field = columns.get(columnName);
        if (field == null) {
            throw new IllegalArgumentException(STR."Column \{columnName} not found");
        }
        field.addConstraint(constraint);
    }

    public void removeConstraint(String columnName, Constraint constraint) {
        Field field = columns.get(columnName);
        if (field == null) {
            throw new IllegalArgumentException(STR."Column \{columnName} not found");
        }
        field.removeConstraint(constraint);
    }
}
