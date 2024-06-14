package database.system.core.constraints;

import database.system.core.structures.Column;
import database.system.core.structures.Table;


public class ForeignKeyConstraint extends Constraint {
    private final Table referenceTable;

    public ForeignKeyConstraint(String columnName, Column column, Table referenceTable) {
        super(ForeignKeyConstraint.class.getSimpleName().toLowerCase(),columnName, column);
        this.referenceTable = referenceTable;
    }

    @Override
    public boolean serve(Object value) {
        return false;
    }
}

