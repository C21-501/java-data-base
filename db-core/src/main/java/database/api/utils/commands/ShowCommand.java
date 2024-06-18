package database.api.utils.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import database.monitor.Config;

import java.io.IOException;
import java.util.Optional;

public class ShowCommand  extends Command {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<String> databasePath;

    /**
     * Constructs a new Command instance.
     *
     * @param databaseAPI    the database API instance to interact with the database
     * @param databaseEditor the database editor instance to execute the command
     */
    public ShowCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor) {
        super(databaseAPI, databaseEditor);
    }

    public ShowCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<String> databasePath) {
        super(databaseAPI, databaseEditor);
        this.databasePath = databasePath;
    }

    @Override
    public boolean execute() throws IOException {
        databasePath.ifPresentOrElse(
                string -> databaseEditor.getUtilManager()
                        .showAvailableDatabases(
                                string,
                                Config.CURRENT_OUTPUT_TYPE,
                                Config.CURRENT_OUTPUT_PATH
                        ),
                () -> databaseEditor.getUtilManager()
                        .showAvailableTables(
                                Config.CURRENT_OUTPUT_TYPE,
                                Config.CURRENT_OUTPUT_PATH
                        )
        );
        return false;
    }
}
