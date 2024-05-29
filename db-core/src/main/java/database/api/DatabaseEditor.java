package database.api;

import database.system.core.managers.DatabaseSerializer;
import database.system.core.structures.Column;
import database.system.core.structures.Database;
import database.system.core.structures.Response;
import database.system.core.structures.Table;
import database.system.core.types.DataType;
import lombok.Data;

import java.io.*;
import java.util.List;

@Data
public class DatabaseEditor {
    private final DatabaseSerializer databaseSerializer;
    private final Database database;

    public DatabaseEditor(String dbName) {
        this.databaseSerializer = new DatabaseSerializer(dbName);
        this.database = databaseSerializer.getDatabase();
    }

    public void saveDatabaseState() {
        try {
            databaseSerializer.update();
            databaseSerializer.save();
            System.out.println("Database state saved successfully.");
        } catch (IOException e) {
            System.err.println(STR."Error while saving database state: \{e.getMessage()}");
        }
    }

    public void restoreDatabaseState() {
        try {
            databaseSerializer.read();
            System.out.println("Database state restored successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(STR."Error while restoring database state: \{e.getMessage()}");
        }
    }

    @Data
    public class DDL {
        public void createTable(String tableName, List<String> columns) {
            // Логика создания таблицы
            Table table = new Table();
            for (String columnName : columns) {
                String[] parts = columnName.split("\\s+");
                Column column = new Column(DataType.valueOf(parts[1]));
                table.createColumn(columnName, column);
            }
            database.createTable(tableName, table);
        }

        public void alterTable(
                String tableName,
                List<String> addedColumns,
                List<String> modifiedColumns,
                List<String> droppedColumns
        ) {
            // Логика изменения таблицы
            Table table = new Table();
            // Добавление новых столбцов
            for (String columnName : addedColumns) {
                String[] parts = columnName.split("\\s+");
                Column column = new Column(DataType.valueOf(parts[1]));
                table.createColumn(columnName, column);
            }
            database.alterTable(tableName, modifiedColumns, droppedColumns, table);
        }

        public void dropTable(String tableName) {
            database.dropTable(tableName);
        }
    }

    @Data
    public class DML {
        public void insert(String tableName, String[] columns, List<Object[]> values) {
            // Логика вставки данных
            for (Object[] row : values) {
                database.insertInto(tableName, columns, row);
            }
        }

        public void update(String tableName, Object value, String condition) {
            // Логика обновления данных
            database.update(tableName, value, condition);
        }

        public void delete(String tableName, String condition) {
            // Логика удаления данных
            database.delete(tableName, condition);
        }

        public Response select(String tableName, List<String> columns, String condition) {
            // Логика выборки данных
            return database.select(tableName, columns, condition);
        }
        // Другие методы DML
    }


    @Data
    public class TCL {
        public void beginTransaction() {
            // Логика начала транзакции
        }

        public void commit() {
            // Логика подтверждения транзакции
        }

        public void rollback() {
            // Логика отката транзакции
        }
    }
}
