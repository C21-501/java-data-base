package database.system.core.structures;

import database.system.core.types.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@EqualsAndHashCode(callSuper = true)
@Data
public class Database extends DatabaseStructure {
    @Serial
    private static final long serialVersionUID = 1494292840007224912L;
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

    public static void resetInstance() {
        instance = null;
    }

    public boolean containsTable(String tableName) {
        validateTableName(tableName);
        return tables.containsKey(tableName);
    }

    public Table createTable(String tableName) {
        validateTableName(tableName);
        if (tables.containsKey(tableName))
            throw new IllegalArgumentException(String.format("Table already exists with name: %s", tableName));
        Table table = new Table();
        tables.put(tableName, table);
        return table;
    }

    public Table createTable(String tableName, Table table) {
        validateTableName(tableName);
        validateNonNull(table);
        if (tables.containsKey(tableName))
            throw new IllegalArgumentException(String.format("Table already exists with name: %s", tableName));
        tables.put(tableName, table);
        return table;
    }

    public Database dropTable(String tableName) {
        validateTableName(tableName);
        if (!tables.containsKey(tableName))
            throw new IllegalArgumentException(String.format("Table does not exist: %s", tableName));
        tables.remove(tableName);
        return this;
    }

    public Optional<Table> getTable(String tableName) {
        validateTableName(tableName);
        return Optional.ofNullable(tables.get(tableName));
    }

    public Database insertInto(String tableName, String[] columnNames, Object[] values) {
        validateTableName(tableName);
        validateColumnNames(columnNames);
        validateValues(values);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        for (int i = 0; i < columnNames.length; i++) {
            table.insert(columnNames[i], values[i]);
        }
        return this;
    }

    public Database insertInto(String tableName, String columnName, Object[] values) {
        validateTableName(tableName);
        validateNonNull(columnName);
        validateValues(values);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        Column column = table.getColumn(columnName);
        for (Object value : values) {
            column.insert(value);
        }
        return this;
    }

    public Database insertInto(String tableName, String[] columnNames, List<Object[]> firstTableValues) {
        validateTableName(tableName);
        validateColumnNames(columnNames);
        validateNonNull(firstTableValues);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't not exist", tableName));
        int i = 0;
        for (String columnName : columnNames) {
            Column column = table.getColumn(columnName);
            for (Object[] row : firstTableValues) {
                column.insert(row[i]);
            }
            i++;
        }
        return this;
    }

    public Database insertInto(String tableName, String columnName, Object value) {
        validateTableName(tableName);
        validateNonNull(columnName);
        validateNonNull(value);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't not exist", tableName));
        table.insert(columnName, value);
        return this;
    }

    public Response selectFrom(String tableName, String[] columnNames) {
        validateTableName(tableName);
        validateColumnNames(columnNames);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't not exist", tableName));
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

    public void delete(String tableName, String condition) {
        validateTableName(tableName);
        validateNonNull(condition);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        table.delete(condition);
    }

    public void update(String tableName, Object value, String condition) {
        validateTableName(tableName);
        validateNonNull(value);
        validateNonNull(condition);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        table.update(value, condition);
    }

    public Response select(String tableName, List<String> columns, String condition) {
        validateTableName(tableName);
        validateNonNull(columns);
        validateNonNull(condition);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        return table.select(tableName, columns, condition);
    }

    public Response select(String tableName, List<String> columns) {
        validateTableName(tableName);
        validateNonNull(columns);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        return table.select(tableName, columns);
    }

    public void alter(String tableName, List<String>... columnLists) {
        validateTableName(tableName);
        if (columnLists == null || columnLists.length > 3) {
            throw new IllegalArgumentException("Error: Expected three or less lists of columns: newColumns, modifiedColumns, droppedColumns.");
        }
        Table existingTable = getExistingTable(tableName);

        switch (columnLists.length) {
            case 1:
                if (columnLists[0] != null && !columnLists[0].isEmpty()) {
                    processNewColumns(columnLists[0], existingTable);
                }
                break;
            case 2:
                if (columnLists[0] != null && !columnLists[0].isEmpty()) {
                    processNewColumns(columnLists[0], existingTable);
                }
                if (columnLists[1] != null && !columnLists[1].isEmpty()) {
                    processModifiedColumns(columnLists[1], existingTable);
                }
                break;
            case 3:
                if (columnLists[0] != null && !columnLists[0].isEmpty()) {
                    processNewColumns(columnLists[0], existingTable);
                }
                if (columnLists[1] != null && !columnLists[1].isEmpty()) {
                    processModifiedColumns(columnLists[1], existingTable);
                }
                if (columnLists[2] != null && !columnLists[2].isEmpty()) {
                    processDroppedColumns(columnLists[2], existingTable);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid column list index.");
        }
        // обновить таблицу в базе данных
        tables.put(tableName, existingTable);
    }

    private Table getExistingTable(String tableName) {
        Table existingTable = tables.get(tableName);
        if (existingTable == null) {
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        }
        return existingTable;
    }

    private void processModifiedColumns(List<String> modifiedColumns, Table existingTable) {
        for (String modifiedColumn : modifiedColumns) {
            String[] parts = parseColumnDefinition(modifiedColumn);
            if (parts.length < 2) {
                throw new IllegalArgumentException(String.format("Error: Invalid column definition: %s", modifiedColumn));
            }
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
    }

    private void processNewColumns(List<String> newColumns, Table existingTable) {
        for (String newColumn : newColumns) {
            String[] parts = parseColumnDefinition(newColumn);
            if (parts.length < 2) {
                throw new IllegalArgumentException(String.format("Error: Invalid column definition: %s", newColumn));
            }
            String columnName = parts[0];
            String columnType = parts[1];
            if (!existingTable.contains(columnName)) {
                if (DataType.validate(columnType)) { // Проверяем, является ли тип данных допустимым
                    DataType dataType = DataType.valueOf(columnType);
                    Column column = new Column(dataType);
                    existingTable.createColumn(columnName, column);
                } else {
                    throw new IllegalArgumentException(String.format("Error: Invalid data type: %s", columnType));
                }
            }
        }
    }

    private void processDroppedColumns(List<String> droppedColumns, Table existingTable) {
        for (String columnName : droppedColumns) {
            existingTable.dropColumn(columnName);
        }
    }

    public void create(String tableName, List<String> columns) {
        Table table = new Table();
        for (String columnDefinition : columns) {
            String[] parts = parseColumnDefinition(columnDefinition);
            if (parts.length < 2) {
                throw new IllegalArgumentException(String.format("Error: Invalid column definition: %s", columnDefinition));
            }
            String columnName = parts[0];
            String columnType = parts[1];
            if (DataType.validate(columnType)) {
                Column column = new Column(DataType.valueOf(columnType));
                table.createColumn(columnName, column);
            } else {
                throw new IllegalArgumentException(String.format("Error: Invalid data type: %s", columnType));
            }
        }
        tables.put(tableName, table);
    }

    private String[] parseColumnDefinition(String columnDefinition) {
        return columnDefinition.split("\\s+");
    }

    public void drop(String tableName) {
        dropTable(tableName);
    }

    public void drop(){
        tables.clear();
    }


    public void insert(String tableName, List<String> columns, List<Object[]> values) {
        // Проверка, существует ли таблица
        Table table = tables.get(tableName);
        if (table == null) {
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        }
        // Проверка, что количество столбцов соответствует количеству значений
        for (Object[] valueArray : values) {
            if (valueArray.length != columns.size()) {
                throw new IllegalArgumentException("Error: Number of columns and values do not match.");
            }
        }
        // Вставка данных в таблицу
        for (Object[] valueArray : values) {
            for (int i = 0; i < columns.size(); i++) {
                String columnName = columns.get(i);
                Column column = table.getColumn(columnName);
                if (column == null) {
                    throw new RuntimeException(String.format("Error: column '%s' doesn't exist in table '%s'", columnName, tableName));
                }
                // Проверка типа данных перед вставкой
                DataType expectedType = column.getFieldScheme().getType();
                Object value = valueArray[i];
                DataType actualType = DataType.map(value);

                if (expectedType != actualType) {
                    throw new IllegalArgumentException(String.format(
                            "Error: Invalid data type for column '%s'. Expected: %s, but got: %s",
                            columnName, expectedType, actualType));
                }
                column.insert(value);
            }
        }
    }

    public void restore(Database backupDatabase) {
        // Проверка на null
        if (backupDatabase == null) {
            throw new IllegalArgumentException("Error: Backup database is null.");
        }
        // Очистка текущих таблиц
        tables.clear();

        // Восстановление таблиц и их данных из резервной копии
        for (Map.Entry<String, Table> entry : backupDatabase.tables.entrySet()) {
            String tableName = entry.getKey();
            Table backupTable = entry.getValue();
            // Копирование структуры таблицы
            Table table = new Table();
            for (Map.Entry<String, Column> columnEntry : backupTable.getColumns().entrySet()) {
                String columnName = columnEntry.getKey();
                Column backupColumn = columnEntry.getValue();

                // Копирование схемы и данных столбца
                Column column = new Column(backupColumn.getFieldScheme().getType());
                column.getFieldBody().getValues().addAll(backupColumn.getFieldBody().getValues());

                table.createColumn(columnName, column);
            }
            tables.put(tableName, table);
        }
    }

    public void saveState(String file) throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        }
    }
}
