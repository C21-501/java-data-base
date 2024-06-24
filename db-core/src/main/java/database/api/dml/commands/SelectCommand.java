package database.api.dml.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import database.system.core.exceptions.DatabaseIOException;

import java.util.List;
import java.util.Optional;

/**
 * The SelectCommand class represents a command to select records from a table in the database.
 * It extends the Command class and overrides the execute method to perform the select operation.
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public final class SelectCommand extends Command {

    private final String tableName;
    private final Optional<List<String>> columns;
    private final Optional<String> condition;

    /**
     * Constructs a new SelectCommand instance with tableName only.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the select operation
     * @param tableName       the name of the table from which records will be selected
     */
    public SelectCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.columns = Optional.empty();
        this.condition = Optional.empty();
    }

    /**
     * Constructs a new SelectCommand instance with columns.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the select operation
     * @param tableName       the name of the table from which records will be selected
     * @param columns         the columns to be selected, represented as a list of strings
     */
    public SelectCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            List<String> columns
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.columns = Optional.of(columns);
        this.condition = Optional.empty();
    }

    /**
     * Constructs a new SelectCommand instance with columns and condition.
     *
     * @param databaseAPI     the database API instance to interact with the database
     * @param databaseEditor  the database editor instance to perform the select operation
     * @param tableName       the name of the table from which records will be selected
     * @param columns         the columns to be selected, represented as a list of strings
     * @param condition       the condition to filter which records are selected
     */
    public SelectCommand(
            DatabaseAPI databaseAPI,
            DatabaseEditor databaseEditor,
            String tableName,
            List<String> columns,
            String condition
    ) {
        super(databaseAPI, databaseEditor);
        this.tableName = tableName;
        this.columns = Optional.of(columns);
        this.condition = Optional.of(condition);
    }

    /**
     * Executes the select command.
     * If a condition is provided, selects records based on the condition using the database editor's DML manager.
     * Otherwise, selects all records.
     *
     * @return false if the command don't change state of database
     */
    @Override
    public boolean execute() throws DatabaseIOException {
        if (columns.isEmpty() && condition.isEmpty()) {
            databaseAPI.setLastSelectResponse(databaseEditor.getDmlManager().select(tableName));
        } else if (condition.isEmpty()) {
            databaseAPI.setLastSelectResponse(databaseEditor.getDmlManager().select(tableName, columns.get()));
        } else {
            databaseAPI.setLastSelectResponse(databaseEditor.getDmlManager().select(tableName, columns.orElse(List.of()), condition.get()));
        }
        return false;
    }
}
