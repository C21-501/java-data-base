package database.system.core.constraints.interfaces;

import database.system.core.structures.Column;
import lombok.Data;

import java.util.Objects;

@Data
public abstract class Constraint {
    protected String columnName;
    protected String constraintName;
    protected Column column;

    public Constraint(String columnName){
        if (Objects.isNull(columnName))
            throw new RuntimeException("Error: constraint parameters are null.");
        this.columnName = columnName;
        this.constraintName = String.format("%s_constraint",columnName.toLowerCase());
    }

    public Constraint(String columnName, Column column){
        if (Objects.isNull(columnName) && Objects.isNull(column))
            throw new RuntimeException("Error: constraint parameters are null.");
        this.columnName = columnName;
        this.constraintName = String.format("%s_constraint",columnName.toLowerCase());
        this.column = column;
    }

    public Constraint(String columnName, String constraintName, Column column){
        if (Objects.isNull(columnName))
            throw new RuntimeException("Error: constraint parameters are null.");
        if (Objects.isNull(constraintName)){
            this.constraintName = String.format("%s_constraint",columnName.toLowerCase());
        } else {
            this.columnName = columnName;
            this.constraintName = constraintName;
        }
        this.column = column;
    }

    abstract public boolean check(Object value);
}