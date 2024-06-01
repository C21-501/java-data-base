package database.api;

import java.io.IOException;

/**
 * The Command class represents an abstract command to be executed on the database.
 * It provides methods to save a database state as backup, undo a command, and execute the command.
 */
public abstract class Command {
    protected DatabaseAPI databaseAPI;
    protected DatabaseEditor databaseEditor;

    /**
     * Constructs a new Command instance.
     *
     * @param databaseAPI    the database API instance to interact with the database
     * @param databaseEditor the database editor instance to execute the command
     */
    public Command(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor) {
        this.databaseAPI = databaseAPI;
        this.databaseEditor = databaseEditor;
    }

    /**
     * Saves the current database state as a backup.
     */
    public void saveBackup() {
        databaseEditor.saveDatabaseState();
    }

    /**
     * Restores the database state from the backup.
     *
     * @throws IOException            if an I/O error occurs while restoring the database state
     * @throws ClassNotFoundException if the class of a serialized object could not be found while restoring
     */
    public void undo() throws IOException, ClassNotFoundException {
        databaseEditor.restoreDatabaseState();
    }

    /**
     * Executes the command.
     *
     * @return true if the command executes successfully
     * @throws IOException if an I/O error occurs during the execution
     */
    public abstract boolean execute() throws IOException;
}
