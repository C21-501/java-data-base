package database.api.ddl.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

import java.util.List;
import java.util.Optional;

/**
 * The CreateCommand class represents a command to create a new table or database in the database system.
 * It extends the Command class and overrides the execute method to perform the creation operation.
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public final class CreateCommand extends Command {
    private final String tableName;
    private final List<String> columns;
    private final String databaseName;
    private final Optional<String> databasePath;

    /**
     * Constructs a new CreateCommand instance to create a new table.
     *
     * @param databaseAPI    the database API instance to interact with the database
     * @param databaseEditor the database editor instance to perform the creation
     * @param tableName      the name of the table to be created
     * @param columns        the columns to be included in the new table, represented as a list of strings
     */
    public CreateCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            List<String> columns
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.columns = columns;
        this.databaseName = null;
        this.databasePath = Optional.empty();
    }

    /**
     * Constructs a new CreateCommand instance to create a new database.
     *
     * @param databaseAPI    the database API instance to interact with the database
     * @param databaseEditor the database editor instance to perform the creation
     * @param databaseName   the name of the database to be created
     * @param databasePath   the optional path where the database will be stored
     */
    public CreateCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String databaseName,
            Optional<String> databasePath
    ) {
        super(databaseAPI, databaseEditor);
        this.databaseName = databaseName;
        this.databasePath = databasePath;
        this.columns = null;
        this.tableName = null;
    }

    /**
     * Executes the create command.
     * Saves a backup before performing the creation using the database editor's DDL manager.
     * If the command is for creating a table, it uses the DDL manager to create the table.
     * If the command is for creating a database, it uses the database editor to create the database.
     *
     * @return true if the command changes the state of the database successfully
     */
    @Override
    public boolean execute() {
        saveBackup();
        if (databaseName != null && columns == null && tableName == null) {
            databasePath.ifPresent(path -> databaseEditor.createDatabase(databaseName, path));
            if (databasePath.isEmpty()) {
                databaseEditor.createDatabase(databaseName);
            }
        } else {
            databaseEditor.getDdlManager().create(tableName, columns);
        }
        return true;
    }
}
