package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import lombok.EqualsAndHashCode;

import java.util.function.Predicate;

@EqualsAndHashCode(callSuper = false)
public class CheckConstraint extends Constraint {
    Predicate<Object> predicate;

    public CheckConstraint(String columnName, Predicate<Object> predicate) {
        super(columnName);
        if (predicate == null)
            throw new NullPointerException("'predicate' is null");
        this.predicate = predicate;
    }

    @Override
    public boolean check(Object value) {
        if (predicate.test(value))
            return true;
        throw new RuntimeException(String.format("CheckConstraint violation: The value '%s' does not satisfy the specified predicate", value));
    }
}

