package database.system.core.serializers;

import database.system.core.exceptions.DatabaseIOException;
import database.system.core.structures.Database;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@EqualsAndHashCode(callSuper = true)
@Data
public class DatabaseSerializer extends Serializer {
    private static volatile DatabaseSerializer instance;

    private DatabaseSerializer() {}

    public static DatabaseSerializer getInstance() {
        if (instance == null) {
            synchronized (DatabaseSerializer.class) {
                if (instance == null) {
                    instance = new DatabaseSerializer();
                }
            }
        }
        return instance;
    }

    @Override
    public void saveDatabaseInstance(Database database) throws DatabaseIOException {
        try {
            writeInstanceToFile(database.getFilePath(), database);
        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new DatabaseIOException("Failed to save instance of database to file.", e);
        }
    }

    @Override
    public void createDatabaseDirectoryAndFile(String filePath, String databaseName) throws DatabaseIOException {
        String path = String.format("%s/%s", filePath, databaseName);
        Path directory = Paths.get(path);
        try {
            Files.createDirectories(directory);
            createFile(String.format("%s/%s.instance", path, databaseName));
        } catch (IOException e) {
            throw new DatabaseIOException(String.format("Failed to create directory: %s", directory.getFileName()), e);
        }
    }

    @Override
    public Database readInstanceFromFile(String filePath, String databaseName) throws DatabaseIOException {
        try {
            return readFromFile(filePath, databaseName);
        } catch (IOException | ClassNotFoundException e) {
            throw new DatabaseIOException("Failed to read instance from file.", e);
        }
    }

    private static void writeInstanceToFile(String filepath, Database record) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(record);
        }
    }

    private static Database readFromFile(String filePath, String databaseName) throws IOException, ClassNotFoundException {
        String fileName = String.format("%s/%s/%s.instance", filePath, databaseName, databaseName);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Database) ois.readObject();
        }
    }
}
