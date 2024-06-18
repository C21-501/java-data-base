package database.api.ddl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

/**
 * The DropCommand class represents a command to drop (delete) a table from the database.
 * It extends the Command class and overrides the execute method to perform the drop operation.
 */
public final class DropCommand extends Command {
    private final String name;
    private final boolean isDatabase;

    /**
     * Constructs a new DropCommand instance.
     *
     * @param databaseAPI    the database API instance to interact with the database
     * @param databaseEditor the database editor instance to perform the drop operation
     * @param name           the name of the table to be dropped
     * @param isDatabase     the flag of structure to be dropped
     */
    public DropCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String name,
            boolean isDatabase
    ) {
        super(databaseAPI, databaseEditor);
        this.name = name;
        this.isDatabase = isDatabase;
    }

    /**
     * Executes the drop command.
     * Saves a backup before performing the drop operation using the database editor's DDL manager.
     *
     * @return true if the command changes state of database successfully
     */
    @Override
    public boolean execute() {
        saveBackup();
        if (isDatabase){
            databaseEditor.dropDatabase(name);
            return false;
        } else {
            databaseEditor.getDdlManager().drop(name);
            return true;
        }
    }
}
