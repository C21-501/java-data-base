package database.system.core.constraints.interfaces;

import database.system.core.constraints.ConstraintEnum;

public interface Constraint {
    boolean check(Object value);
    ConstraintEnum get();
}

