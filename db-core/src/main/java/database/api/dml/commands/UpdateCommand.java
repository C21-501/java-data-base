package database.api.dml.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

public class UpdateCommand extends Command {


    public UpdateCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor) {
        super(databaseAPI, databaseEditor);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
