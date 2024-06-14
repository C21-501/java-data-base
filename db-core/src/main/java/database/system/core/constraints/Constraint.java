package database.system.core.constraints;

import database.system.core.structures.Column;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

import static database.api.utils.Utils.camelCaseToSnakeCase;

@Data
public abstract class Constraint implements Serializable {
    protected String columnName;
    protected String constraintName;
    protected Column column;
    private String childClassName;

    public Constraint(String childClassName, String columnName){
        this.childClassName = childClassName;
        if (Objects.isNull(columnName))
            throw new RuntimeException("Error: constraint parameters are null.");
        this.columnName = columnName;
        this.constraintName = String.format(
                "%s_%s_constraint",
                camelCaseToSnakeCase(childClassName.replace("Constraint", "")),
                camelCaseToSnakeCase(columnName)
        );
    }

    public Constraint(String childClassName, String columnName, Column column){
        this.childClassName = childClassName;
        if (Objects.isNull(columnName) && Objects.isNull(column))
            throw new RuntimeException("Error: constraint parameters are null.");
        this.columnName = columnName;
        this.column = column;
        this.constraintName = String.format(
                "%s_%s_constraint",
                camelCaseToSnakeCase(childClassName.replace("Constraint", "")),
                camelCaseToSnakeCase(columnName)
        );
    }

    public Constraint(String childClassName, String columnName, String constraintName, Column column){
        this.childClassName = childClassName;
        if (Objects.isNull(columnName))
            throw new RuntimeException("Error: constraint parameters are null.");
        if (Objects.isNull(constraintName)){
            this.constraintName = String.format(
                    "%s_%s_constraint",
                    childClassName.replace("Constraint", "").toLowerCase(),
                    columnName.toLowerCase()
            );
        } else {
            this.columnName = columnName;
            this.constraintName = constraintName;
        }
        this.column = column;
    }

    abstract public boolean serve(Object value);
}