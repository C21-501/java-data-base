package database.system.core.managers;

import database.api.CommandManager;
import database.system.core.structures.bodies.DatabaseBody;
import database.system.core.structures.bodies.TableBody;
import database.system.core.structures.schemes.DatabaseScheme;
import database.system.core.structures.schemes.FieldScheme;
import database.system.core.structures.schemes.TableScheme;
import database.system.core.types.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@EqualsAndHashCode(callSuper = true)
@Data
public class DatabaseSerializer extends Serializer{
    private DatabaseScheme databaseScheme;
    private DatabaseBody databaseBody;

    private final String filePath = STR."\{super.DATABASE_DIR_PATH}/\{databaseName}";

    public DatabaseSerializer(String dirName){
        if (dirName.isEmpty())
            throw new NullPointerException();
        super(dirName);
        this.databaseScheme = DatabaseScheme.getInstance();
        this.databaseBody = new DatabaseBody(databaseScheme);
    }

    public DatabaseSerializer(String dirName, DatabaseScheme instance){
        if (dirName.isEmpty() || instance == null)
            throw new NullPointerException("null parameters");
        super(dirName);
        this.databaseScheme = instance;
    }

    @Override
    void save() throws IOException {
        if (!databaseScheme.getTables().isEmpty())
            writeToFileCascade(databaseScheme);
        writeSchmToFile(filePath,databaseName,databaseScheme);
        writeBdyToFile(filePath,databaseName,databaseBody);
    }

    @Override
    void update() {
        File directory = new File(filePath);
        if (directory.exists()) {
            System.err.println(STR."Directory already exists: \{databaseName}");
        } else {
            boolean created = directory.mkdirs();
            if (!created)
                throw new RuntimeException(STR."Failed to create directory: \{databaseName}");
        }
    }

    @Override
    void read() throws IOException, ClassNotFoundException {
        this.databaseScheme = readFromFile();
    }

    private void writeToFileCascade(DatabaseScheme databaseScheme) {
        for (Map.Entry<String, TableScheme> table: databaseScheme.getTables().entrySet()){
            String directoryPath = STR."\{filePath}/\{table.getKey()}";
            try {
                Files.createDirectories(Paths.get(directoryPath));
                writeSchmToFile(directoryPath,table.getKey(),table.getValue());
                writeBdyToFile(directoryPath,table.getKey(), new TableBody().setTable(table.getValue()));
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private void writeSchmToFile(String filePath, String name, Serializable record) throws IOException {
        String fileName = STR."\{filePath}/\{name}.schm";
        if (super.createFile(fileName)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(record);
            }
        }
    }

    private void writeBdyToFile(String filePath, String name, Serializable record) throws IOException {
        String fileName = STR."\{filePath}/\{name}.bdy";
        if (super.createFile(fileName)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(record);
            }
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

    public static void main(String[] args) throws IOException {
        DatabaseSerializer databaseSerializer = new DatabaseSerializer("test_db");
        databaseSerializer.update();

        databaseSerializer.getDatabaseScheme()
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
        databaseSerializer.update();
        databaseSerializer.save();

        List<Object[]> firstTableValues = new ArrayList<>();
        firstTableValues.add(new Object[]{1, "ivan"});
        firstTableValues.add(new Object[]{1, "ivan"});

        List<Object[]> secondTableValues = new ArrayList<>();
        secondTableValues.add(new Object[]{1, "ivan"});
        secondTableValues.add(new Object[]{1, "ivan"});

        databaseSerializer.save();

        databaseSerializer.getDatabaseBody()
                .insertInto("1_table", firstTableValues)
                .insertInto("2_table", secondTableValues);

        databaseSerializer.getDatabaseBody()
                .selectFrom("1_table");
//        CommandManager commandManager = new CommandManager();
//        databaseSerializer.getDatabaseBody()
//                .select()
//                .from()
//                .where();

        databaseSerializer.save();
    }
}

