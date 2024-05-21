package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.bodies.Body;
import database.system.core.structures.schemes.TableScheme;


public record ForeignKeyConstraint(TableScheme parentTableScheme) implements Constraint {
    public ForeignKeyConstraint {
        if (parentTableScheme == null)
            throw new NullPointerException("`parentTable` is null");
    }

    @Override
    public boolean check(Body parent, Object value) {
        return !parentTableScheme.contains(String.valueOf(value));
    }
}

