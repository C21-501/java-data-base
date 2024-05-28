package database.api.dml.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

public class SelectCommand extends Command {

    public SelectCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor) {
        super(databaseAPI, databaseEditor);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
