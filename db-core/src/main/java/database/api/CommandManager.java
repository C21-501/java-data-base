package database.api;

import database.api.ddl.DDLManager;
import database.api.ddl.commands.AlterCommand;
import database.api.ddl.commands.CreateCommand;
import database.api.ddl.commands.DropCommand;
import database.api.dml.DMLManager;
import database.api.dml.commands.DeleteCommand;
import database.api.dml.commands.InsertCommand;
import database.api.dml.commands.SelectCommand;
import database.api.dml.commands.UpdateCommand;
import database.api.tcl.TCLManager;
import database.api.tcl.commands.BeginCommand;
import database.api.tcl.commands.CommitCommand;
import database.api.tcl.commands.RollBackCommand;

public class CommandManager {
    private final DDLManager ddlManager = new DDLManager();
    private final DMLManager dmlManager = new DMLManager();
    private final TCLManager tclManager = new TCLManager();

    public void executeCommands() {
        // Example usage of DDL commands
        AlterCommand alterCommand = new AlterCommand();

        CreateCommand createCommand = new CreateCommand();

        DropCommand dropCommand = new DropCommand();

        // Example usage of DML commands
        DeleteCommand deleteCommand = new DeleteCommand();

        InsertCommand insertCommand = new InsertCommand();

        SelectCommand selectCommand = new SelectCommand();

        UpdateCommand updateCommand = new UpdateCommand();

        // Example usage of TCL commands
        BeginCommand beginCommand = new BeginCommand();

        CommitCommand commitCommand = new CommitCommand();

        RollBackCommand rollBackCommand = new RollBackCommand();
    }
}
