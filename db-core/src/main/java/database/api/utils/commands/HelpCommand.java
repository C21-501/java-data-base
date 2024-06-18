package database.api.utils.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import database.api.utils.UtilManager;
import database.monitor.Config;
import database.system.core.exceptions.DatabaseIOException;

import java.io.IOException;
import java.util.Optional;

/**
 * This class represents a help command that provides descriptions and examples of available commands.
 * The help information is retrieved from the {@link UtilManager}.
 */
@SuppressWarnings("ALL")
public class HelpCommand extends Command {
    private final Optional<String> command;

    /**
     * Constructs a new HelpCommand instance.
     *
     * @param databaseAPI    the database API instance to interact with the database
     * @param databaseEditor the database editor instance to execute the command
     * @param command        an optional command name for which help is requested; if not present, help for all commands is displayed
     */
    public HelpCommand(DatabaseAPI databaseAPI, DatabaseEditor databaseEditor, Optional<String> command) {
        super(databaseAPI, databaseEditor);
        this.command = command;
    }

    /**
     * Executes the help command.
     * If a specific command name is provided, displays help for that command.
     * Otherwise, displays help for all available commands.
     *
     * @return always returns {@code false} as this command does not modify the database
     * @throws IOException if an I/O error occurs while accessing help information
     */
    @Override
    public boolean execute() throws DatabaseIOException {
        if (command.isPresent())
            databaseEditor.getUtilManager()
                    .printCommandHelp(
                    command.get().toUpperCase(), Config.CURRENT_OUTPUT_TYPE, Config.CURRENT_OUTPUT_PATH
            );
        else
            databaseEditor.getUtilManager()
                    .printAllCommandsHelp(Config.CURRENT_OUTPUT_TYPE, Config.CURRENT_OUTPUT_PATH);
        return false;
    }
}
