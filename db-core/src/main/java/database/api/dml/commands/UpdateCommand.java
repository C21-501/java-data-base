package database.api.dml.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

public class UpdateCommand extends Command {
    private final String tableName;
    private final Object value;
    private final String condition;

    public UpdateCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            Object value,
            String condition
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.value = value;
        this.condition = condition;
    }

    @Override
    public boolean execute() {
        saveBackup();
        databaseEditor
                .getDmlManager()
                .update(tableName,value,condition);
        return true;
    }
}
