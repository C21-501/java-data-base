package database.system.core.structures.bodies;

import database.system.core.structures.schemes.FieldScheme;
import lombok.Data;

import java.util.*;

@Data
public class FieldBody implements Body {
    private Integer fieldId = 0;
    public final List<Object> objectList = new ArrayList<>(); // list of values

    public void setField(FieldScheme scheme, Object value){
        if (scheme.validate(this, value))
            objectList.add(value);
    }

    @Override
    public List<Object> getInnerObjects() { // method is useless in this class
        return objectList;
    }
}