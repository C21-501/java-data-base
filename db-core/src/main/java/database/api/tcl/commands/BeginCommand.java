package database.api.tcl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

public class BeginCommand extends Command {


    public BeginCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor
    ) {
        super(databaseAPI, databaseEditor);
    }

    @Override
    public boolean execute() {
        databaseEditor.getTclManager().begin();
        return true;
    }
}
