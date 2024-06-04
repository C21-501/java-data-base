package database.api.tcl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

/**
 * The RollBackCommand class represents a command to rollback a transaction in the database.
 * It extends the Command class and overrides the execute method to perform the rollback operation.
 */
public final class RollBackCommand extends Command {
    /**
     * Constructs a new RollBackCommand instance.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the rollback operation
     */
    public RollBackCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor
    ) {
        super(databaseAPI, databaseEditor);
    }

    /**
     * Executes the rollback transaction command.
     * Rolls back the current transaction using the database editor's TCL manager.
     *
     * @return true if the command changes state of database successfully
     */
    @Override
    public boolean execute() {
        databaseEditor.getTclManager().rollback();
        return true;
    }
}
