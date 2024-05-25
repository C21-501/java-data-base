package database.system.core.managers;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ColumnSerializer extends Serializer{
    private List<String> columnNames;

    // Метод для записи FieldRecord в файл
//    public static void writeToFile(Body record, String filePath) throws IOException {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
//            oos.writeObject(record);
//        }
//    }
//
//    // Метод для чтения FieldRecord из файла
//    public static Body readFromFile(String filePath) throws IOException, ClassNotFoundException {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
//            return (Body) ois.readObject();
//        }
//    }

    @Override
    void save() {

    }

    @Override
    void update() {

    }

    @Override
    void read() {

    }
}
