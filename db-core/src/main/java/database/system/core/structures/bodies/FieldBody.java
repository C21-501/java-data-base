package database.system.core.structures.bodies;

import database.system.core.structures.schemes.FieldScheme;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
public class FieldBody implements Body {
    private record Value(int id, Object object) implements Serializable {}

    private Integer fieldId = 0;
    public final List<Value> objectList = new ArrayList<>(); // list of values
    private FieldScheme fieldScheme;

    public void insertValue(Object value){
        if (fieldScheme.validate(this, value))
            objectList.add(new Value(fieldId++, value));
    }

    @Override
    public List<Object> getInnerObjects() { // method is useless in this class
        return Arrays.asList(objectList.toArray());
    }

    public Object getValue(int index) {
        return objectList.get(index).object;
    }

    public FieldBody setFieldScheme(FieldScheme fieldScheme) {
        if (fieldScheme == null)
            throw new NullPointerException("fieldScheme is null");
        this.fieldScheme = fieldScheme;
        return this;
    }
}