package database.system.core.constraints;

import database.system.core.structures.Column;


public class PrimaryKeyConstraint extends Constraint {

    public PrimaryKeyConstraint(String columnName, Column table) {
        super(PrimaryKeyConstraint.class.getSimpleName().toLowerCase(),columnName, table);
    }

    @Override
    public boolean serve(Object value) {

        return true;
    }
}