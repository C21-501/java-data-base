package database.system.core.structures.bodies;

import database.system.core.structures.Value;
import database.system.core.structures.schemes.FieldScheme;
import lombok.Data;

import java.util.*;

@Data
public class FieldBody implements Body {
    private Integer fieldId = 0;
    public final List<Value> objectList = new ArrayList<>(); // list of values

    public void insertValue(FieldScheme fieldScheme, byte[] value){
        if (fieldScheme.validate(this, value))
            objectList.add(new Value(fieldId++, value));
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
}