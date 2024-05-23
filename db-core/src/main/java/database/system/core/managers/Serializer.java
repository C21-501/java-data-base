package database.system.core.managers;

import database.system.core.structures.bodies.Body;
import lombok.Data;

import java.io.*;

@Data
public class Serializer {
    // Метод для записи FieldRecord в файл
    public static void writeToFile(Body record, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(record);
        }
    }

    // Метод для чтения FieldRecord из файла
    public static Body readFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Body) ois.readObject();
        }
    }
}
