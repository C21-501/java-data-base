package database.api.help.commands;

import database.api.Command;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import database.api.help.HELPManager;

import java.io.IOException;
import java.util.Optional;

/**
 * This class represents a help command that provides descriptions and examples of available commands.
 * The help information is retrieved from the {@link HELPManager}.
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
    public boolean execute() throws IOException {
        if (command.isPresent())
            databaseEditor.getHelpManager().printCommandHelp(command.get().toUpperCase());
        else
            databaseEditor.getHelpManager().printAllCommandsHelp();
        return false;
    }
}
