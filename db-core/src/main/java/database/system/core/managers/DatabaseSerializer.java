package database.system.core.managers;

import database.system.core.structures.Database;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.*;


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
    public void save(Database database) throws IOException,FileNotFoundException {
        writeInstanceToFile(filePath,databaseName, database);
    }

    @Override
    public void createDatabaseDirectory(String filePath) {
        File directory = new File(filePath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created)
                throw new RuntimeException(STR."Failed to create directory: \{databaseName}");
        }
    }

    @Override
    public Database read(String filePath, String databaseName) throws IOException, ClassNotFoundException {
        return readFromFile(filePath, databaseName);
    }

    private static void writeInstanceToFile(String filePath, String name, Serializable record) throws IOException {
        String fileName = STR."\{filePath}/\{name}.instance";
        if (createFile(fileName)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(record);
            }
        }
    }

    private static Database readFromFile(String filePath, String databaseName) throws IOException, ClassNotFoundException {
        String fileName = STR."\{filePath}/\{databaseName}/\{databaseName}.instance";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Database) ois.readObject();
        }
    }

//    public static void main(String[] args) throws IOException {
//        Database database1 = Database.getInstance();
//        DatabaseSerializer databaseSerializer = new DatabaseSerializer(database1,"db_test", "db-core/src/main/resources");
//        databaseSerializer.createDatabaseDirectory();
//
//        databaseSerializer.getDatabase()
//                .createTable(
//                        "1_table",
//                        new Table()
//                                .createColumn("id", new Column(DataType.INTEGER))
//                                .createColumn("name", new Column(DataType.STRING))
//                );
//
//        databaseSerializer.getDatabase()
//                .createTable(
//                        "2_table",
//                        new Table()
//                                .createColumn("id", new Column(DataType.INTEGER))
//                                .createColumn("surname", new Column(DataType.STRING))
//                );
//
//        databaseSerializer.createDatabaseDirectory();
//        databaseSerializer.save();
//
//        try {
//            databaseSerializer.readFromFile();
//            System.out.println(databaseSerializer.getDatabase());
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        List<Object[]> firstTableValues = new ArrayList<>();
//        firstTableValues.add(new Object[]{1, "ivan"});
//        firstTableValues.add(new Object[]{2, "peter"});
//
//        List<Object[]> secondTableValues = new ArrayList<>();
//        secondTableValues.add(new Object[]{1, "pedro"});
//        secondTableValues.add(new Object[]{2, "pe"});
//
//        databaseSerializer
//                .getDatabase()
//                .insertInto("1_table", new String[]{"id", "name"}, firstTableValues);
//        databaseSerializer
//                .getDatabase()
//                .insertInto("2_table", new String[]{"id", "surname"}, secondTableValues);
//
//        databaseSerializer.save();
//
//        try {
//            databaseSerializer.readFromFile();
//            System.out.println(databaseSerializer.getDatabase());
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        databaseSerializer
//                .getDatabase()
//                .selectFrom("2_table", new String[]{"id","surname"})
//                .printTable();
//
//        databaseSerializer
//                .getDatabase()
//                .selectFrom("1_table", new String[]{"id", "name"})
//                .printTable();
////        databaseSerializer.getDatabase()
////                .insertInto("1_table", firstTableValues)
////                .insertInto("2_table", secondTableValues);
////
////        databaseSerializer.getDatabase()
////                .selectFrom("1_table");
//
////        CommandManager commandManager = new CommandManager();
////        databaseSerializer.getDatabaseBody()
////                .select()
////                .from()
////                .where();
//
//        databaseSerializer.save();
//    }
}

