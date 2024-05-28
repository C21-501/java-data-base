package database.system.core.managers;

import lombok.Data;

import java.io.File;
import java.io.IOException;

@Data
public abstract class Serializer {
    protected String DATABASE_DIR_PATH = "db-core/src/main/resources/root/db";
    protected String databaseName;

    Serializer(){}

    protected Serializer(String dirName) {
        this.databaseName = dirName;
    }

    protected boolean createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists())
            return file.createNewFile();
        return false;
    }

    abstract void save() throws IOException;
    abstract void update();
    abstract void read() throws IOException, ClassNotFoundException;
}
