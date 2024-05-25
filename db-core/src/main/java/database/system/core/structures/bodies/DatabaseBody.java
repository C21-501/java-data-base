package database.system.core.structures.bodies;

import database.system.core.structures.schemes.DatabaseScheme;
import database.system.core.structures.schemes.TableScheme;
import lombok.Data;

import java.util.*;

@Data
public class DatabaseBody implements Body {
    private Integer tableId = 0;
    private DatabaseScheme databaseScheme;
    private static Map<String, TableBody> tableBodyMap = new HashMap<>();


    public DatabaseBody(DatabaseScheme databaseScheme) {
        if (databaseScheme == null)
            throw new NullPointerException("`databaseScheme` is null");
        this.databaseScheme = databaseScheme;
        for (Map.Entry<String, TableScheme> table :databaseScheme.getTables().entrySet()){
            tableBodyMap.put(table.getKey(), new TableBody().setTable(table.getValue()));
        }
    }

    public DatabaseBody insertInto(String tableName, List<Object[]> values){
        TableBody tableBody = tableBodyMap.get(tableName);
        if (tableBody == null)
            throw new RuntimeException(STR."Error: table '\{tableName}' not exists");
        for (Object value: values)
            tableBody.setFieldValue(value);
        return this;
    }

    @Override
    public List<Object> getInnerObjects() {
        return Collections.singletonList(tableBodyMap.values());
    }

    public void selectFrom(String tableName) {
        TableBody tableBody = tableBodyMap.get(tableName);
        if (tableBody == null)
            throw new RuntimeException(STR."Error: table '\{tableName}' not exists");
        int i = 0;
        for (Map.Entry<String, FieldBody> column: tableBody.getColumnBodyMap().entrySet()){
            selectFrom(tableName, column.getValue(), i);
            i++;
        }

    }

    private void selectFrom(String tableName, FieldBody column, int index) {
        System.out.print(STR."\{column} \{column.getValue(index)}");
    }
}
