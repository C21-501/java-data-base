package database.system.core.managers;

import database.system.core.structures.Column;
import database.system.core.structures.Database;
import database.system.core.structures.Table;
import database.system.core.types.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class DatabaseSerializer extends Serializer{
    private Database database;

    private final String filePath = STR."\{super.DATABASE_DIR_PATH}/\{databaseName}";

    public DatabaseSerializer(String dirName){
        if (dirName.isEmpty())
            throw new NullPointerException();
        super(dirName);
        this.database = Database.getInstance();
    }

    public DatabaseSerializer(String dirName, Database instance){
        if (dirName.isEmpty() || instance == null)
            throw new NullPointerException("null parameters");
        super(dirName);
        this.database = instance;
    }

    @Override
    public void save() throws IOException {
        writeInstanceToFile(filePath,databaseName, database);
    }

    @Override
    public void update() {
        File directory = new File(filePath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created)
                throw new RuntimeException(STR."Failed to create directory: \{databaseName}");
        }
    }

    @Override
    public void read() throws IOException, ClassNotFoundException {
        this.database = readFromFile();
    }

    private void writeInstanceToFile(String filePath, String name, Serializable record) throws IOException {
        String fileName = STR."\{filePath}/\{name}.instance";
        if (super.createFile(fileName)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(record);
            }
        }
    }

    private Database readFromFile() throws IOException, ClassNotFoundException {
        String fileName = STR."\{filePath}/\{databaseName}.instance";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Database) ois.readObject();
        }
    }

    public static void main(String[] args) throws IOException {
        DatabaseSerializer databaseSerializer = new DatabaseSerializer("test_db");
        databaseSerializer.update();

        databaseSerializer.getDatabase()
                .createTable(
                        "1_table",
                        new Table()
                                .createColumn("id", new Column(DataType.INTEGER))
                                .createColumn("name", new Column(DataType.STRING))
                );

        databaseSerializer.getDatabase()
                .createTable(
                        "2_table",
                        new Table()
                                .createColumn("id", new Column(DataType.INTEGER))
                                .createColumn("surname", new Column(DataType.STRING))
                );

        databaseSerializer.update();
        databaseSerializer.save();

        try {
            databaseSerializer.readFromFile();
            System.out.println(databaseSerializer.getDatabase());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Object[]> firstTableValues = new ArrayList<>();
        firstTableValues.add(new Object[]{1, "ivan"});
        firstTableValues.add(new Object[]{2, "peter"});

        List<Object[]> secondTableValues = new ArrayList<>();
        secondTableValues.add(new Object[]{1, "pedro"});
        secondTableValues.add(new Object[]{2, "pe"});

        databaseSerializer
                .getDatabase()
                .insertInto("1_table", new String[]{"id", "name"}, firstTableValues);
        databaseSerializer
                .getDatabase()
                .insertInto("2_table", new String[]{"id", "surname"}, secondTableValues);

        databaseSerializer.save();

        try {
            databaseSerializer.readFromFile();
            System.out.println(databaseSerializer.getDatabase());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        databaseSerializer
                .getDatabase()
                .selectFrom("2_table", new String[]{"id","surname"})
                .printTable();

        databaseSerializer
                .getDatabase()
                .selectFrom("1_table", new String[]{"id", "name"})
                .printTable();
//        databaseSerializer.getDatabase()
//                .insertInto("1_table", firstTableValues)
//                .insertInto("2_table", secondTableValues);
//
//        databaseSerializer.getDatabase()
//                .selectFrom("1_table");

//        CommandManager commandManager = new CommandManager();
//        databaseSerializer.getDatabaseBody()
//                .select()
//                .from()
//                .where();

        databaseSerializer.save();
    }
}

