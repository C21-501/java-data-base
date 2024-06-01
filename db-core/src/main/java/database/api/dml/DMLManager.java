package database.api.dml;

import database.system.core.structures.Database;
import database.system.core.structures.Response;
import lombok.Data;

import java.util.List;

@Data
public class DMLManager {
    private Database database;

    public DMLManager(Database database) {
        this.database = database;
    }

    public void insert(String tableName, List<String> columns, List<Object[]> values) {
        database.insert(tableName, columns, values);
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

    public Response select(String tableName, List<String> columns) {
        // Логика выборки данных
        return database.select(tableName, columns);
    }
}
