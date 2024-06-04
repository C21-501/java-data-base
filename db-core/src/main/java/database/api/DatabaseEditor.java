package database.api;

import database.api.ddl.DDLManager;
import database.api.dml.DMLManager;
import database.api.tcl.TCLManager;
import database.system.core.managers.DatabaseSerializer;
import database.system.core.structures.Database;
import database.system.core.structures.Table;
import lombok.Data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;

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
        try {
            databaseSerializer.createDatabaseDirectory(path);
            databaseSerializer.save(database);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error: can't save instance of database %s", databaseName));
        }
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
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error while saving database state: %s%n", e.getMessage()));
        }
    }

    public void restoreDatabaseState() {
        try {
            resetDatabaseInstance();
            Database tmpDatabase = databaseSerializer.read(databasePath, databaseName);
            if (tmpDatabase == null)
                throw new RuntimeException("Error while restoring database state: database instance is null");
            this.database = tmpDatabase;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(String.format("Error while restoring database state: %s%n", e.getMessage()));
        }
    }

    public static void resetDatabaseInstance(){
        Database.resetInstance();
        System.gc();  // Опционально, запросить сбор мусора
    }

    public String getFullPath(){
        return String.format("%s/%s/%s.instance",databasePath,databaseName,databaseName);
    }

    public void dropDatabase(String name) {
        dropDatabase(name, databasePath);
    }

    public void dropDatabase(String name, String path) {
        Path databaseDir;
        if (path.equals(name))
            databaseDir = Paths.get(name);
        else
            databaseDir = Paths.get(path, name);
        if (Files.exists(databaseDir)) {
            try {
                Files.walk(databaseDir)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                throw new RuntimeException(String.format("Error while dropping database: %s%n", e.getMessage()));
            } finally {
                resetDatabaseInstance();
            }
        } else {
            throw new RuntimeException(String.format("Database %s does not exist.%n", name));
        }
    }

    public void restoreDatabaseStateFromBackup(Map<String, Table> backup) {
        resetDatabaseInstance();
        database = Database.getInstance();
        database.setTables(backup);
        saveDatabaseState();
    }
}

