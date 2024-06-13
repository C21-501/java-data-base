package database.api;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static database.api.utils.Utils.parseStringToObjectArray;

public class DatabaseAPIExample {
    public static void main(String[] ignoredArgs) throws IOException {
        // Создаем экземпляр DatabaseAPI
        DatabaseAPI databaseAPI = new DatabaseAPI();

        // Устанавливаем активного редактора и историю команд
        databaseAPI.setActiveEditor(new DatabaseEditor());
        databaseAPI.setHistory(new CommandHistory());

        // Создаем базу данных
        databaseAPI.create("test_database", Optional.empty());

        // Создаем таблицу
        databaseAPI.create("test_table", List.of("id INTEGER", "name STRING", "age INTEGER"));

        // Вставляем записи
        databaseAPI.insert("test_table",
                List.of("id", "name", "age"),
                List.of(
                        parseStringToObjectArray("1, 'Alice', 20"),
                        parseStringToObjectArray("2, 'Bob', 25")
                )
        );

        // Выбор всех записей
        databaseAPI.select("test_table");
        databaseAPI.print();

        // Начинаем транзакцию
        databaseAPI.begin();

        // Вставляем записи в рамках транзакции
        databaseAPI.insert("test_table",
                List.of("id", "name", "age"),
                List.of(
                        parseStringToObjectArray("3, 'Tom', 21"),
                        parseStringToObjectArray("4, 'Peter', 18")
                )
        );

        // Коммитим транзакцию
        databaseAPI.commit();

        // Выбор всех записей после коммита
        databaseAPI.select("test_table");
        databaseAPI.print();

        // Обновляем записи
        databaseAPI.update("test_table", List.of("name = 'Alice Smith'", "age = 31"), "id = 1");

        // Удаляем запись
        databaseAPI.delete("test_table", "id = 2");

        databaseAPI.insert("test_table",
                List.of("id", "name", "age"),
                List.of(
                        parseStringToObjectArray("5, 'Sara', 21"),
                        parseStringToObjectArray("6, 'Connor', 18")
                )
        );

        // Выбор всех записей после изменений
        databaseAPI.select("test_table");
        databaseAPI.getLastSelectResponse().print();

        // Добавляем новый столбец
        databaseAPI.alter("test_table", List.of("salary REAL"));

        // Выбор всех записей после добавления столбца
        databaseAPI.select("test_table");
        databaseAPI.print();
        // Начинаем транзакцию для демонстрации отката
        databaseAPI.begin();

        // Вставляем записи в рамках транзакции
        databaseAPI.insert("test_table",
                List.of("id", "name", "age", "salary"),
                List.of(
                        parseStringToObjectArray("5, 'Sam', 28, 50000.0"),
                        parseStringToObjectArray("6, 'Anna', 24, 60000.0")
                )
        );
        // Откатываем транзакцию
        databaseAPI.rollback();
        // Выбор всех записей после отката
        databaseAPI.select("test_table");
        databaseAPI.print();

        // Переименовываем таблицу
        databaseAPI.alter("test_table", "renamed_table", false);

        databaseAPI.insert("renamed_table",
                List.of("id", "name", "age", "salary"),
                List.of(
                        parseStringToObjectArray("7, 'Sam', null, 50000.0"),
                        parseStringToObjectArray("8, 'Anna', 24, 60000.0")
                )
        );

        databaseAPI.update("renamed_table", List.of("salary = 50000.0"), "id = 1");

        // Выбор всех записей из переименованной таблицы
        databaseAPI.select("renamed_table");
        databaseAPI.print();

        // Удаляем таблицу
        databaseAPI.drop("renamed_table", false);

        // Удаляем базу данных
        databaseAPI.drop("test_database", true);
    }
}
