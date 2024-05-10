package database.system.core.constraints.listeners;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.interfaces.Listener;
import database.system.core.structures.Table;

import java.util.HashSet;
import java.util.Set;

public record PrimaryKeyConstraint(Table parentTable) implements Constraint, Listener {
    private static final Set<Object> uniqueValues = new HashSet<>();

    @Override
    public boolean check(Object value) {
        // Check if the value is already present in the set of unique values
        return !uniqueValues.contains(value);
    }

    @Override
    public void update(Object object) {
        // When a value is updated, ensure it does not violate the primary key constraint
        if (!check(object)) {
            throw new IllegalArgumentException("Duplicate primary key value detected.");
        }
        uniqueValues.add(object);
    }

    @Override
    public ConstraintEnum get() {
        return ConstraintEnum.PRIMARY_KEY;
    }
}