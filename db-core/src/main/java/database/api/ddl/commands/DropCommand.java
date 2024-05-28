package database.api.ddl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

public class DropCommand extends Command {

    public DropCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor) {
        super(databaseAPI, databaseEditor);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
