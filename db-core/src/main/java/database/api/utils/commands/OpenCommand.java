package database.api.utils.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class OpenCommand extends Command {
    private final String databaseName;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<String> databasePath;
    /**
     * Constructs a new OpenCommand instance.
     *
     * @param databaseAPI    the database API instance to interact with the database
     * @param databaseEditor the database editor instance to execute the command
     * @param databaseName   the name of database
     */
    public OpenCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor, String databaseName, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<String> databasePath) {
        super(databaseAPI, databaseEditor);
        if (Objects.isNull(databaseName))
            throw new NullPointerException("Error: database name is null.");
        this.databaseName = databaseName;
        this.databasePath = databasePath;
    }

    @Override
    public boolean execute() throws IOException {
        if (databasePath.isPresent())
            databaseEditor.getUtilManager().openDatabase(databaseName, databasePath.get());
        else
            databaseEditor.getUtilManager().openDatabase(databaseName);
        return false;
    }
}
