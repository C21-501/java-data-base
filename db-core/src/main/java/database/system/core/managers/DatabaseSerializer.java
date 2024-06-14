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
    private String filePath = String.format("%s/%s",databaseDirPath,databaseName);

    public DatabaseSerializer(Database instance, String dirName, String path){
        if (dirName.isEmpty() || instance == null)
            throw new NullPointerException("null parameters");
        super(dirName, path);
    }

    @Override
    public void saveInstance(Database database) {
        try {
            writeInstanceToFile(database.getFilePath(), database);
        } catch (IOException e){
            throw new RuntimeException("Failed to save instance of database to file.");
        }
    }

    @Override
    public void createDatabaseDirectoryAndFile(String filePath) {
        String path = String.format("%s/%s",filePath, databaseName);
        Path directory = Paths.get(path);
        try {
            Files.createDirectories(directory);
            createFile(String.format("%s/%s.instance",path, databaseName));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to create directory: %s", directory.getFileName()));
        }
    }

    @Override
    public Database read(String filePath, String databaseName) throws IOException, ClassNotFoundException {
        return readFromFile(filePath, databaseName);
    }

    private static void writeInstanceToFile(String filepath, Database record) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(record);
        }
    }

    private static Database readFromFile(String filePath, String databaseName) throws IOException, ClassNotFoundException, FileNotFoundException {
        String fileName = String.format("/%s/%s/%s.instance",filePath,databaseName,databaseName);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Database) ois.readObject();
        }
    }
}

