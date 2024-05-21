package database.system.core.structures.bodies;

import database.system.core.structures.schemes.TableScheme;
import lombok.Data;

import java.util.*;

@Data
public class TableBody implements Body {
    private Integer tableId = 0;
    private TableScheme tableScheme;
    private List<FieldBody> columnBodyList;

    public TableBody(TableScheme tableScheme) {
        if (tableScheme == null)
            throw new NullPointerException("`table` is null");
        this.tableScheme = tableScheme;
        this.columnBodyList = new ArrayList<>();

    }

    public void setFieldValues(Object... objects) {
        FieldBody fieldBody = new FieldBody();
        try {
            for (int i = 0; i < objects.length; i++) {
                fieldBody.setField(tableScheme.getFields().get(i), objects[i]);
            }
            columnBodyList.add(fieldBody);
        } catch (RuntimeException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public List<Object> getInnerObjects() {
        return Collections.singletonList(columnBodyList);
    }
}
