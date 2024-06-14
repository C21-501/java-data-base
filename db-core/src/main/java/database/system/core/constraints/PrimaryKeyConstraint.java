package database.system.core.constraints;

import database.system.core.structures.Column;


public class PrimaryKeyConstraint extends Constraint {

    public PrimaryKeyConstraint(String columnName, Column table) {
        super(PrimaryKeyConstraint.class.getSimpleName(),columnName, table);
    }

    @Override
    public boolean serve(Object value) {
        if (value == null)
            throw new NullPointerException("PrimaryKeyConstraint violation: Value is null");
        boolean exists = column.getFieldBody()
                .getValues()
                .stream()
                .anyMatch(existingValue -> existingValue.getObject().equals(value));
        if (!exists) {
            return true;
        }
        throw new RuntimeException(String.format("PrimaryKeyConstraint violation: Value '%s' already exists in the table", value));
    }
}