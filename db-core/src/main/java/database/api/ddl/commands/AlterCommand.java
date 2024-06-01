package database.api.ddl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

import java.util.List;

/**
 * The AlterCommand class represents a command to alter a table in the database.
 * It extends the Command class and overrides the execute method to perform the alter operation.
 */
public class AlterCommand extends Command {
    private final String tableName;
    private final List<String>[] alterColumns;

    /**
     * Constructs a new AlterCommand instance.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the alterations
     * @param tableName       the name of the table to be altered
     * @param alterColumns    the columns to be altered, represented as an array of lists of strings
     */
    @SafeVarargs
    public AlterCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            List<String>... alterColumns
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.alterColumns = alterColumns;
    }

    /**
     * Executes the alter command.
     * Saves a backup before performing the alteration using the database editor's DDL manager.
     *
     * @return true if the command changes state of database successfully
     */
    @Override
    public boolean execute() {
        saveBackup();
        databaseEditor
                .getDdlManager()
                .alter(tableName, alterColumns);
        return true;
    }
}
