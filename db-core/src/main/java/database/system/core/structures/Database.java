package database.system.core.structures;

import database.system.core.types.DataType;
import lombok.Data;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

@Data
public class Database implements DatabaseStructure, Closeable {
    private static volatile Database instance;
    private final Map<String, Table> tables = new TreeMap<>();

    private Database() {
    }

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

    public boolean containsTable(String tableName) {
        return tables.containsKey(tableName);
    }

    public Table createTable(String tableName) {
        if (tableName == null)
            throw new NullPointerException("parameter `tableName` is null");
        if (tables.containsKey(tableName))
            throw new IllegalArgumentException(String.format("Table already exists with name: %s", tableName));
        Table table = new Table();
        tables.put(tableName, table);
        return table;
    }

    public Table createTable(String tableName, Table table) {
        if (tableName == null || table == null)
            throw new NullPointerException("parameter `tableName` or `table` is null");
        if (tables.containsKey(tableName))
            throw new IllegalArgumentException(String.format("Table already exists with name: %s", tableName));
        tables.put(tableName, table);
        return table;
    }

    public Database dropTable(String tableName) {
        if (tableName == null)
            throw new NullPointerException("parameter `tableName` is null");
        if (!tables.containsKey(tableName))
            throw new IllegalArgumentException(String.format("Table does not exist: %s", tableName));
        tables.remove(tableName);
        return this;
    }

    public Optional<Table> getTable(String tableName) {
        if (tableName == null)
            throw new NullPointerException("parameter `tableName` is null");
        return Optional.ofNullable(tables.get(tableName));
    }

    public Database insertInto(String tableName, String[] columnNames, Object[] values) {
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exists", tableName));
        for (int i = 0; i < columnNames.length; i++) {
            table.insert(columnNames[i],values[i]);
        }
        return this;
    }

    public Database insertInto(String tableName, String columnName, Object[] values) {
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exists", tableName));
        Column column = table.getColumn(columnName);
        for (Object value : values) {
            column.insert(value);
        }
        return this;
    }

    public Database insertInto(String tableName, String[] columnNames, List<Object[]> firstTableValues) {
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't not exists", tableName));
        int i = 0;
        for (String columnName : columnNames) {
            Column column = table.getColumn(columnName);
            for (Object[] row : firstTableValues) {
                column.insert((byte[]) row[i]);
            }
            i++;
        }
        return this;
    }
    public Database insertInto(String tableName, String columnName, Object value) {
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't not exists", tableName));
        table.insert(columnName,value);
        return this;
    }

    public Response selectFrom(String tableName, String[] columnNames) {
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't not exists", tableName));
        Response response = new Response(tableName);
        for (String columnName : columnNames) {
            Column column = table.getColumn(columnName);
            if (column == null) {
                throw new RuntimeException(String.format("Error: column '%s' does not exist in table '%s'", columnName, tableName));
            }
            response.set(columnName, column.getFieldBody().getValues());
        }
        return response;
    }

    public Response selectFrom(String tableName, String[] columnNames, Predicate<Object> predicate) {
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't not exists", tableName));
        Response response = new Response(tableName);
        for (String columnName : columnNames) {
            Column column = table.getColumn(columnName);
            response.set(columnName, column.getFieldBody().getValues(), predicate);
        }
        return response;
    }

    public Database alterTable(String tableName, List<String> modifiedColumns, List<String> droppedColumns, Table table) {
        Table existingTable = tables.get(tableName);
        if (existingTable == null) {
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        }
        // Обработка измененных столбцов
        for (String modifiedColumn : modifiedColumns) {
            String[] parts = modifiedColumn.split("\\s+");
            String columnName = parts[0];
            String newColumnType = parts[1]; // Предполагаем, что тип столбца указан в строке
            if (existingTable.contains(columnName)) {
                // Изменяем тип данных столбца
                Column column = existingTable.getColumn(columnName);
                column.getFieldScheme().setType(DataType.valueOf(newColumnType));
            } else {
                throw new RuntimeException(String.format("Error: column '%s' doesn't exist", columnName));
            }
        }
        // Обработка добавленных столбцов
        for (Map.Entry<String, Column> entry : table.getColumns().entrySet()) {
            String columnName = entry.getKey();
            if (!existingTable.contains(columnName)) {
                existingTable.createColumn(columnName, entry.getValue());
            }
        }
        // Обработка удаленных столбцов
        for (String columnName : droppedColumns) {
            existingTable.dropColumn(columnName);
        }
        return this;
    }

    public void delete(String tableName, String condition) {
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        table.delete(condition);
    }

    public void update(String tableName, Object value, String condition) {
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        table.update(value, condition);
    }

    public Response select(String tableName, List<String> columns, String condition) {
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));

        Response response = new Response(tableName);
        for (String columnName : columns) {
            Column column = table.getColumn(columnName);
            if (column == null) {
                throw new RuntimeException(String.format("Error: column '%s' does not exist in table '%s'", columnName, tableName));
            }
            response.set(columnName, column.getFieldBody().getValues());
        }
        return response;
    }

    @Override
    public void close() throws IOException {
        tables.clear();
    }
}