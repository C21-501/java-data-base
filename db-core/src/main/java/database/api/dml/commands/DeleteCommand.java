package database.api.dml.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

/**
 * The DeleteCommand class represents a command to delete records from a table in the database.
 * It extends the Command class and overrides the execute method to perform the delete operation.
 */
public class DeleteCommand extends Command {
    private final String tableName;
    private final String condition;

    /**
     * Constructs a new DeleteCommand instance.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the delete operation
     * @param tableName       the name of the table from which records will be deleted
     * @param condition       the condition to determine which records to delete
     */
    public DeleteCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            String condition
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.condition = condition;
    }

    /**
     * Executes the delete command.
     * Saves a backup before performing the delete operation using the database editor's DML manager.
     *
     * @return true if the command changes state of database successfully
     */
    @Override
    public boolean execute() {
        saveBackup();
        databaseEditor
                .getDmlManager()
                .delete(tableName, condition);
        return true;
    }
}
