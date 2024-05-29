package database.api.tcl;

import database.api.DatabaseEditor;
import database.system.core.structures.Database;
import lombok.Data;

import java.io.*;

@Data
public class TCLManager {
    private Database database;
    private String transactionFile;

    public TCLManager(Database database, String transactionFile) {
        this.database = database;
        this.transactionFile = transactionFile;
    }

    public void begin() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(this.transactionFile))
        ) {
            oos.writeObject(database);
            System.out.println("Transaction started. Database state saved.");
        } catch (IOException e) {
            System.err.printf("Error while starting transaction: %s%n", e.getMessage());
        }
    }

    public void commit() {
        try {
            database.saveState(this.transactionFile);
            File transactionFile = new File(this.transactionFile);
            if (transactionFile.exists() && !transactionFile.delete()) {
                System.err.println("Warning: Could not delete transaction backup file.");
            }
            System.out.println("Transaction committed. Changes saved.");
        } catch (Exception e) {
            System.err.printf("Error while committing transaction: %s%n", e.getMessage());
        }
    }

    public void rollback() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.transactionFile))) {
            Database backupDatabase = (Database) ois.readObject();
            database.restore(backupDatabase);
            System.out.println("Transaction rolled back. Database state restored.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.printf("Error while rolling back transaction: %s%n", e.getMessage());
        }
    }
}
