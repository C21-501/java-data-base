package database.system.core.constraints.listeners;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.interfaces.Listener;
import database.system.core.structures.Table;


public record ForeignKeyConstraint(Table parentTable) implements Constraint, Listener {
    public static final ConstraintEnum CONSTRAINTS_ENUM = ConstraintEnum.FOREIGN_KEY;

    @Override
    public boolean check(Object value) {
        return !parentTable.contains(String.valueOf(value));
    }


    @Override
    public void update(Object columnValue) {

    }

    @Override
    public ConstraintEnum get() {
        return CONSTRAINTS_ENUM;
    }
}

