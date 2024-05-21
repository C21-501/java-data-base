package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.bodies.Body;


public record NotNullConstraint() implements Constraint {
    @Override
    public boolean check(Body parent, Object value) {
        if (value == null)
            throw new NullPointerException(STR."NotNullConstraint violation: Value \{value} is null");
        return true;
    }
}
