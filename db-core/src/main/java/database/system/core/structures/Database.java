package database.system.core.structures;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode
public class Database {
    private static volatile Database instance;

    private final Map<String, Table> tables = new HashMap<>();

    private Database() {}

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public void createTable(String tableName, Table table) {
        if (tableName == null || table == null)
            throw new NullPointerException("parameter `tableName` or `table` is null");
        if (tables.containsKey(tableName))
            throw new IllegalArgumentException(STR."Table already exists with name: \{tableName}");
        tables.put(tableName, table);
    }

    public void dropTable(String tableName) {
        if (tableName == null)
            throw new NullPointerException("parameter `tableName` is null");
        if (!tables.containsKey(tableName))
            throw new IllegalArgumentException(STR."Table does not exist: \{tableName}");
        tables.remove(tableName);
    }

    public Table getTable(String tableName) {
        if (tableName == null)
            throw new NullPointerException("parameter `tableName` is null");
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(STR."Table does not exist: \{tableName}");
        return table;
    }

    public boolean containsTable(String tableName) {
        if (tableName == null)
            throw new NullPointerException("parameter `tableName` is null");
        return tables.containsKey(tableName);
    }
}