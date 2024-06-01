package database.api.ddl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

import java.util.List;

public class CreateCommand extends Command {
    private final String tableName;
    private final List<String> columns;

    public CreateCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor, String tableName, List<String> columns) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.columns = columns;
    }

    @Override
    public boolean execute() {
        saveBackup();
        databaseEditor
                .getDdlManager()
                .create(tableName,columns);
        return true;
    }
}
