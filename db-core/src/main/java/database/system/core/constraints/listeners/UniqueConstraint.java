package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.bodies.Body;


public record UniqueConstraint() implements Constraint {
    @Override
    public boolean check(Body parent, Object value) {
        boolean exists = parent
                .getValues()
                .stream()
                .anyMatch(existingValue -> existingValue.getObject().equals(value));
        if (!exists) {
            return true;
        }
        throw new RuntimeException(STR."UniqueConstraint violation: Value '\{value}' already exists in the table");
    }
}