package database.api.dml;

import database.api.ddl.AbstractManager;
import database.system.core.structures.Database;
import database.system.core.structures.Response;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The DMLManager class manages Data Manipulation Language (DML) operations on the database.
 * It provides methods to insert, update, delete, and select records in the database.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class DMLManager extends AbstractManager {

    /**
     * Constructs a new DMLManager instance.
     *
     * @param database the database instance on which DML operations will be performed
     */
    public DMLManager(Database database)  {
        super(database);
    }

    /**
     * Inserts records into a table in the database.
     *
     * @param tableName the name of the table into which records will be inserted
     * @param columns   the columns into which values will be inserted, represented as a list of strings
     * @param values    the values to be inserted, represented as a list of object arrays
     */
    public void insert(String tableName, List<String> columns, List<Object[]> values) {
        validateDatabaseState();
        database.insert(tableName, columns, values);
    }

    /**
     * Updates records in a table in the database.
     *
     * @param tableName the name of the table in which records will be updated
     * @param values     the new values to be set in the records
     * @param condition the condition to determine which records to update
     */
    public void update(String tableName, List<String> values, String condition) {
        validateDatabaseState();
        database.update(tableName, values, condition);
    }

    /**
     * Deletes records from a table in the database.
     *
     * @param tableName the name of the table from which records will be deleted
     * @param condition the condition to determine which records to delete
     */
    public void delete(String tableName, String condition) {
        validateDatabaseState();
        database.delete(tableName, condition);
    }

    /**
     * Selects records from a table in the database with a condition.
     *
     * @param tableName the name of the table from which records will be selected
     * @param columns   the columns to be selected, represented as a list of strings
     * @param condition the condition to filter which records are selected
     * @return a Response object containing the selected records
     */
    public Response select(String tableName, List<String> columns, String condition) {
        validateDatabaseState();
        return database.select(tableName, columns, condition);
    }

    /**
     * Selects records from a table in the database without a condition.
     *
     * @param tableName the name of the table from which records will be selected
     * @param columns   the columns to be selected, represented as a list of strings
     * @return a Response object containing the selected records
     */
    public Response select(String tableName, List<String> columns) {
        validateDatabaseState();
        return database.select(tableName, columns);
    }

    /**
     * Selects all records from a table in the database without a condition.
     *
     * @param tableName the name of the table from which records will be selected
     * @return a Response object containing the selected records
     */
    public Response select(String tableName) {
        validateDatabaseState();
        return database.select(tableName);
    }
}
