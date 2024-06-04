package database.api;

import database.system.core.structures.Database;
import database.system.core.structures.Table;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Command class represents an abstract command to be executed on the database.
 * It provides methods to save a database state as backup, undo a command, and execute the command.
 */
public abstract class Command {
    protected DatabaseAPI databaseAPI;
    protected DatabaseEditor databaseEditor;
    protected Map<String, Table> backup = new TreeMap<>();;

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
        backup = new TreeMap<>();
        for (Map.Entry<String, Table> entry : databaseEditor.getDatabase().getTables().entrySet()) {
            String key = entry.getKey();
            Table originalTable = entry.getValue();
            Table copiedTable = new Table(originalTable);
            backup.put(key, copiedTable);
        }
    }

    /**
     * Restores the database state from the backup.
     *
     */
    public void undo() {
        databaseEditor.restoreDatabaseStateFromBackup(backup);
    }

    /**
     * Executes the command.
     *
     * @return true if the command executes successfully
     * @throws IOException if an I/O error occurs during the execution
     */
    public abstract boolean execute() throws IOException;
}
