package database.system.core.constraints;

import database.system.core.structures.Column;


public class UniqueConstraint extends Constraint {
    Column column;

    public UniqueConstraint(String columnName, Column column) {
        super(UniqueConstraint.class.getSimpleName().toLowerCase(),columnName, column);
        this.column = column;
    }

    @Override
    public boolean serve(Object value) {
        boolean exists = column.getFieldBody()
                .getValues()
                .stream()
                .anyMatch(existingValue -> existingValue.getObject().equals(value));
        if (!exists) {
            return true;
        }
        throw new RuntimeException(String.format("UniqueConstraint violation: Value '%s' already exists in the table", value));
    }
}