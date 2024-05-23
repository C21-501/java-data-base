package database.system.core.structures.bodies;

import database.system.core.structures.schemes.TableScheme;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class TableBody implements Body {
    private Integer tableId = 0;
    private TableScheme tableScheme;
    private List<FieldBody> columnBodyList;

    public void setTable(TableScheme tableScheme) {
        if (tableScheme == null)
            throw new NullPointerException("`table` is null");
        this.tableScheme = tableScheme;
        this.columnBodyList = Stream.generate(FieldBody::new)
                .limit(tableScheme.getObjectsNumber())
                .collect(Collectors.toList());
    }

    public TableBody setFieldValues(Object... objects) {
        if (objects.length != tableScheme.getObjectsNumber())
            throw new IllegalArgumentException("values number mismatches with column number");
        for (int i = 0; i < tableScheme.getObjectsNumber(); i++) {
                columnBodyList.get(i).setField(tableScheme.getFields().get(i), objects[i]);
        }
        return this;
    }

    @Override
    public List<Object> getInnerObjects() {
        return Collections.singletonList(columnBodyList);
    }
}
