package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;

import java.util.function.Predicate;


public record CheckConstraint(String constraintName, Predicate<Object> predicate) implements Constraint {
    public CheckConstraint {
        if (predicate == null || constraintName == null)
            throw new NullPointerException("`predicate` or `constraintName` is null");
    }

    @Override
    public boolean check(Object value) {
        if (value == null)
            throw new NullPointerException("`value` parameter is null");
        return predicate.test(value);
    }
}

