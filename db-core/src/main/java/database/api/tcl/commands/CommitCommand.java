package database.api.tcl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import database.system.core.exceptions.DatabaseIOException;

/**
 * The CommitCommand class represents a command to commit a transaction in the database.
 * It extends the Command class and overrides the execute method to perform the commit operation.
 */
public final class CommitCommand extends Command {

    /**
     * Constructs a new CommitCommand instance.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the commit operation
     */
    public CommitCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor
    ) {
        super(databaseAPI, databaseEditor);
    }

    /**
     * Executes the commit transaction command.
     * Commits the current transaction using the database editor's TCL manager.
     *
     * @return true if the command changes state of database successfully
     */
    @Override
    public boolean execute() throws DatabaseIOException {
        saveBackup();
        databaseEditor.getTclManager().commit();
        databaseEditor.saveDatabaseState();
        return false;
    }
}
