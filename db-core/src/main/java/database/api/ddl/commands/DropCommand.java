package database.api.ddl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import lombok.Data;


public class DropCommand extends Command {
    private final String tableName;

    public DropCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
    }

    @Override
    public boolean execute() {
        saveBackup();
        databaseEditor
                .getDdlManager()
                .drop(tableName);
        return true;
    }
}
