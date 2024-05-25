package database.system.core.structures.bodies;

import database.system.core.structures.schemes.FieldScheme;
import database.system.core.structures.schemes.TableScheme;
import lombok.Data;

import java.util.*;

@Data
public class TableBody implements Body {
    private Integer tableId = 0;
    private TableScheme tableScheme;
    private Map<String, FieldBody> columnBodyMap = new HashMap<>();

    public TableBody setTable(TableScheme tableScheme) {
        if (tableScheme == null)
            throw new NullPointerException("`table` is null");
        this.tableScheme = tableScheme;
        for (Map.Entry<String, FieldScheme> table: tableScheme.getFields().entrySet()){
            columnBodyMap.put(table.getKey(), new FieldBody().setFieldScheme(table.getValue()));
        }
        return this;
    }

    public TableBody setFieldValue(Object... objects) {
        if (objects.length != tableScheme.getObjectsNumber())
            throw new IllegalArgumentException("values number mismatches with column number");
        int i = 0;
        for (String columnName: columnBodyMap.keySet()) {
            columnBodyMap.get(columnName).insertValue(objects[i]);
            i++;
        }
        return this;
    }

    public Object getFieldValue(String columnName, int rowNumber){
        return columnBodyMap.get(columnName).getValue(rowNumber);
    }

    @Override
    public List<Object> getInnerObjects() {
        return Collections.singletonList(columnBodyMap);
    }
}
