package database.system.core.constraints;

import database.system.core.structures.Column;


public class NotNullConstraint extends Constraint {
    public NotNullConstraint(String columnName, Column table){
        super(NotNullConstraint.class.getSimpleName(), columnName, table);
    }

    @Override
    public boolean serve(Object value) {
        if (value == null)
            throw new NullPointerException(STR."NotNullConstraint violation: Value \{value} is null");
        return true;
    }
}
