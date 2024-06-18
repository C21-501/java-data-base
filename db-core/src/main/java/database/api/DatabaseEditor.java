package database.api;

import database.api.ddl.DDLManager;
import database.api.dml.DMLManager;
import database.api.utils.UtilManager;
import database.api.tcl.TCLManager;
import database.monitor.Config;
import database.system.core.exceptions.*;
import database.system.core.exceptions.enums.EmptyParameterError;
import database.system.core.exceptions.enums.InvalidParameterError;
import database.system.core.exceptions.enums.RuntimeError;
import database.system.core.serializers.DatabaseSerializer;
import database.system.core.structures.Database;
import database.system.core.structures.Table;
import lombok.Data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Data
public class DatabaseEditor {
    private DDLManager ddlManager;
    private DMLManager dmlManager;
    private TCLManager tclManager;
    private UtilManager utilManager;
    private Database database;
    private String databaseName;
    private String databasePath = Config.ROOT_DATABASE_PATH; // Using default path from Config
    private DatabaseSerializer databaseSerializer;

    public DatabaseEditor() {
        this.database = Database.getInstance();
        this.setUpManagers(database);
    }

    public void createDatabase(String databaseName) {
        resetDatabaseInstance();
        if (databaseName.isEmpty()) {
            throw new EmptyParameterException(EmptyParameterError.DATABASE_NAME_NULL);
        }
        this.databaseName = databaseName;
        this.database = Database.getInstance();
        this.database.setFilePath(Config.getDatabaseFilePath(databasePath, databaseName));
        DatabaseSerializer databaseSerializer = DatabaseSerializer.getInstance();
        databaseSerializer.setDatabaseName(databaseName);
        databaseSerializer.setDatabaseDirPath(databasePath);
        databaseSerializer.createDatabaseDirectoryAndFile(databasePath, databaseName);
        databaseSerializer.saveDatabaseInstance(database);
        this.setUpManagers(database);
    }

    public void createDatabase(String databaseName, String path) {
        resetDatabaseInstance();
        if (databaseName.isEmpty() || path.isEmpty()) {
            throw new EmptyParameterException(EmptyParameterError.DATABASE_NAME_OR_PATH_NULL);
        }
        this.databaseName = databaseName;
        this.databasePath = path;
        this.database = Database.getInstance();
        this.database.setFilePath(Config.getDatabaseFilePath(databasePath, databaseName));
        DatabaseSerializer databaseSerializer = DatabaseSerializer.getInstance();
        databaseSerializer.setDatabaseName(databaseName);
        databaseSerializer.setDatabaseDirPath(databasePath);
        databaseSerializer.createDatabaseDirectoryAndFile(path, databaseName);
        databaseSerializer.saveDatabaseInstance(database);
        this.setUpManagers(database);
    }

    private void setUpManagers(Database database) {
        this.ddlManager = new DDLManager(database);
        this.dmlManager = new DMLManager(database);
        this.tclManager = new TCLManager(database);
        this.utilManager = new UtilManager(database);
    }

    public void saveDatabaseState() {
        databaseSerializer.saveDatabaseInstance(database);
    }

    public void restoreDatabaseState() {
        try {
            resetDatabaseInstance();
            Database tmpDatabase = databaseSerializer.readInstanceFromFile(databasePath, databaseName);
            if (tmpDatabase == null) {
                throw new InvalidParameterException(InvalidParameterError.DATABASE_INSTANCE_NULL);
            }
            this.database = tmpDatabase;
        } catch (DatabaseIOException e) {
            throw new DatabaseRuntimeException(RuntimeError.DROPPING_DATABASE, e.getMessage());
        }
    }

    public static void resetDatabaseInstance() {
        Database.resetInstance();
        System.gc();  // Optional, request garbage collection
    }

    public String getFullPath() {
        return Config.getDatabaseFilePath(databasePath, databaseName);
    }

    public void dropDatabase(String name) {
        dropDatabase(name, databasePath);
        database = null;
        setUpManagers(null);
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
                throw new DatabaseRuntimeException(RuntimeError.DROPPING_DATABASE, e.getMessage());
            } finally {
                resetDatabaseInstance();
                this.databaseName = "";
            }
        } else {
            throw new DatabaseRuntimeException(RuntimeError.DATABASE_DOES_NOT_EXIST, name);
        }
    }

    public void restoreDatabaseStateFromBackup(Map<String, Table> backup) {
        String path = database.getFilePath();
        resetDatabaseInstance();
        database = Database.getInstance();
        database.setTables(backup);
        database.setFilePath(path);
        saveDatabaseState();
    }

    public void renameDatabase(String name, String newName) throws IOException {
        if (name.isEmpty() || newName.isEmpty()) {
            throw new EmptyParameterException(EmptyParameterError.DATABASE_NAME_OR_NEW_NAME_EMPTY);
        }

        Path oldDatabaseDir = Paths.get(databasePath, name);
        Path newDatabaseDir = Paths.get(databasePath, newName);

        if (Files.exists(oldDatabaseDir)) {
            if (Files.exists(newDatabaseDir)) {
                throw new DatabaseRuntimeException(RuntimeError.RENAMING_DATABASE_EXISTS, newName);
            }

            try {
                // Move the directory
                Files.move(oldDatabaseDir, newDatabaseDir);
                databaseName = newName;

                // Update the database path if needed
                databasePath = newDatabaseDir.getParent().toString();

                // Rename files starting with the old database name
                try (Stream<Path> files = Files.walk(newDatabaseDir)) {
                    files.filter(Files::isRegularFile)
                            .filter(path -> path.getFileName().toString().startsWith(name))
                            .forEach(path -> {
                                try {
                                    Path newPath = path.resolveSibling(path.getFileName().toString().replaceFirst(name, newName));
                                    Files.move(path, newPath);
                                } catch (IOException e) {
                                    throw new DatabaseRuntimeException(RuntimeError.RENAMING_FILE, e.getMessage());
                                }
                            });
                }

            } catch (IOException e) {
                throw new DatabaseRuntimeException(RuntimeError.RENAMING_DATABASE, e.getMessage());
            }
        } else {
            throw new DatabaseRuntimeException(RuntimeError.DATABASE_DOES_NOT_EXIST, name);
        }
    }

    public boolean haveActiveTransactions() {
        return tclManager.isTransactionActive();
    }

    public void collectCommands(Command command) {
        tclManager.addCommand(command);
    }

    public void openDatabase(String databaseName) {
        openDatabase(databaseName, this.databasePath);
    }

    public void openDatabase(String databaseName, String path) {
        if (databaseName.isEmpty() || path.isEmpty()) {
            throw new IllegalArgumentException("Error while opening database: database name or path is null");
        }
        this.databasePath = path;
        this.database.setFilePath(String.format("%s/%s/%s.instance", path, databaseName, databaseName));
        this.databaseSerializer = DatabaseSerializer.getInstance();
        databaseSerializer.setDatabaseName(databaseName);
        databaseSerializer.setDatabaseDirPath(databasePath);
        try {
            Database tmpDatabase = databaseSerializer.readInstanceFromFile(path, databaseName);
            if (tmpDatabase == null) {
                throw new RuntimeException("Error while opening database: database instance is null");
            }
            this.database = tmpDatabase;
        } catch (DatabaseIOException e) {
            e.printStackTrace(System.err);
            throw new RuntimeException(String.format("Error while opening database: %s%n", e.getMessage()));
        }
    }
}
