package database.system.core.managers;

import database.system.core.structures.schemes.FieldScheme;
import database.system.core.structures.schemes.TableScheme;
import database.system.core.structures.bodies.FieldBody;
import database.system.core.structures.bodies.TableBody;
import database.system.core.types.DataType;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Serializer {

    public static void main(String[] args) {

        TableScheme tableScheme = new TableScheme("test_table");
        tableScheme.createField("1st_column",new FieldScheme(DataType.INTEGER));
        tableScheme.createField("2nd_column",new FieldScheme(DataType.STRING));
        tableScheme.createField("3rd_column",new FieldScheme(DataType.STRING));


        TableBody tableBody = new TableBody(tableScheme);
        tableBody.setFieldValues(0, "test", "test");
        tableBody.setFieldValues(1, "test1", "test1");
        tableBody.setFieldValues(2, "test2", "test2");
        // Создаем пример записи поля
        Map<Integer, TableBody> fieldMap = new TreeMap<>();




        // Записываем FieldRecord в файл
        String filePath = "db-core/src/test/resources/fieldRecord.ser";
        try {
            FieldBody record = null;
            writeToFile(record, filePath);
            System.out.println("FieldRecord записан в файл");

            // Читаем FieldRecord из файла
            FieldBody readRecord = readFromFile(filePath);
            System.out.println(STR."FieldRecord считан из файла: \{readRecord}");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.err);
        }
    }

    // Метод для записи FieldRecord в файл
    private static void writeToFile(FieldBody record, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(record);
        }
    }

    // Метод для чтения FieldRecord из файла
    private static FieldBody readFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (FieldBody) ois.readObject();
        }
    }
}
