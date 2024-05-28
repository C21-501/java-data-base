package database.api.ddl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

public class AlterCommand extends Command {
    private final String tableName;
    private final String columnName;
    private final String newColumnName;

    public AlterCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor, String tableName, String columnName, String newColumnName) {
        super(databaseAPI,databaseEditor);
        this.tableName = tableName;
        this.columnName = columnName;
        this.newColumnName = newColumnName;
    }

    @Override
    public boolean execute() {
        return false;
    }
}
