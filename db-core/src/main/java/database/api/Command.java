package database.api;

import database.system.core.structures.Database;

import java.io.IOException;

public abstract class Command {
    protected DatabaseAPI databaseAPI;
    protected DatabaseEditor databaseEditor;
//    protected Database backup = databaseEditor.getDatabase();

    public Command(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor) {
        this.databaseAPI = databaseAPI;
        this.databaseEditor = databaseEditor;
    }

    // Save current database state as backup
    public void saveBackup() {
        databaseEditor.saveDatabaseState();
    }

    // Restore database state from backup
    public void undo() throws IOException, ClassNotFoundException {
        databaseEditor.restoreDatabaseState();
    }

    // Execute the command
    public abstract boolean execute() throws IOException;
}
