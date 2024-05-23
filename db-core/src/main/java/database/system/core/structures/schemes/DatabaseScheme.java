package database.system.core.structures.schemes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@Data
public class DatabaseScheme implements Scheme {
    private static volatile DatabaseScheme instance;

    private final Map<String, TableScheme> tables = new HashMap<>();

    private DatabaseScheme() {}

    public static DatabaseScheme getInstance() {
        if (instance == null) {
            synchronized (DatabaseScheme.class) {
                if (instance == null) {
                    instance = new DatabaseScheme();
                }
            }
        }
        return instance;
    }

    public void createTable(String tableName, TableScheme tableScheme) {
        if (tableName == null || tableScheme == null)
            throw new NullPointerException("parameter `tableName` or `tableScheme` is null");
        if (tables.containsKey(tableName))
            throw new IllegalArgumentException(STR."Table already exists with name: \{tableName}");
        tables.put(tableName, tableScheme);
    }

    public void dropTable(String tableName) {
        if (tableName == null)
            throw new NullPointerException("parameter `tableName` is null");
        if (!tables.containsKey(tableName))
            throw new IllegalArgumentException(STR."Table does not exist: \{tableName}");
        tables.remove(tableName);
    }

    public TableScheme getTable(String tableName) {
        if (tableName == null)
            throw new NullPointerException("parameter `tableName` is null");
        TableScheme tableScheme = tables.get(tableName);
        if (tableScheme == null)
            throw new RuntimeException(STR."Table does not exist: \{tableName}");
        return tableScheme;
    }

    public boolean containsTable(String tableName) {
        if (tableName == null)
            throw new NullPointerException("parameter `tableName` is null");
        return tables.containsKey(tableName);
    }


    @Override
    public long getObjectsNumber() {
        return tables.size();
    }
}