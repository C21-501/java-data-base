package database.api.dml.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

public class DeleteCommand extends Command {
    private final String tableName;
    private final String condition;

    public DeleteCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            String condition
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.condition = condition;
    }

    @Override
    public boolean execute() {
        saveBackup();
        databaseEditor
                .getDmlManager()
                .delete(tableName, condition);
        return true;
    }
}
