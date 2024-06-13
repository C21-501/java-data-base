package database.system.core.structures;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.bodies.FieldBody;
import database.system.core.structures.schemes.FieldScheme;
import database.system.core.types.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Column extends DatabaseStructure {
    private FieldScheme fieldScheme = new FieldScheme();
    private FieldBody fieldBody = new FieldBody();

    public Column(DataType dataType){
        fieldScheme.setType(dataType);
    }

    public Column(Column other) {
        if (!this.equals(other)){
            this.fieldScheme = new FieldScheme(other.getFieldScheme());
            this.fieldBody = new FieldBody(other.getFieldBody());
        }
    }

    public Column(DataType dataType, Set<Integer> ids, Object defaultValue) {
        fieldScheme.setType(dataType);
        if (fieldScheme.validate(fieldBody,defaultValue))
            fieldBody.setByDefault(ids, defaultValue);
    }

    public Object convertValue(String value) {
        return fieldScheme.convertValueToValidType(value);
    }

    public Column setConstraint(Constraint constraint){
        fieldScheme.addConstraint(constraint);
        fieldBody.validate(fieldScheme);
        return this;
    }

    public void removeConstraint(Constraint constraint){
        fieldScheme.removeConstraint(constraint);
    }

    public void insert(Object value, int depth) {
        Value insertion = new Value(depth, value);
        fieldBody.insertValue(fieldScheme, insertion);
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
}
