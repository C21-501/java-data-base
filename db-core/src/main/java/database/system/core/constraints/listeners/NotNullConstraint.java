package database.system.core.constraints.listeners;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.interfaces.Listener;
import database.system.core.structures.Field;


public record NotNullConstraint() implements Constraint, Listener {
    @Override
    public boolean check(Object value) {
        if (!(value instanceof Field))
            throw new IllegalArgumentException("Parameter must be of class Column");
        return ((Field) value).getData() == null;
    }

    @Override
    public void update(Object value) {
        if (check(value)) {
            throw new RuntimeException(STR."NotNull constraint violation: \{value.getClass()}: \{value.toString()} cannot be null.");
        }
    }
}
