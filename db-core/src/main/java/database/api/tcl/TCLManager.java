package database.api.tcl;

import database.system.core.structures.Database;
import lombok.Data;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

@Data
public class TCLManager {
    private Database database;
    private String transactionFile;
    private boolean transactionActive;
    private Queue<Runnable> commandQueue;

    public TCLManager(Database database, String transactionFile) {
        this.database = database;
        this.transactionFile = transactionFile;
        this.transactionActive = false;
        this.commandQueue = new LinkedList<>();
    }

    public void begin() {
        if (transactionActive) {
            System.err.println("Error: Transaction already in progress.");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.transactionFile))) {
            oos.writeObject(database);
            transactionActive = true;
            commandQueue.clear();
            System.out.println("Transaction started. Database state saved.");
        } catch (IOException e) {
            System.err.printf("Error while starting transaction: %s%n", e.getMessage());
        }
    }

    public void commit() {
        if (!transactionActive) {
            System.err.println("Error: No active transaction to commit.");
            return;
        }

        try {
            // Execute all commands in the queue
            while (!commandQueue.isEmpty()) {
                commandQueue.poll().run();
            }
            database.saveState(this.transactionFile); // Assuming saveState method exists in Database class
            File transactionFile = new File(this.transactionFile);
            if (transactionFile.exists() && !transactionFile.delete()) {
                System.err.println("Warning: Could not delete transaction backup file.");
            }
            transactionActive = false;
            System.out.println("Transaction committed. Changes saved.");
        } catch (Exception e) {
            System.err.printf("Error while committing transaction: %s%n", e.getMessage());
        }
    }

    public void rollback() {
        if (!transactionActive) {
            System.err.println("Error: No active transaction to roll back.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.transactionFile))) {
            Database backupDatabase = (Database) ois.readObject();
            database.restore(backupDatabase); // Assuming restore method exists in Database class
            transactionActive = false;
            System.out.println("Transaction rolled back. Database state restored.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.printf("Error while rolling back transaction: %s%n", e.getMessage());
        }
    }

    public void addCommand(Runnable command) {
        if (!transactionActive) {
            System.err.println("Error: No active transaction. Cannot add command.");
            return;
        }
        commandQueue.add(command);
    }
}
