package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.Column;


public class PrimaryKeyConstraint extends Constraint {

    public PrimaryKeyConstraint(String columnName, Column column) {
        super(columnName, column);
    }

    @Override
    public boolean check(Object value) {
        // Check if the value is already present in the set of unique values
        return false;
    }
}