package database.system.core.managers;

import database.system.core.structures.schemes.TableScheme;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class TableSerializer extends Serializer{
    private final Map<String, TableScheme> tables;
    private final String concreteDatabaseDirPath;

    public TableSerializer(Map<String,TableScheme> tables){
        if (tables.isEmpty())
            throw new NullPointerException("tables is empty");
        if (super.databaseName.isEmpty())
            throw new RuntimeException("db not exist. Create db before creating tables");
        this.tables = tables;
        this.concreteDatabaseDirPath = STR."\{DATABASE_DIR_PATH}/\{databaseName}";
    }

    @Override
    void save() {

    }

    @Override
    void update() {
        for (String tableName: tables.keySet()){
            File directory = new File(STR."\{concreteDatabaseDirPath}/\{tableName}");
            if (directory.exists()) {
                System.out.println(STR."Table directory already exists: \{tableName}");
            } else {
                boolean created = directory.mkdirs();
                if (created) {
                    System.out.println(STR."Table directory created successfully: \{tableName}");
                } else {
                    throw new RuntimeException(STR."Failed to create directory: \{tableName}");
                }
            }
        }
    }

    @Override
    void read() {

    }

    private void writeTables(){

    }
}
