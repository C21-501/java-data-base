package database.api.tcl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

/**
 * The BeginCommand class represents a command to begin a transaction in the database.
 * It extends the Command class and overrides the execute method to perform the begin transaction operation.
 */
public final class BeginCommand extends Command {

    /**
     * Constructs a new BeginCommand instance.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the begin transaction operation
     */
    public BeginCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor
    ) {
        super(databaseAPI, databaseEditor);
    }

    /**
     * Executes the begin transaction command.
     * Starts a new transaction using the database editor's TCL manager.
     *
     * @return true if the command changes state of database successfully
     */
    @Override
    public boolean execute() {
        databaseEditor.getTclManager().begin();
        return true;
    }
}
