package database.api;

import database.api.ddl.DDLManager;
import database.api.dml.DMLManager;
import database.api.tcl.TCLManager;
import database.system.core.managers.DatabaseSerializer;
import database.system.core.structures.Database;
import lombok.Data;

import java.io.*;

@Data
public class DatabaseEditor {
    private DatabaseSerializer databaseSerializer;
    private DDLManager ddlManager;
    private DMLManager dmlManager;
    private TCLManager tclManager;
    private Database database;

    public DatabaseEditor(){}

    public void createDatabase(String databaseName){
        if (databaseName.isEmpty())
            throw  new NullPointerException("Error while creating database : database name is null");
        this.databaseSerializer = new DatabaseSerializer(databaseName);
        this.database = databaseSerializer.getDatabase();
        this.ddlManager = new DDLManager(databaseSerializer.getDatabase());
        this.dmlManager = new DMLManager(databaseSerializer.getDatabase());
        this.tclManager = new TCLManager(databaseSerializer.getDatabase(), databaseSerializer.getDATABASE_DIR_PATH());
    }

    public void saveDatabaseState() {
        try {
            databaseSerializer.create();
            databaseSerializer.save();
            System.out.println("Database state saved successfully.");
        } catch (IOException e) {
            System.err.printf("Error while saving database state: %s%n", e.getMessage());
        }
    }

    public void restoreDatabaseState() {
        try {
            databaseSerializer.read();
            System.out.println("Database state restored successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.printf("Error while restoring database state: %s%n", e.getMessage());
        }
    }

    public void close() throws IOException {
        databaseSerializer.getDatabase().close();
        databaseSerializer.setDatabase(null);
    }
}

