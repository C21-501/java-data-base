package database.system.core.structures;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.types.DataType;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@Data
public class Table implements DatabaseStructure {
    private Map<String, Column> columns = new HashMap<>();
    private Set<Constraint> constraintSet = new HashSet<>();

    public boolean contains(String columnName) {
        return columns.containsKey(columnName);
    }

    public Column getColumn(String columnName) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        return columns.get(columnName);
    }

    public Table createColumn(String columnName, DataType type) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        columns.put(columnName, new Column(type));
        return this;
    }

    public Table createColumn(String columnName, Column column) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (column == null)
            throw new NullPointerException("parameter `column` is null");
        columns.put(columnName, column);
        return this;
    }

    public void dropColumn(String columnName) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (columns.remove(columnName) == null)
            throw new RuntimeException(String.format("Column with name %s not found", columnName));
    }

    public Table renameField(String oldColumnName, String newColumnName) {
        if (oldColumnName == null)
            throw new NullPointerException("parameter `oldColumnName` is null");
        if (newColumnName == null)
            throw new NullPointerException("parameter `newColumnName` is null");
        Column removedFieldScheme = columns.remove(oldColumnName);
        if (removedFieldScheme == null)
            throw new RuntimeException(String.format("Column %s not found", oldColumnName));
        columns.put(newColumnName, removedFieldScheme);
        return this;
    }

    public Table updateField(String columnName, Column newFieldScheme) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (newFieldScheme == null)
            throw new NullPointerException("parameter `newField` is null");
        if (columns.replace(columnName, newFieldScheme) == null)
            throw new RuntimeException(String.format("Column %s does not exist", columnName));
        return this;
    }

    public Table addConstraint(String columnName, Constraint constraint) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        Column column = columns.get(columnName);
        if (column == null)
            throw new RuntimeException(String.format("Column %s does not exist", columnName));
        column.getFieldScheme().addConstraint(constraint);
        return this;
    }

    public Table dropConstraint(String columnName, Constraint constraint) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        Column column = columns.get(columnName);
        if (column == null)
            throw new RuntimeException(String.format("Column %s does not exist", columnName));
        column.removeConstraint(constraint);
        return this;
    }

    public boolean containsValues(Object value) {
        return columns.containsValue((Column) value);
    }

    public long getObjectsNumber() {
        return columns.size();
    }

    public void renameColumn(String oldColumnName, String newColumnName) {
        if (oldColumnName == null)
            throw new NullPointerException("parameter `oldColumnName` is null");
        if (newColumnName == null)
            throw new NullPointerException("parameter `newColumnName` is null");
        Column removed = columns.remove(oldColumnName);
        if (removed == null)
            throw new RuntimeException(String.format("Column %s not found", oldColumnName));
        columns.put(newColumnName, removed);
    }

    public Table updateColumn(String columnName, Column column) {
        if (columnName == null)
            throw new NullPointerException("parameter `columnName` is null");
        if (column == null)
            throw new NullPointerException("parameter `newField` is null");
        if (columns.replace(columnName, column) == null)
            throw new RuntimeException(String.format("Column %s does not exist", columnName));
        return this;
    }


    public void insert(String columnName, Object value) {
        columns.get(columnName).insert((byte[])value);
    }

    public void delete(String condition) {
        // Получаем предикат для фильтрации строк
        Predicate<Object[]> filter = createFilter(condition);

        // Получаем список ключей столбцов
        Set<String> columnNames = columns.keySet();

        // Удаляем строки, удовлетворяющие условию
        for (String columnName : columnNames) {
            Column column = columns.get(columnName);
            column.delete(filter);
        }

        // Возвращаем экземпляр текущей таблицы для поддержки цепочки вызовов
    }

    public Table update(Column column, Object value, String condition) {
        Predicate<Object[]> filter = createFilter(condition);
        column.update(value, filter);
        // Возвращаем экземпляр текущей таблицы для поддержки цепочки вызовов
        return this;
    }

    private Predicate<Object[]> createFilter(String condition) {
        // Разбиваем условие на имя столбца, оператор и значение
        String[] parts = condition.split(" ");
        String columnName = parts[0].trim();
        String operator = parts[1].trim();
        Object value = parts[2].trim(); // Предполагается, что value имеет правильный формат для типа столбца

        // Создаем предикат для фильтрации строк в зависимости от оператора сравнения
        return switch (operator) {
            case "=" -> row -> columns
                    .get(columnName)
                    .getFieldBody().getObjectList()
                    .stream()
                    .anyMatch(existingValue -> existingValue.getObject().equals(value));
            case "<" -> row -> columns
                    .get(columnName)
                    .getFieldBody().getObjectList()
                    .stream()
                    .anyMatch(existingValue -> existingValue.compareTo(value) < 0);
            case ">" -> row -> columns
                    .get(columnName)
                    .getFieldBody().getObjectList()
                    .stream()
                    .anyMatch(existingValue -> existingValue.compareTo(value) > 0);
            // Добавьте другие операторы по необходимости
            default -> throw new IllegalArgumentException(String.format("Unsupported operator: %s", operator));
        };
    }
}
