package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.Table;
import database.system.core.structures.bodies.Body;

public record PrimaryKeyConstraint(Table parentTableScheme) implements Constraint {
    public PrimaryKeyConstraint {
        if (parentTableScheme == null)
            throw new NullPointerException("`parentTable` is null");
    }

    @Override
    public boolean check(Body parent, Object value) {
        // Check if the value is already present in the set of unique values
        return false;
    }
}