package database.system.core.structures.records;

import database.system.core.structures.Field;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
public class FieldRecord implements Serializable {
    private Integer fieldId = 0;

    public static final Map<Integer,Field> fieldMapRecord = new TreeMap<>();

    public void setField(Field field) {
        fieldMapRecord.put(fieldId++, field);
    }
}