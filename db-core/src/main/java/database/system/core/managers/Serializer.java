package database.system.core.managers;

import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Serializer {
    protected String DATABASE_DIR_PATH = "db-core/src/main/resources/root/db";
    protected String databaseName;
    protected List<String> tableNames;

    Serializer(){}

    protected Serializer(String dirName) {
        this.databaseName = dirName;
        this.tableNames = new ArrayList<>();
    }

    protected Serializer(String dirName, List<String> tableNames){
        this.databaseName = dirName;
        this.tableNames = tableNames;
    }

    protected boolean createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            System.out.println(STR."file already exists: \{fileName}");
        }
        return file.createNewFile();
    }

    abstract void save() throws IOException;
    abstract void create();
    abstract void read() throws IOException, ClassNotFoundException;
}
