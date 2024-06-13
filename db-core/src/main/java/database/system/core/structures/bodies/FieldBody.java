package database.system.core.structures.bodies;

import database.system.core.structures.Value;
import database.system.core.structures.schemes.FieldScheme;
import lombok.Data;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class FieldBody implements Body {
    private Integer fieldId = 0;
    public List<Value> objectList = new ArrayList<>(); // list of values

    public FieldBody(){}

    public FieldBody(FieldBody other) {
        if (!this.equals(other)){
            this.fieldId = other.fieldId;
            this.objectList = new ArrayList<>(other.objectList);
        }
    }

    public void insertValue(FieldScheme fieldScheme, Value value){
        if (fieldScheme.validate(this, value.getObject()))
            objectList.add(value);
    }

    public boolean validate(FieldScheme fieldScheme){
        for (Value value: objectList){
            if (!fieldScheme.validate(this, value.getObject()))
                return false;
        }
        return true;
    }

    public List<Value> getValues() { // method is useless in this class
        return objectList;
    }

    public Value getValue(int index) {
        return objectList.get(index);
    }

    public void updateById(Object updatedValue, List<Integer> valueIds) {
        objectList.replaceAll(obj -> {
            if (valueIds.contains(obj.getId())) {
                obj.setObject(updatedValue);
            }
            return obj;
        });
    }

    public void removeValuesIf(Predicate<Object> filter) {
        objectList.removeIf(value -> filter.test(value.getObject()));
    }

    public List<Value> selectValuesIf(Predicate<Object> filter) {
        return objectList.stream()
                .filter(object -> filter.test(object.getObject()))
                .collect(Collectors.toList());
    }

    public List<Value> selectValuesById(Set<Integer> ids) {
        return objectList.stream()
                .filter(value -> ids.contains(value.getId()))
                .collect(Collectors.toList());
    }

    public void removeValuesById(List<Integer> ids) {
        Set<Integer> idSet = new HashSet<>(ids);
        objectList.removeIf(value -> idSet.contains(value.getId()));
    }

    public void setByDefault(Set<Integer> ids, Object value) {
        objectList = ids.stream()
                .map(id -> new Value(id, value))
                .collect(Collectors.toList());
    }
}