package database.system.core.constraints.listeners;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.interfaces.Listener;
import database.system.core.structures.Field;

import java.util.function.Predicate;


public record CheckConstraint(Predicate<Object> predicate) implements Constraint {
    public CheckConstraint {
        if (predicate == null)
            throw new NullPointerException("`predicate` is null");
    }

    @Override
    public boolean check(Object value) {
        if (value == null)
            throw new NullPointerException("`value` parameter is null");
        return predicate.test(value);
    }
}

