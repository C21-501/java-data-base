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
    private DDLManager ddlManager;
    private DMLManager dmlManager;
    private TCLManager tclManager;
    private Database database;
    private String databaseName;
    private String databasePath;
    private DatabaseSerializer databaseSerializer;

    public void createDatabase(String databaseName){
        if (databaseName.isEmpty())
            throw  new NullPointerException("Error while creating database : database name is null");
        this.databaseName = databaseName;
        this.databasePath = databaseName;
        this.database = Database.getInstance();
        this.databaseSerializer = new DatabaseSerializer(database, databaseName);
        databaseSerializer.createDatabaseDirectory(databaseName);
        this.setUpManagers();
    }

    public void createDatabase(String databaseName, String path){
        if (databaseName.isEmpty() || path.isEmpty())
            throw  new NullPointerException("Error while creating database : database name or path is null");
        this.databaseName = databaseName;
        this.databasePath = path;
        this.database = Database.getInstance();
        this.databaseSerializer = new DatabaseSerializer(database, databaseName, path);
        databaseSerializer.createDatabaseDirectory(path);
        this.setUpManagers();
    }

    private void setUpManagers() {
        this.ddlManager = new DDLManager(database);
        this.dmlManager = new DMLManager(database);
        this.tclManager = new TCLManager(database, String.format("%s/%s/%s.instance",databasePath,databaseName,databaseName));
    }

    public void saveDatabaseState() {
        try {
            databaseSerializer.save(database);
//            System.out.println("Database state saved successfully.");
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error while saving database state: %s%n", e.getMessage()));
        }
    }

    public void restoreDatabaseState() {
        try {
            Database tmpDatabase = databaseSerializer.read(databasePath,databaseName);
            if (tmpDatabase == null)
                throw new RuntimeException("Error while restoring database state: database instance is null");
            resetDatabaseInstance();
            this.database = databaseSerializer.read(databasePath, databaseName);
//            System.out.println("Database state restored successfully.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(String.format("Error while restoring database state: %s%n", e.getMessage()));
        }
    }

    public void resetDatabaseInstance() throws IOException {
        Database.resetInstance();
        System.gc();  // Опционально, запросить сбор мусора
    }

    public String getFullPath(){
        return String.format("%s/%s/%s.instance",databasePath,databaseName,databaseName);
    }
}

