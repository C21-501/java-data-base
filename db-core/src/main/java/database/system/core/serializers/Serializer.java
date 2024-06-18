package database.system.core.serializers;

import database.monitor.Config;
import database.system.core.structures.Database;
import lombok.Data;

import java.io.File;
import java.io.IOException;

@Data
public abstract class Serializer {
    protected String databaseDirPath = Config.ROOT_DATABASE_PATH;
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

    abstract void saveDatabaseInstance(Database database) throws IOException;
    abstract void createDatabaseDirectoryAndFile(String filePath);
    abstract Database readInstanceFromFile(String filePath, String databaseName) throws IOException, ClassNotFoundException;
}
