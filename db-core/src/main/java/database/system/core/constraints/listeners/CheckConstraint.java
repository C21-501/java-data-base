package database.system.core.constraints.listeners;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.interfaces.Listener;
import database.system.core.structures.Field;

import java.util.function.Predicate;


public record CheckConstraint(Field field, Predicate<Object> predicate) implements Constraint, Listener {
    public static final ConstraintEnum CONSTRAINTS_ENUM = ConstraintEnum.CHECK;

    @Override
    public boolean check(Object value) {
        return !predicate.test(value);
    }

    @Override
    public void update(Object columnValue) {
        if (!check(columnValue))
            throw new RuntimeException("Check violation detected!");
        field.setData(columnValue);
    }

    @Override
    public ConstraintEnum get() {
        return CONSTRAINTS_ENUM;
    }
}

