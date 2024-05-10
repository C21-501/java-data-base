package database.system.core.constraints.listeners;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.interfaces.Listener;
import database.system.core.structures.Field;

import java.util.HashSet;
import java.util.Set;

public record UniqueConstraint(Field field) implements Constraint, Listener {
    private static final Set<Object> uniqueValues = new HashSet<>();

    @Override
    public boolean check(Object value) {
        // Check if the value is already present in the set of unique values
        return !uniqueValues.contains(value);
    }

    @Override
    public void update(Object value) {
        // If the value is not unique, throw an exception
        if (!check(value)) {
            throw new RuntimeException(STR."Unique constraint violation: Value \{value} is not unique.");
        }
        // Otherwise, add the value to the set of unique values
        uniqueValues.add(value);
    }

    @Override
    public ConstraintEnum get() {
        return ConstraintEnum.UNIQUE;
    }
}