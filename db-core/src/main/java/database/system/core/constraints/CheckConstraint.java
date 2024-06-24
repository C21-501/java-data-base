package database.system.core.constraints;

import lombok.EqualsAndHashCode;

import java.util.function.Predicate;

@EqualsAndHashCode(callSuper = false)
public class CheckConstraint extends Constraint {
    Predicate<Object> predicate;

    public CheckConstraint(String columnName, Predicate<Object> predicate) {
        super(CheckConstraint.class.getSimpleName(),columnName);
        if (predicate == null)
            throw new NullPointerException("Error: 'predicate' is null");
        this.predicate = predicate;
    }

    @Override
    public boolean serve(Object value) {
        if (predicate.test(value))
            return true;
        throw new RuntimeException(String.format("CheckConstraint violation: The value '%s' does not satisfy the specified predicate", value));
    }
}

