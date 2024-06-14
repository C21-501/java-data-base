package database.system.core.structures;

import database.system.core.constraints.Constraint;
import database.system.core.constraints.DefaultConstraint;
import database.system.core.structures.bodies.FieldBody;
import database.system.core.structures.schemes.ColumnScheme;
import database.system.core.types.DataType;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;


@Getter
public class Column extends DatabaseStructure {
    private ColumnScheme columnScheme = new ColumnScheme();
    private FieldBody fieldBody = new FieldBody();

    public Column(DataType dataType){
        columnScheme.setType(dataType);
    }

    public Column(Column other) {
        if (!this.equals(other)){
            this.columnScheme = new ColumnScheme(other.getColumnScheme());
            this.fieldBody = new FieldBody(other.getFieldBody());
        }
    }

    public Column(DataType dataType, Set<Integer> ids, Object defaultValue) {
        columnScheme.setType(dataType);
        if (columnScheme.validate(defaultValue))
            fieldBody.setByDefault(ids, defaultValue);
    }

    public Object convertValue(String value) {
        return columnScheme.convertValueToValidType(value);
    }

    public Column addConstraint(Constraint constraint){
        columnScheme.addConstraint(constraint);
        fieldBody.validate(columnScheme);
        return this;
    }

    public void removeConstraint(String constraintName){
        columnScheme.removeConstraint(constraintName);
    }

    public void insert(Object value, int depth) {
        Value insertion = new Value(depth, value);
        fieldBody.insertValue(columnScheme, insertion);
    }

    public void delete(Predicate<Object> filter) {
        fieldBody.removeValuesIf(filter);
    }

    public void deleteOther(List<Value> values) {
        fieldBody.removeValuesById(values.stream().map(Value::getId).toList());
    }

    public void update(Object value, List<Integer> valueIds) {
        fieldBody.updateById(value, valueIds);
    }

    public List<Value> select(Predicate<Object> filter) {
        return fieldBody.selectValuesIf(filter);
    }

    public List<Value> selectOther(Set<Integer> ids) {return fieldBody.selectValuesById(ids);}

    public List<Value> selectAll() {
        return fieldBody.getValues();
    }

    public boolean containsConstraint(Class<DefaultConstraint> defaultConstraintClass) {
        return columnScheme.contains(defaultConstraintClass);
    }
}
