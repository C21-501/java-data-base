package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.bodies.Body;


public record UniqueConstraint() implements Constraint {
    @Override
    public boolean check(Body parent, Object value) {
        return !parent.getInnerObjects().contains(value);
    }
}