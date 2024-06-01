package database.api;

import database.api.ddl.commands.AlterCommand;
import database.api.ddl.commands.CreateCommand;
import database.api.ddl.commands.DropCommand;
import database.api.dml.commands.DeleteCommand;
import database.api.dml.commands.InsertCommand;
import database.api.dml.commands.SelectCommand;
import database.api.dml.commands.UpdateCommand;
import database.system.core.structures.Response;
import lombok.Data;

import java.io.IOException;
import java.util.List;

/**
 * The DatabaseAPI class provides an interface for interacting with the database.
 * It allows executing Data Definition Language (DDL) and Data Manipulation Language (DML) commands,
 * managing command history, and undoing commands.
 */
@Data
public class DatabaseAPI {
    private List<DatabaseEditor> editors;
    private DatabaseEditor activeEditor;
    private CommandHistory history;
    private Response lastResponse;

    /**
     * Executes a CREATE command to create a new table in the database.
     *
     * @param tableName the name of the table to create
     * @param columns   the list of column names for the new table
     * @throws IOException if an I/O error occurs during the execution
     */
    synchronized public void create(String tableName, List<String> columns) throws IOException {
        executeCommand(new CreateCommand(this, activeEditor, tableName, columns));
    }

    /**
     * Executes an ALTER command to modify an existing table in the database.
     * The method allows adding new columns, modifying existing columns, and deleting columns from the table.
     *
     * @param tableName    the name of the table to modify
     * @param alterColumns an array of lists representing modifications to the table columns
     *                     <p>
     *                     <strong>Variations:</strong>
     *                     <ol>
     *                     <li>If one list is provided, it represents the new columns to be added to the table.</li>
     *                     <li>If two lists are provided:
     *                     <ul>
     *                     <li>The first list represents the modified columns.</li>
     *                     <li>The second list represents the new values for those columns.</li>
     *                     </ul>
     *                     </li>
     *                     <li>If three lists are provided:
     *                     <ul>
     *                     <li>The first list represents the modified columns.</li>
     *                     <li>The second list represents the new values for those columns.</li>
     *                     <li>The third list represents the columns to be deleted from the table.</li>
     *                     </ul>
     *                     </li>
     *                     </ol>
     * @throws IOException if an I/O error occurs during the execution
     */
    synchronized public void alter(String tableName, List<String>... alterColumns) throws IOException {
        executeCommand(new AlterCommand(this, activeEditor, tableName, alterColumns));
    }


    /**
     * Executes a DROP command to delete a table from the database.
     *
     * @param tableName the name of the table to delete
     * @throws IOException if an I/O error occurs during the execution
     */
    synchronized public void drop(String tableName) throws IOException {
        executeCommand(new DropCommand(this, activeEditor, tableName));
    }

    /**
     * Executes a DELETE command to remove records from a table in the database.
     *
     * @param tableName the name of the table from which records will be deleted
     * @param condition the condition to filter which records are deleted
     * @throws IOException if an I/O error occurs during the execution
     */
    synchronized public void delete(String tableName, String condition) throws IOException {
        executeCommand(new DeleteCommand(this, activeEditor, tableName, condition));
    }

    /**
     * Executes an INSERT command to add records to a table in the database.
     *
     * @param tableName the name of the table to insert records into
     * @param columns   the list of column names for the records
     * @param values    the list of values to be inserted into the columns
     * @throws IOException if an I/O error occurs during the execution
     */
    synchronized public void insert(String tableName, List<String> columns, List<Object[]> values) throws IOException {
        executeCommand(new InsertCommand(this, activeEditor, tableName, columns, values));
    }

    /**
     * Executes a SELECT command to retrieve records from a table in the database.
     *
     * @param tableName the name of the table from which records will be retrieved
     * @param columns   the list of column names to select from the table
     * @param condition the condition to filter which records are selected
     * @throws IOException if an I/O error occurs during the execution
     */
    synchronized public void select(String tableName, List<String> columns, String condition) throws IOException {
        executeCommand(new SelectCommand(this, activeEditor, tableName, columns, condition));
    }

    /**
     * Executes a SELECT command to retrieve all records from a table in the database.
     *
     * @param tableName the name of the table from which all records will be retrieved
     * @param columns   the list of column names to select from the table
     * @throws IOException if an I/O error occurs during the execution
     */
    synchronized public void select(String tableName, List<String> columns) throws IOException {
        executeCommand(new SelectCommand(this, activeEditor, tableName, columns));
    }

    /**
     * Executes an UPDATE command to modify records in a table in the database.
     *
     * @param tableName the name of the table in which records will be updated
     * @param value     the new value to be set in the records
     * @param condition the condition to determine which records to update
     * @throws IOException if an I/O error occurs during the execution
     */
    synchronized public void update(String tableName, Object value, String condition) throws IOException {
        executeCommand(new UpdateCommand(this, activeEditor, tableName, value, condition));
    }

    private void executeCommand(Command command) throws IOException {
        if (command.execute()) {
            history.push(command);
        }
    }

    /**
     * Undoes the last executed command by popping it from the history and calling its undo method.
     *
     * @throws IOException            if an I/O error occurs during the execution
     * @throws ClassNotFoundException if the class of a serialized object could not be found while undoing
     */
    synchronized public void undo() throws IOException, ClassNotFoundException {
        Command command = history.pop();
        if (command != null) {
            command.undo();
        }
    }
}
