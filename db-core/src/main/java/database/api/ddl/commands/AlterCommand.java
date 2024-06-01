package database.api.ddl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import lombok.Data;

import java.util.List;


public class AlterCommand extends Command {
    private final String tableName;
    private final List<String>[] alterColumns;


    @SafeVarargs
    public AlterCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            List<String>... alterColumns
    ) {
        super(databaseAPI,databaseEditor);
        this.tableName = tableName;
        this.alterColumns = alterColumns;
    }

    @Override
    public boolean execute() {
        saveBackup();
        databaseEditor
                .getDdlManager()
                .alter(tableName, alterColumns);
        return true;
    }
}
