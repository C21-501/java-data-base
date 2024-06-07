package database.system.core.structures;

import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents a Response object that stores data in a table-like structure with column names and values.
 * Provides methods to set values for columns, retrieve values by column and index, and print the table.
 */
@Data
public class Response implements Serializable {
    public static final String NULL_STRING = "NULL";
    private String tableName;
    private Map<String, List<Value>> responseMap = new TreeMap<>();
    private Set<String> responseColumnsOrder = new LinkedHashSet<>();

    /**
     * Constructs a new Response object with the specified table name and column names.
     *
     * @param tableName   The name of the table.
     * @param columnNames The list of column names in the table.
     * @throws NullPointerException if tableName is null or empty.
     */
    public Response(String tableName, List<String> columnNames) {
        if (tableName == null || tableName.isEmpty()) {
            throw new NullPointerException("Table name cannot be null or empty");
        }
        this.tableName = tableName;
        this.responseColumnsOrder.addAll(columnNames);
    }

    /**
     * Sets the values for the specified column.
     *
     * @param columnName The name of the column.
     * @param objects    The list of values for the column.
     */
    public void set(String columnName, List<Value> objects) {
        responseMap.put(columnName, objects);
    }

    /**
     * Sets the values for the specified column, applying the given predicate.
     *
     * @param columnName The name of the column.
     * @param objects    The list of values for the column.
     * @param predicate  The predicate to filter the values.
     */
    public void set(String columnName, List<Value> objects, Predicate<Object> predicate) {
        List<Value> filteredObjects = objects.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        responseMap.put(columnName, filteredObjects);
    }

    /**
     * Retrieves the list of values for the specified column.
     *
     * @param columnName The name of the column.
     * @return The list of values for the column.
     * @throws IndexOutOfBoundsException if the column name is invalid.
     */
    public List<Value> get(String columnName) {
        List<Value> values = responseMap.get(columnName);
        if (values == null) {
            throw new IndexOutOfBoundsException(String.format("Invalid name of column: %s", columnName));
        }
        return values;
    }

    /**
     * Retrieves the value at the specified index for the specified column.
     *
     * @param columnName The name of the column.
     * @param index      The index of the value.
     * @return The value at the specified index.
     * @throws IndexOutOfBoundsException if the column name is invalid or the index is out of bounds.
     */
    public Object get(String columnName, int index) {
        List<Value> values = responseMap.get(columnName);
        if (values == null || index >= values.size()) {
            throw new IndexOutOfBoundsException(String.format("Invalid index for column: %s", columnName));
        }
        return values.get(index).getObject();
    }

    /**
     * Prints the table with formatted columns and values.
     */
    public void printTable() {
        // Calculate the maximum number of rows
        int maxRows = responseMap.values().stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);

        // Calculate the maximum width for each column
        Map<String, Integer> columnWidths = new HashMap<>();
        for (String columnName : responseColumnsOrder) {
            int maxWidth = columnName.length();
            for (Value value : responseMap.get(columnName)) {
                int length = value.getObject() != null ? value.getObject().toString().length(): NULL_STRING.length();
                if (length > maxWidth) {
                    maxWidth = length;
                }
            }
            columnWidths.put(columnName, maxWidth);
        }

        // Calculate the total table width
        int totalWidth = columnWidths.values().stream().mapToInt(Integer::intValue).sum()
                + columnWidths.size() * 3 + 1;
        int tableNameWidth = tableName.length();
        int tableWidthWithHeader = Math.max(totalWidth, tableNameWidth + 4); // +4 for padding and separator

        // Print the table name row
        printTableNameSeparator(tableNameWidth + 4);
        System.out.printf("| %s |%n", tableName);

        // Print the column headers
        printSeparator(totalWidth);
        for (String columnName : responseColumnsOrder) {
            int width = columnWidths.get(columnName);
            System.out.printf("| %%-%ds ".formatted(width), columnName);
        }
        System.out.println("|");
        printSeparator(totalWidth);

        // Print the rows
        for (int i = 0; i < maxRows; i++) {
            for (String columnName : responseColumnsOrder) {
                List<Value> values = responseMap.get(columnName);
                int width = columnWidths.get(columnName);
                if (i < values.size()) {
                    String value = values.get(i).getObject() == null ? NULL_STRING: values.get(i).getObject().toString();
//                    String value = values.get(i).getObject() == null ? "NULL": String.valueOf(values.get(i).getId());
                    System.out.printf("| %%-%ds ".formatted(width), center(value, width));
                } else {
                    System.out.printf("| %%-%ds ".formatted(width), center(NULL_STRING, width));
                }
            }
            System.out.println("|");
            printSeparator(totalWidth);
        }
    }

    private void printSeparator(int width) {
        System.out.printf("+%s+%n", "-".repeat(width - 2));
    }

    private void printTableNameSeparator(int width) {
        System.out.printf("+%s+%n", "-".repeat(width - 2));
    }

    private String center(String text, int width) {
        int padding = width - text.length();
        int paddingLeft = padding / 2;
        int paddingRight = padding - paddingLeft;
        return " ".repeat(paddingLeft) + text + " ".repeat(paddingRight);
    }
}
