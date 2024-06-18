package database.api.ddl;

import database.system.core.structures.Database;
import lombok.Data;

import java.util.List;

/**
 * The DDLManager class manages Data Definition Language (DDL) operations on the database.
 * It provides methods to create, alter, and drop tables in the database.
 */
@Data
public class DDLManager {
    private Database database;

    /**
     * Constructs a new DDLManager instance.
     *
     * @param database the database instance on which DDL operations will be performed
     */
    public DDLManager(Database database) {
        this.database = database;
    }

    /**
     * Creates a new table in the database.
     *
     * @param tableName the name of the table to be created
     * @param columns   the columns to be included in the new table, represented as a list of strings
     */
    public void create(String tableName, List<String> columns) {
        database.create(tableName, columns);
    }

    /**
     * Alters an existing table in the database.
     *
     * @param tableName   the name of the table to be altered
     * @param alterColumns the columns to be altered, represented as an array of lists of strings
     */
    @SafeVarargs
    public final void alter(String tableName, List<String>... alterColumns) {
        database.alter(tableName, alterColumns);
    }

    /**
     * Alters an existing table in the database.
     *
     * @param name   the name of the table to be renamed
     * @param newName the new name of the table
     */
    public void alter(String name, String newName) {
        database.alter(name, newName);
    }

    /**
     * Drops (deletes) a table from the database.
     *
     * @param tableName the name of the table to be dropped
     */
    public void drop(String tableName) {
        database.drop(tableName);
    }
}
