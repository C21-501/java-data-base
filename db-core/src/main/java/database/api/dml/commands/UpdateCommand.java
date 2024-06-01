package database.api.dml.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

/**
 * The UpdateCommand class represents a command to update records in a table in the database.
 * It extends the Command class and overrides the execute method to perform the update operation.
 */
public class UpdateCommand extends Command {
    private final String tableName;
    private final Object value;
    private final String condition;

    /**
     * Constructs a new UpdateCommand instance.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the update operation
     * @param tableName       the name of the table in which records will be updated
     * @param value           the new value to be set in the records
     * @param condition       the condition to determine which records to update
     */
    public UpdateCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            Object value,
            String condition
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.value = value;
        this.condition = condition;
    }

    /**
     * Executes the update command.
     * Saves a backup before performing the update operation using the database editor's DML manager.
     *
     * @return true if the command changes state of database successfully
     */
    @Override
    public boolean execute() {
        saveBackup();
        databaseEditor
                .getDmlManager()
                .update(tableName, value, condition);
        return true;
    }
}
