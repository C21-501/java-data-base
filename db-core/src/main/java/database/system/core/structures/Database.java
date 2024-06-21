package database.system.core.structures;

import database.system.core.exceptions.DatabaseRuntimeException;
import database.system.core.exceptions.enums.RuntimeError;
import database.system.core.types.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static database.system.core.structures.Database.DatabaseState.CREATED;
import static database.system.core.structures.Database.DatabaseState.IN_WORK;


@EqualsAndHashCode(callSuper = true)
@Data
public class Database extends DatabaseStructure {
    private String filePath;
    private static volatile Database instance;
    private Map<String, Table> tables = new TreeMap<>();
    private DatabaseState state = DatabaseState.IDLE;

    public enum DatabaseState {
        IDLE,
        RESET,
        CREATED,
        IN_WORK,
        DROPPED
    };

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

    public Table create(String tableName) {
        validateTableName(tableName);
        if (tables.containsKey(tableName))
            throw new DatabaseRuntimeException(RuntimeError.TABLE_ALREADY_EXIST, tableName);
        return switch (state){
            case IDLE, DROPPED, RESET -> throw new DatabaseRuntimeException(RuntimeError.INVALID_DATABASE_STATE, state.name());
            case CREATED -> {
                state = IN_WORK;
                Table table = new Table();
                tables.put(tableName, table);
                yield  table;
            }
            case IN_WORK -> {
                Table table = new Table();
                tables.put(tableName, table);
                yield  table;
            }
        };
    }

    public void drop(String tableName) {
        validateTableName(tableName);
        if (!tables.containsKey(tableName))
            throw new IllegalArgumentException(String.format("Table does not exist: %s", tableName));
        tables.remove(tableName);
    }

    public Optional<Table> getTable(String tableName) {
        validateTableName(tableName);
        return Optional.ofNullable(tables.get(tableName));
    }

    public void delete(String tableName, String condition) {
        validateTableName(tableName);
        validateNonNull(condition);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        table.delete(condition);
    }

    public void update(String tableName, List<String> values, String condition) {
        validateTableName(tableName);
        validateNonNull(values);
        validateNonNull(condition);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        table.update(values, condition);
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

    public Response select(String tableName) {
        validateTableName(tableName);
        Table table = tables.get(tableName);
        if (table == null)
            throw new RuntimeException(String.format("Error: table '%s' doesn't exist", tableName));
        List<String> columns = table.getColumns().keySet().stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();
        return table.select(tableName, columns);
    }

    @SafeVarargs
    public final void alter(String tableName, List<String>... columnLists) {
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
            String parameter = parts[1];
            if (DataType.validate(parameter)){
                existingTable.modify(columnName, parameter);
            } else {
                String[] constraints = Arrays.copyOfRange(parts, 1, parts.length);
                existingTable.modify(columnName, constraints);
            }
        }
    }

    private void processNewColumns(List<String> newColumns, Table existingTable) {
        create(existingTable, newColumns);
    }

    private void processDroppedColumns(List<String> droppedColumns, Table existingTable) {
        for (String modifiedColumn : droppedColumns) {
            String[] parts = parseColumnDefinition(modifiedColumn);
            if (parts.length < 1) {
                throw new IllegalArgumentException(String.format("Error: Invalid column definition: %s", modifiedColumn));
            } else if (parts.length == 1) {
                existingTable.dropColumn(parts[0]);
            } else {
                String columnName = parts[0];
                String[] constraints = Arrays.copyOfRange(parts, 1, parts.length);
                existingTable.drop(columnName, constraints);
            }
        }
    }

    public void create(Table existingTable, List<String> newColumns) {
        validateColumnNames(newColumns);
        processCreateNewColumns(newColumns, existingTable);
    }

    public void create(String tableName, List<String> columns) {
        validateTableName(tableName);
        if (tables.containsKey(tableName))
            throw new DatabaseRuntimeException(RuntimeError.TABLE_ALREADY_EXIST, tableName);
        validateColumnNames(columns);
        Table table = new Table();
        processCreateNewColumns(columns, table);
        tables.put(tableName, table);
    }

    private void processCreateNewColumns(List<String> columns, Table table) {
        for (String columnDefinition : columns) {
            String[] parts = parseColumnDefinition(columnDefinition);
            if (parts.length < 2) {
                throw new IllegalArgumentException(String.format("Error: Invalid column definition: %s. Type is necessary.", columnDefinition));
            } else if (parts.length == 2) {
                String columnName = parts[0];
                String columnType = parts[1];
                table.create(columnName, columnType);
            } else {
                String columnName = parts[0];
                String columnType = parts[1];
                String[] constraints = Arrays.copyOfRange(parts, 2, parts.length);
                table.create(columnName,columnType, constraints);
            }
        }
    }


    private String @NotNull[] parseColumnDefinition(String columnDefinition) {
        // Regex pattern to capture column name, column type, and constraints
        String regex = "\\s*(\\w+)\\s+(\\w+)(.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(columnDefinition.trim());

        if (matcher.matches()) {
            String columnName = matcher.group(1);
            String columnType = matcher.group(2);
            String constraints = matcher.group(3).trim();

            List<String> constraintsList = new ArrayList<>();

            // Split constraints respecting spaces within CHECK constraints and handling foreign key constraints
            String[] constraintParts = constraints.split("(?=\\bNOT\\s+NULL\\b|\\bPRIMARY\\s+KEY\\b|\\bUNIQUE\\b|\\bCHECK\\b)");

            for (String part : constraintParts) {
                part = part.trim();
                if (!part.isEmpty()) {
                    constraintsList.add(part);
                }
            }

            String[] result = new String[constraintsList.size() + 2];
            result[0] = columnName;
            result[1] = columnType;

            // Copy elements from constraintsList into result array starting from index 2
            System.arraycopy(constraintsList.toArray(new String[0]), 0, result, 2, constraintsList.size());

            return result;
        } else {
            return new String[]{columnDefinition};
        }
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
            table.insert(columns, valueArray);
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
            tables.put(tableName, backupTable);
        }
    }

    public void saveStateInFile(String file) throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        }
    }

    public void alter(String tableName, String newTableName) {
        validateTableName(tableName);
        validateTableName(newTableName);
        Table removedTable = tables.remove(tableName);
        tables.put(newTableName, removedTable);
    }
}
