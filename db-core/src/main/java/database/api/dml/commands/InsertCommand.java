package database.api.dml.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import database.system.core.exceptions.DatabaseIOException;

import java.io.IOException;
import java.util.List;

/**
 * The InsertCommand class represents a command to insert records into a table in the database.
 * It extends the Command class and overrides the execute method to perform the insert operation.
 */
public final class InsertCommand extends Command {
    private final String tableName;
    private final List<String> columns;
    private final List<Object[]> values;

    /**
     * Constructs a new InsertCommand instance.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the insert operation
     * @param tableName       the name of the table into which records will be inserted
     * @param columns         the columns into which values will be inserted, represented as a list of strings
     * @param values          the values to be inserted, represented as a list of object arrays
     */
    public InsertCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            List<String> columns,
            List<Object[]> values
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.columns = columns;
        this.values = values;
    }

    /**
     * Executes the insert command.
     * Saves a backup before performing the insert operation using the database editor's DML manager.
     *
     * @return true if the command changes state of database successfully
     * @throws IOException if an I/O error occurs during the operation
     */
    @Override
    public boolean execute() throws DatabaseIOException {
        saveBackup();
        databaseEditor
                .getDmlManager()
                .insert(tableName, columns, values);
        return true;
    }
}
