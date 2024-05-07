package database.types;

import lombok.Data;

/**
 * Enumeration for column constraints types in a relational database.
 */
public enum ColumnConstraint {
    /**
     * Constraint indicating that the column is a primary key.
     * The primary key uniquely identifies each record in the table.
     */
    PRIMARY_KEY,

    /**
     * Constraint indicating that the values in the column must be unique.
     * Duplicate values are not allowed in this column.
     */
    UNIQUE,

    /**
     * Constraint indicating that values in the column cannot be null.
     * Each value must be filled in without fail.
     */
    NOT_NULL,

    /**
     * Constraint allowing a user-defined condition for values in the column.
     * If the value does not satisfy the condition, the insert/update operation will be rejected.
     */
    CHECK,

    /**
     * Constraint specifying a default value for the column.
     * If a value is not explicitly specified during insertion, the default value will be used.
     */
    DEFAULT,

    /**
     * Constraint defining a foreign key linking this column to another table.
     * The value in this column must reference an existing record in the associated table.
     */
    FOREIGN_KEY
}

