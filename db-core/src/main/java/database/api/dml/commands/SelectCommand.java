package database.api.dml.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

import java.util.List;

public class SelectCommand extends Command {

    private final String tableName;
    private final List<String> columns;
    private String condition;

    public SelectCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            List<String> columns,
            String condition
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.columns = columns;
        this.condition = condition;
    }

    public SelectCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            List<String> columns
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.columns = columns;
    }

    @Override
    public boolean execute() {
        if (condition == null)
            databaseEditor.getDmlManager()
                .select(tableName,columns);
        databaseEditor.getDmlManager()
                .select(tableName,columns,condition);
        return false;
    }
}
