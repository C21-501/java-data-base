package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.Column;


public class UniqueConstraint extends Constraint {
    public UniqueConstraint(String columnName, Column column) {
        super(columnName, column);
    }

    @Override
    public boolean check(Object value) {
        boolean exists = column.getFieldBody()
                .getValues()
                .stream()
                .anyMatch(existingValue -> existingValue.getObject().equals(value));
        if (!exists) {
            return true;
        }
        throw new RuntimeException(STR."UniqueConstraint violation: Value '\{value}' already exists in the table");
    }
}