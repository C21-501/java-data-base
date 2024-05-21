package database.system.core.constraints.interfaces;

import database.system.core.structures.bodies.Body;

public interface Constraint {
    boolean check(Body parent, Object value);
}