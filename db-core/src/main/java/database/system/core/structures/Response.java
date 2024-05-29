package database.system.core.structures;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents a Response object that stores data in a table-like structure with column names and values.
 * Provides methods to set values for columns, retrieve values by column and index, and print the table.
 */
@Data
public class Response implements Serializable {
    private String tableName;
    private Map<String, List<Value>> responseMap = new TreeMap<>();

    public Response(String tableName) {
        if (tableName == null || tableName.isEmpty()) {
            throw new NullPointerException("Table name cannot be null or empty");
        }
        this.tableName = tableName;
    }

    public void set(String columnName, List<Value> objects){
        responseMap.put(columnName,objects);
    }

    public void set(String columnName, List<Value> objects, Predicate<Object> predicate) {
        List<Value> filteredObjects = objects.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        responseMap.put(columnName, filteredObjects);
    }

    public List<Value> get(String columnName) {
        List<Value> values = responseMap.get(columnName);
        if (values == null) {
            throw new IndexOutOfBoundsException(STR."Invalid name of column: \{columnName}");
        }
        return values;
    }

    public Object get(String columnName, int index) {
        List<Value> values = responseMap.get(columnName);
        if (values == null || index >= values.size()) {
            throw new IndexOutOfBoundsException(STR."Invalid index for column: \{columnName}");
        }
        return values.get(index).getObject();
    }

    public void printTable() {
        // Calculate the maximum number of rows
        System.out.println(tableName);
        int maxRows = responseMap.values().stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);

        // Calculate the maximum width for each column
        Map<String, Integer> columnWidths = new HashMap<>();
        for (String columnName : responseMap.keySet()) {
            int maxWidth = columnName.length();
            for (Value value : responseMap.get(columnName)) {
                int length = value.getObject().toString().length();
                if (length > maxWidth) {
                    maxWidth = length;
                }
            }
            columnWidths.put(columnName, maxWidth);
        }

        // Calculate total table width
        int totalWidth = columnWidths.values().stream().mapToInt(Integer::intValue).sum()
                + columnWidths.size() * 3 + 1;

        // Print the column headers
        printSeparator(totalWidth);
        for (String columnName : responseMap.keySet()) {
            int width = columnWidths.get(columnName);
            System.out.printf(STR."| %-\{width}s ", columnName);
        }
        System.out.println("|");
        printSeparator(totalWidth);

        // Print the rows
        for (int i = 0; i < maxRows; i++) {
            for (String columnName : responseMap.keySet()) {
                List<Value> values = responseMap.get(columnName);
                int width = columnWidths.get(columnName);
                if (i < values.size()) {
                    String value = values.get(i).getObject().toString();
                    System.out.printf(STR."| %-\{width}s ", center(value, width));
                } else {
                    System.out.printf(STR."| %-\{width}s ", center("", width));
                }
            }
            System.out.println("|");
        }
        printSeparator(totalWidth);
    }

    private void printSeparator(int width) {
        System.out.println("-".repeat(width));
    }

    private String center(String text, int width) {
        int padding = width - text.length();
        int paddingLeft = padding / 2;
        int paddingRight = padding - paddingLeft;
        return " ".repeat(paddingLeft) + text + " ".repeat(paddingRight);
    }
}