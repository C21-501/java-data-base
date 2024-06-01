package database.system.core.managers;

import database.system.core.structures.Database;
import lombok.Data;

import java.io.File;
import java.io.IOException;

@Data
public abstract class Serializer {
    protected String databaseDirPath = "db-core/src/main/resources/root/db";
    protected String databaseName;

    Serializer(){}

    protected Serializer(String dirName) {
        this.databaseDirPath = dirName;
    }

    public Serializer(String dirName, String dirPath) {
        this.databaseName = dirName;
        this.databaseDirPath = dirPath;
    }

    protected static boolean createFile(String fileName) throws IOException {
        File file = new File(fileName);
        return file.createNewFile();
    }

    abstract void save(Database database) throws IOException;
    abstract void createDatabaseDirectory(String filePath);
    abstract Database read(String filePath, String databaseName) throws IOException, ClassNotFoundException;
}
