package database.system.core.managers;

import database.system.core.structures.Field;
import database.system.core.structures.Table;
import database.system.core.structures.records.FieldRecord;
import database.system.core.structures.records.TableRecord;
import database.system.core.types.DataType;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Serializer {

    public static void main(String[] args) {

        Table table = new Table("test_table");
        table.createField("1st_column",new Field(DataType.INTEGER));
        table.createField("2nd_column",new Field(DataType.STRING));
        table.createField("3rd_column",new Field(DataType.STRING));


        TableRecord tableRecord = new TableRecord(table);
        tableRecord.setFields(0, "test", "test");
        // Создаем пример записи поля
        Map<Integer, TableRecord> fieldMap = new TreeMap<>();




        // Записываем FieldRecord в файл
        String filePath = "db-core/src/test/resources/fieldRecord.ser";
        try {
            writeToFile(record, filePath);
            System.out.println("FieldRecord записан в файл");

            // Читаем FieldRecord из файла
            FieldRecord readRecord = readFromFile(filePath);
            System.out.println(STR."FieldRecord считан из файла: \{readRecord}");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.err);
        }
    }

    // Метод для записи FieldRecord в файл
    private static void writeToFile(FieldRecord record, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(record);
        }
    }

    // Метод для чтения FieldRecord из файла
    private static FieldRecord readFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (FieldRecord) ois.readObject();
        }
    }
}
