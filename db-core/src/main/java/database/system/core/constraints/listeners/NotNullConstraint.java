package database.system.core.constraints.listeners;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.Column;


public class NotNullConstraint extends Constraint {

    public NotNullConstraint(String columnName, Column column){
        super(columnName, column);
    }

    @Override
    public boolean check(Object value) {
        if (value == null)
            throw new NullPointerException(STR."NotNullConstraint violation: Value \{value} is null");
        return true;
    }
}
