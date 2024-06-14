package database.system.core.constraints;

import database.system.core.structures.Column;
import database.system.core.types.DataType;

import static database.system.core.types.DataType.map;

public class DefaultConstraint extends Constraint {

    public DefaultConstraint(String columnName, Column column, Object defaultValue) {
        super(DefaultConstraint.class.getSimpleName().toLowerCase(),columnName, column);
        DataType valueType = map(defaultValue);
        if (column.getColumnScheme().getType() != valueType && defaultValue != null) {
            throw new IllegalArgumentException(String.format(
                    "Error: Type mismatch - expected '%s', but got '%s' (%s).",
                    column.getColumnScheme().getType(), valueType, defaultValue.getClass().getName()
            ));
        }
        column.getColumnScheme().setValueByDefault(defaultValue);
    }

    @Override
    public boolean serve(Object value) {
        return true;
    }
}