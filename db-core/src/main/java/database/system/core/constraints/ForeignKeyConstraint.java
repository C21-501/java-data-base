package database.system.core.constraints;

import database.system.core.structures.Column;
import database.system.core.structures.Table;

public class ForeignKeyConstraint extends Constraint {
    private final Table referenceTable;
    private final String referenceColumnName;

    public ForeignKeyConstraint(String columnName, Column column, Table referenceTable, String referenceColumnName) {
        super(ForeignKeyConstraint.class.getSimpleName(), columnName, column);
        this.referenceTable = referenceTable;
        this.referenceColumnName = referenceColumnName;
    }

    @Override
    public boolean serve(Object value) {
        boolean exists = referenceTable.getColumn(referenceColumnName)
                .getFieldBody()
                .getValues()
                .stream()
                .anyMatch(existingValue -> existingValue.getObject().equals(value));
        if (exists) {
            return true;
        }
        throw new RuntimeException(String.format("ForeignKeyConstraint violation: Value '%s' does not exist in the reference table", value));
    }
}
