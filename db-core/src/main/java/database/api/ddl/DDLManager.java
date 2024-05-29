package database.api.ddl;

import database.system.core.structures.Database;
import lombok.Data;

import java.util.List;

@Data
public class DDLManager {
    private Database database;
    public DDLManager(Database database) {
        this.database = database;
    }

    public void create(String tableName, List<String> columns) {
        database.create(tableName, columns);
    }

    public void alter(String tableName, List<String>... alterColumns) {
        database.alter(tableName, alterColumns);
    }

    public void drop(String tableName) {
        database.drop(tableName);
    }
}
