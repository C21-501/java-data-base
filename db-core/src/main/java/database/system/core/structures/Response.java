package database.system.core.structures;

import database.api.utils.OUTPUT_TYPE;
import database.system.core.structures.interfaces.Printable;
import lombok.Data;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents a Response object that stores data in a table-like structure with column names and values.
 * Provides methods to set values for columns, retrieve values by column and index, and print the table.
 */
@Data
public class Response implements Serializable, Printable {
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
     * Retrieves the IDs from the specified column.
     *
     * @param columnName The name of the column.
     * @return A set of IDs from the specified column.
     */
    public Set<Integer> getIds(String columnName) {
        return get(columnName).stream()
                .map(Value::getId)
                .collect(Collectors.toCollection(TreeSet::new));
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
     * Prints the table with formatted columns and values to the specified output.
     *
     * @param outputType The type of output: "console" or "file".
     * @param filePath   The file path if outputType is "file"; ignored otherwise.
     */
    @Override
    public synchronized void print(OUTPUT_TYPE outputType, Optional<String> filePath) {
        PrintStream out = System.out;
        if (outputType.equals(OUTPUT_TYPE.FILE) && filePath.isPresent()) {
            try {
                out = new PrintStream(new FileOutputStream(filePath.get(),true));
                // Print the current date and time at the beginning of the file
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                out.printf("Date: %s%n", now.format(formatter));
                out.println();
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found: %s".formatted(filePath), e);
            }
        }

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
                int length = value.getObject() != null ? value.getObject().toString().length() : NULL_STRING.length();
                if (length > maxWidth) {
                    maxWidth = length;
                }
            }
            columnWidths.put(columnName, maxWidth);
        }

        // Calculate the total table width
        int totalWidth = columnWidths.values().stream().mapToInt(Integer::intValue).sum() + columnWidths.size() * 3 + 1;
        int tableNameWidth = tableName.length();

        // Print the table name row
        printTableNameSeparator(out, tableNameWidth + 4);
        out.printf("| %s |%n", center(tableName, tableNameWidth));
        // Print the column headers
        printSeparator(out, totalWidth);
        for (String columnName : responseColumnsOrder) {
            int width = columnWidths.get(columnName);
            out.printf("| %s ", center(columnName, width));
        }
        out.println("|");
        printSeparator(out, totalWidth);

        // Print the rows
        for (int i = 0; i < maxRows; i++) {
            for (String columnName : responseColumnsOrder) {
                List<Value> values = responseMap.get(columnName);
                int width = columnWidths.get(columnName);
                if (i < values.size()) {
                    String value = values.get(i).getObject() == null ? NULL_STRING : values.get(i).getObject().toString();
                    out.printf("| %s ", center(value, width));
                } else {
                    out.printf("| %s ", center(NULL_STRING, width));
                }
            }
            out.println("|");
            printSeparator(out, totalWidth);
        }

        if (out != System.out) {
            out.close();
        }
    }

    private void printSeparator(PrintStream out, int width) {
        out.printf("+%s+%n", "-".repeat(width - 2));
    }

    private void printTableNameSeparator(PrintStream out, int width) {
        out.printf("+%s+%n", "-".repeat(width - 2));
    }

    private String center(String text, int width) {
        int padding = width - text.length();
        int paddingLeft = padding / 2;
        int paddingRight = padding - paddingLeft;
        return " ".repeat(paddingLeft) + text + " ".repeat(paddingRight);
    }
}
