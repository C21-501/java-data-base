package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.Table;


public record ForeignKeyConstraint(Table parentTable) implements Constraint {
    public ForeignKeyConstraint {
        if (parentTable == null)
            throw new NullPointerException("`parentTable` is null");
    }

    @Override
    public boolean check(Object value) {
        return !parentTable.contains(String.valueOf(value));
    }
}

