package database.api;

import database.system.core.exceptions.DatabaseIOException;

import java.util.Stack;

/**
 * The CommandHistory class represents a history of executed commands in the database.
 * It uses a stack data structure to store the command history with a maximum size limit.
 */
public class CommandHistory {
    private static final int MAX_SIZE = 10;
    private final Stack<Command> history = new Stack<>();

    /**
     * Pushes a command onto the command history stack.
     *
     * @param command the command to be added to the history
     */
    public void push(Command command) {
        history.push(command);
        if (history.size() > MAX_SIZE) {
            overwriteStack();
        }
    }

    /**
     * Pops a command from the command history stack.
     *
     * @return the popped command, or null if the history is empty
     */
    public Command pop() {
        return history.isEmpty() ? null : history.pop();
    }

    /**
     * Returns the size of the command history stack.
     *
     * @return the number of commands stored in the history stack
     */
    public int size() {
        return history.size();
    }

    /**
     * Overwrites the oldest commands in the stack when it exceeds the maximum size.
     * It saves the database state associated with the removed commands.
     */
    private void overwriteStack() {
        while (history.size() > MAX_SIZE) {
            Command removedCommand = history.removeFirst();
            try {
                removedCommand.databaseEditor.saveDatabaseState();
            } catch (DatabaseIOException e){
                throw new RuntimeException("Error: can't restore database instance: %s%n".formatted(e.getMessage()));
            }
        }
    }
}