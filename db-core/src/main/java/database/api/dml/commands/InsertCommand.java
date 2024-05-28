package database.api.dml.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

import java.io.IOException;
import java.util.List;

class InsertCommand extends Command {
    private final String tableName;
    private final String[] columns;
    private final List<Object[]> values;

    public InsertCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor, String tableName, String[] columns, List<Object[]> values) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.columns = columns;
        this.values = values;
    }

    @Override
    public boolean execute() throws IOException {
        saveBackup();
        return true;
    }
}
