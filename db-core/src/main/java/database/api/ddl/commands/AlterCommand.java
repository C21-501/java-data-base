package database.api.ddl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import database.system.core.exceptions.DatabaseIOException;

import java.util.List;

/**
 * The AlterCommand class represents a command to alter a table in the database.
 * It extends the Command class and overrides the execute method to perform the alter operation.
 */
public final class AlterCommand extends Command {
    private final String name;
    private final List<String>[] alterColumns;
    private String newName = null;
    private final boolean isDatabase;

    /**
     * Constructs a new AlterCommand instance.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the alterations
     * @param name       the name of the table to be altered
     * @param alterColumns    the columns to be altered, represented as an array of lists of strings
     */
    @SafeVarargs
    public AlterCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String name,
            List<String>... alterColumns
    ) {
        super(databaseAPI, databaseEditor);
        this.name = name;
        this.alterColumns = alterColumns;
        isDatabase = false;
    }

    public AlterCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String name,
            String newName,
            boolean isDatabase) {
        super(databaseAPI, databaseEditor);
        this.name = name;
        this.newName = newName;
        this.isDatabase = isDatabase;
        this.alterColumns = null;
    }

    /**
     * Executes the alter command.
     * Saves a backup before performing the alteration using the database editor's DDL manager.
     *
     * @return true if the command changes state of database successfully
     */
    @Override
    public boolean execute() throws DatabaseIOException {
        saveBackup();
        if (this.newName==null)
            databaseEditor.getDdlManager().alter(name, alterColumns);
        else if (!isDatabase)
            databaseEditor.getDdlManager().alter(name, newName);
        else
            databaseEditor.renameDatabase(name, newName);
        return true;
    }
}
