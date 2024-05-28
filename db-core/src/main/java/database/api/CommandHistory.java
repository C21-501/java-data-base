package database.api;

import database.api.Command;

import java.util.Stack;

public class CommandHistory {
    private final Stack<Command> history = new Stack<>();

    public void push(Command command) {
        history.push(command);
    }

    public Command pop() {
        return history.isEmpty() ? null : history.pop();
    }
}
