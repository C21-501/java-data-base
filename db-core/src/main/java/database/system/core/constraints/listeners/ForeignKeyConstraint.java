package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;


public class ForeignKeyConstraint extends Constraint {

    public ForeignKeyConstraint(String columnName) {
        super(columnName);
    }

    @Override
    public boolean check(Object value) {
        return false;
    }
}

