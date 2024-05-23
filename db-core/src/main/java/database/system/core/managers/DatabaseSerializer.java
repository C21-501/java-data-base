package database.system.core.managers;

import database.system.core.structures.schemes.DatabaseScheme;
import database.system.core.structures.schemes.FieldScheme;
import database.system.core.structures.schemes.TableScheme;
import database.system.core.types.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class DatabaseSerializer extends Serializer{
    private DatabaseScheme databaseScheme;
    private String filePath = STR."\{super.DATABASE_DIR_PATH}/\{databaseName}";

    public DatabaseSerializer(String dirName){
        if (dirName.isEmpty())
            throw new NullPointerException();
        super(dirName);
        this.databaseScheme = DatabaseScheme.getInstance();
    }

    @Override
    void save() throws IOException {
        writeToFile(databaseScheme);
    }

    @Override
    void create() {
        File directory = new File(filePath);
        if (directory.exists()) {
            throw new RuntimeException(STR."Directory already exists: \{databaseName}");
        } else {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println(STR."Directory created successfully: \{databaseName}");
            } else {
                throw new RuntimeException(STR."Failed to create directory: \{databaseName}");
            }
        }
    }

    @Override
    void read() throws IOException, ClassNotFoundException {
        this.databaseScheme = readFromFile();
    }


    private void writeToFile(DatabaseScheme record) throws IOException {
        String fileName = STR."\{filePath}/\{databaseName}.schm";
        if (super.createFile(fileName)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(record);
            }
        } else {
            throw new RuntimeException(STR."file to create directory: \{databaseName}");
        }
    }

    private DatabaseScheme readFromFile() throws IOException, ClassNotFoundException {
        String fileName = STR."\{filePath}/\{databaseName}.schm";
        File file = new File(fileName);
        if (file.exists()) {
            throw new RuntimeException(STR."file already exists: \{fileName}");
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (DatabaseScheme) ois.readObject();
        }
    }

    public static void main(String[] args) {
        DatabaseSerializer serializer = new DatabaseSerializer("test_db");
        serializer.create();
        serializer.getDatabaseScheme()
                .createTable(
                        "1_table",
                        new TableScheme()
                                .createField("id", new FieldScheme(DataType.INTEGER))
                                .createField("name", new FieldScheme(DataType.STRING))
                )
                .createTable(
                        "2_table",
                        new TableScheme()
                                .createField("id", new FieldScheme(DataType.INTEGER))
                                .createField("surname", new FieldScheme(DataType.STRING))
                );
        try {
            serializer.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

