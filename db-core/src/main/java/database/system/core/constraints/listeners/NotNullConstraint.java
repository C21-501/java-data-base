package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.Field;


public record NotNullConstraint() implements Constraint {
    @Override
    public boolean check(Object value) {
        if (!(value instanceof Field))
            throw new IllegalArgumentException("Parameter must be of class Column");
        return ((Field) value).getObjectList() == null;
    }
}
