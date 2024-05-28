package database.api.tcl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

public class CommitCommand extends Command {

    public CommitCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor) {
        super(databaseAPI, databaseEditor);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
