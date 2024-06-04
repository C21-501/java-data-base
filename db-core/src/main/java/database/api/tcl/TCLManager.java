package database.api.tcl;

import database.api.Command;
import database.api.tcl.commands.BeginCommand;
import database.api.tcl.commands.CommitCommand;
import database.api.tcl.commands.RollBackCommand;
import database.system.core.structures.Database;
import lombok.Data;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The TCLManager class manages Transaction Control Language (TCL) operations on the database.
 * It provides methods to begin, commit, and rollback transactions, and add commands to a transaction.
 */
@Data
public class TCLManager {
    private Database database;
    private String transactionFile;
    private boolean transactionActive;
    private Queue<Command> commandQueue;

    /**
     * Constructs a new TCLManager instance.
     *
     * @param database        the database instance to manage transactions
     * @param transactionFile the file path for storing transaction backups
     */
    public TCLManager(Database database, String transactionFile) {
        this.database = database;
        this.transactionFile = transactionFile;
        this.transactionActive = false;
        this.commandQueue = new LinkedList<>();
    }

    /**
     * Begins a new transaction.
     *
     * @throws RuntimeException if a transaction is already in progress or an error occurs while starting the transaction
     */
    public void begin() {
        if (transactionActive) {
            throw new RuntimeException("Error: Transaction already in progress.");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.transactionFile))) {
            oos.writeObject(database);
            transactionActive = true;
            commandQueue.clear();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error while starting transaction: %s%n", e.getMessage()));
        }
    }

    /**
     * Commits the current transaction.
     *
     * @throws RuntimeException if no active transaction is found or an error occurs while committing the transaction
     */
    public void commit() {
        if (!transactionActive) {
            throw new RuntimeException("Error: No active transaction to commit.");
        }
        try {
            if (commandQueue.isEmpty())
                throw new RuntimeException("Error: command queue is empty, no command passed.");
            // Execute all commands in the queue
            while (!commandQueue.isEmpty()) {
                commandQueue.poll().execute();
            }
            database.saveState(this.transactionFile); // Assuming saveState method exists in Database class
            File transactionFile = new File(this.transactionFile);
            if (transactionFile.exists() && !transactionFile.delete()) {
                System.err.println("Warning: Could not delete transaction backup file.");
            }
            transactionActive = false;
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while committing transaction: %s%n", e.getMessage()));
        }
    }

    /**
     * Rolls back the current transaction.
     *
     * @throws RuntimeException if no active transaction is found or an error occurs while rolling back the transaction
     */
    public void rollback() {
        if (!transactionActive) {
            throw new RuntimeException("Error: No active transaction to roll back.");
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.transactionFile))) {
            Database backupDatabase = (Database) ois.readObject();
            database.restore(backupDatabase); // Assuming restore method exists in Database class
            transactionActive = false;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(String.format("Error while rolling back transaction: %s%n", e.getMessage()));
        }
    }

    /**
     * Adds a command to the current transaction.
     *
     * @param command the command to be added to the transaction
     * @throws RuntimeException if no active transaction is found
     */
    public void addCommand(Command command) {
        if (!transactionActive) {
            throw new RuntimeException("Error: No active transaction. Cannot add command.");
        }
        if (command instanceof BeginCommand || command instanceof CommitCommand || command instanceof RollBackCommand) {
            throw new RuntimeException(String.format("The TCL command %s can't be executed inside other transaction block", command.getClass().getSimpleName()));
        }
        commandQueue.add(command);
    }
}
