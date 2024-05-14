package database.system.core.constraints.listeners;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.interfaces.Listener;
import database.system.core.structures.Field;

import java.util.Objects;
import java.util.function.Predicate;


public record CheckConstraint(Field field, Predicate<Object> predicate) implements Constraint, Listener {
    public static final ConstraintEnum CONSTRAINTS_ENUM = ConstraintEnum.CHECK;

    public CheckConstraint(Field field,Predicate<Object> predicate) {
        if (field == null || predicate == null)
            throw new NullPointerException("`field` or `predicate` is null");
        this.field = field;
        this.predicate = predicate;
    }

    @Override
    public boolean check(Object value) {
        if (value == null)
            throw new NullPointerException("`value` parameter is null");
        return predicate.test(value);
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

    @Override
    public String toString() {
        return "CheckConstraint[" +
                "field=" + field + ", " +
                "predicate=" + predicate + ']';
    }

}

