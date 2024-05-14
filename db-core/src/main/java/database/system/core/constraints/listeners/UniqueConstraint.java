package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.Table;

public record UniqueConstraint(Table parentTable) implements Constraint {
    public UniqueConstraint {
        if (parentTable == null)
            throw new NullPointerException("`parentTable` is null");
    }

    @Override
    public boolean check(Object value) {
        // Check if the value is already present in the set of unique values
        return parentTable.containsValues(value);
    }
}