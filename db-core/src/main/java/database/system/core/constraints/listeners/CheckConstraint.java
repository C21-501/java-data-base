package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.bodies.Body;

import java.util.function.Predicate;


public record CheckConstraint(Predicate<Object> predicate) implements Constraint {
    public CheckConstraint {
        if (predicate == null)
            throw new NullPointerException("`predicate` or `constraintName` is null");
    }

    @Override
    public boolean check(Body parent, Object value) {
        if (value == null)
            throw new NullPointerException("`value` parameter is null");
        if (predicate.test(value))
            return true;
        throw new RuntimeException(STR."CheckConstraint violation: The value '\{value}' does not satisfy the specified predicate");
    }
}

