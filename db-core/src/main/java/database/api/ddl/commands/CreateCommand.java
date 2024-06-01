package database.api.ddl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

import java.util.List;

/**
 * The CreateCommand class represents a command to create a new table in the database.
 * It extends the Command class and overrides the execute method to perform the creation operation.
 */
public class CreateCommand extends Command {
    private final String tableName;
    private final List<String> columns;

    /**
     * Constructs a new CreateCommand instance.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the creation
     * @param tableName       the name of the table to be created
     * @param columns         the columns to be included in the new table, represented as a list of strings
     */
    public CreateCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor, String tableName, List<String> columns) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.columns = columns;
    }

    /**
     * Executes the create command.
     * Saves a backup before performing the creation using the database editor's DDL manager.
     *
     * @return true if the command changes state of database successfully
     */
    @Override
    public boolean execute() {
        saveBackup();
        databaseEditor
                .getDdlManager()
                .create(tableName, columns);
        return true;
    }
}
