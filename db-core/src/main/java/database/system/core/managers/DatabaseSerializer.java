package database.system.core.managers;

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
    private String filePath = STR."\{databaseDirPath}/\{databaseName}";

    public DatabaseSerializer(Database instance, String databaseName) {
        if (databaseName.isEmpty() || instance == null)
            throw new NullPointerException("null parameters");
        super(databaseName);
    }

    public DatabaseSerializer(Database instance, String dirName, String path){
        if (dirName.isEmpty() || instance == null)
            throw new NullPointerException("null parameters");
        super(dirName, path);
    }

    @Override
    public void save(Database database) throws IOException {
        writeInstanceToFile(filePath,databaseName, database);
    }

    @Override
    public void createDatabaseDirectory(String filePath) {
        Path directory = Paths.get(String.format("%s/%s",filePath, databaseName));
        try {
            Files.createDirectories(directory);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to create directory: %s", directory.getFileName()));
        }
    }

    @Override
    public Database read(String filePath, String databaseName) throws IOException, ClassNotFoundException {
        return readFromFile(filePath, databaseName);
    }

    private static void writeInstanceToFile(String filePath, String name, Serializable record) throws IOException {
        String fileName = STR."\{filePath}/\{name}.instance";
        createFile(fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(record);
        }
    }

    private static Database readFromFile(String filePath, String databaseName) throws IOException, ClassNotFoundException, FileNotFoundException {
        String fileName = STR."\{filePath}/\{databaseName}/\{databaseName}.instance";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Database) ois.readObject();
        }
    }
}

