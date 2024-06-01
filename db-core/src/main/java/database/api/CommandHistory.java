package database.api;

import java.util.Stack;

/**
 * The CommandHistory class represents a history of executed commands in the database.
 * It uses a stack data structure to store the command history.
 */
public class CommandHistory {
    private final Stack<Command> history = new Stack<>();

    /**
     * Pushes a command onto the command history stack.
     *
     * @param command the command to be added to the history
     */
    public void push(Command command) {
        history.push(command);
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
     * Size of a history command stack.
     *
     * @return size of the stack
     */
    public int size() {
        return history.size();
    }
}
