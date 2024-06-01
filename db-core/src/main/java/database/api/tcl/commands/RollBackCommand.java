package database.api.tcl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

public class RollBackCommand extends Command {
    public RollBackCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor
    ) {
        super(databaseAPI, databaseEditor);
    }

    @Override
    public boolean execute() {
        databaseEditor.getTclManager().rollback();
        return false;
    }
}
