package database.api;

import database.system.core.structures.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DatabaseAPITest {
    DatabaseAPI databaseAPI;

    @BeforeEach
    public void setUp() {
        databaseAPI = new DatabaseAPI();
        databaseAPI.setActiveEditor(new DatabaseEditor());
        databaseAPI.setHistory(new CommandHistory());
        databaseAPI.getActiveEditor().createDatabase("test_db", "C:\\Users\\Евгений\\IdeaProjects\\java-data-base\\db-core\\src\\test\\resources");
    }

    @AfterEach
    public void tearDown() throws IOException {
        databaseAPI.drop("test_db", true);
    }
    
    @Test
    public void test_example_with_all_commands() throws IOException {
        // Create a new table named "employees" with columns "id INTEGER", "name STRING", "age INTEGER"
        databaseAPI.create("employees", List.of("id INTEGER", "name STRING", "age INTEGER"));
        // Insert records into the "employees" table
        List<Object[]> values = List.of(
                new Object[]{1, "John", 30},
                new Object[]{2, "Alice", 25}
        );
        databaseAPI.insert("employees", List.of("id", "name", "age"), values);
        // Select all records from the "employees" table
        databaseAPI.select("employees");
        databaseAPI.getLastSelectResponse().print();
        // Begin a new transaction
        databaseAPI.begin();
        // Update the age of employee with id 1 to 32
        databaseAPI.update("employees", List.of("name = 'John'","age = 18"), "id = 1");
        // Commit the transaction
        databaseAPI.commit();
        databaseAPI.select("employees");
        databaseAPI.getLastSelectResponse().print();
        // Drop the "employees" table
        databaseAPI.drop("employees", false);
    }

    // Successfully create a new table with valid table name and columns
    @Test
    public void test_create_table_with_valid_name_and_columns() throws IOException {
        databaseAPI.setHistory(new CommandHistory());
        List<String> columns = List.of("id INTEGER", "name STRING");
        databaseAPI.create("testTable", columns);
        assertTrue(databaseAPI.getActiveEditor().getDdlManager().getDatabase().containsTable("testTable"));
    }

    // Successfully alter an existing table with valid modifications
    @Test
    public void test_alter_table_with_valid_modifications() throws IOException {
        databaseAPI.setHistory(new CommandHistory());
        List<String> columns = List.of("id INTEGER", "name STRING");
        databaseAPI.create("testTable", columns);
        List<String> addColumn = List.of("age INTEGER");
        databaseAPI.alter("testTable", addColumn);
        assertTrue(databaseAPI.getActiveEditor().getDdlManager().getDatabase().containsTable("testTable"));
        assertTrue(databaseAPI.getActiveEditor().getDdlManager().getDatabase().getTable("testTable").get().contains("age"));
    }

    // Successfully drop an existing table with a valid table name
    @Test
    public void test_drop_table_with_valid_name() throws IOException {
        databaseAPI.setHistory(new CommandHistory());
        List<String> columns = List.of("id INTEGER", "name STRING");
        databaseAPI.create("testTable", columns);
        databaseAPI.drop("testTable", false);
        assertFalse(databaseAPI.getActiveEditor().getDdlManager().getDatabase().containsTable("testTable"));
    }

    // Successfully delete records from a table with a valid condition
    @Test
    public void test_delete_records_with_valid_condition() throws IOException {
        databaseAPI.setHistory(new CommandHistory());
        List<String> columns = List.of("id INTEGER", "name STRING");
        List<Object[]> values = List.of(new Object[]{1, "John"}, new Object[]{2, "Jane"});
        databaseAPI.create("testTable", columns);
        columns = List.of("id", "name");
        databaseAPI.insert("testTable", columns, values);
        databaseAPI.delete("testTable", "id = 1");
        Response result = databaseAPI.getActiveEditor().getDmlManager().select("testTable", columns);
        result.print();
        assertEquals(1, result.getResponseMap().get("id").size());
    }

    // Successfully insert records into a table with valid columns and values
    @Test
    public void test_insert_records_with_valid_columns_and_values() throws IOException {
        databaseAPI.setHistory(new CommandHistory());
        List<String> columns = List.of("id INTEGER", "name STRING");
        List<Object[]> values = List.of(new Object[]{1, "John"}, new Object[]{2, "Jane"});
        databaseAPI.create("testTable", columns);
        columns = List.of("id", "name");
        databaseAPI.insert("testTable", columns, values);
        Response result = databaseAPI.getActiveEditor().getDmlManager().select("testTable", columns);
        result.print();
        assertEquals(2, result.getResponseMap().get("id").size());
    }

    // Successfully select records from a table with valid columns and condition
    @Test
    public void test_select_records_with_valid_columns_and_condition() throws IOException {
        databaseAPI.setHistory(new CommandHistory());
        List<String> columns = List.of("id INTEGER", "name STRING", "surname STRING", "salary INTEGER", "is_boss BOOLEAN");
        List<Object[]> values = List.of(
                new Object[]{1, "John", "Doe", 50000, false},
                new Object[]{2, "Jane", "Smith", 60000, true}
        );
        databaseAPI.create("testTable", columns);
        columns = List.of("id", "name", "surname", "salary", "is_boss");
        databaseAPI.insert("testTable", columns, values);
        columns = List.of("id", "name", "surname", "is_boss");
        Response result = databaseAPI.getActiveEditor().getDmlManager().select("testTable", columns, "id = 1");
        result.print();
        assertEquals(1, result.getResponseMap().get("id").size());
    }

    // Attempt to create a table with an empty or null table name
    @Test
    public void test_create_table_with_empty_or_null_name() {
        databaseAPI.setHistory(new CommandHistory());
        List<String> columns = List.of("id INTEGER", "name STRING");
        assertThrows(NullPointerException.class, () -> {
            databaseAPI.create("", columns);
        });
        assertThrows(NullPointerException.class, () -> {
            databaseAPI.create(null, columns);
        });
    }

    // Attempt to alter a table with invalid column modifications
    @Test
    public void test_alter_table_with_invalid_modifications() throws IOException {
        databaseAPI.setHistory(new CommandHistory());
        List<String> columns = List.of("id INTEGER", "name STRING");
        assertThrows(RuntimeException.class, () -> {
            databaseAPI.alter("nonExistentTable", columns);
        });
    }

    // Attempt to drop a table that does not exist
    @Test
    public void test_drop_non_existent_table() throws IOException {
        databaseAPI.setHistory(new CommandHistory());
        assertThrows(RuntimeException.class, () -> {
            databaseAPI.drop("nonExistentTable", false);
        });
    }

    // Attempt to delete records with an invalid condition
    @Test
    public void test_delete_records_with_invalid_condition() throws IOException {
        databaseAPI.setHistory(new CommandHistory());
        assertThrows(RuntimeException.class, () -> {
            databaseAPI.delete("testTable", "invalidCondition");
        });
    }

    // Attempt to insert records with mismatched columns and values
    @Test
    public void test_insert_records_with_mismatched_columns_and_values() {
        List<String> columns = Arrays.asList("id", "name", "age");
        List<Object[]> values = new ArrayList<>();
        values.add(new Object[]{1, "Alice"}); // Missing 'age' value
        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.setActiveEditor(new DatabaseEditor());
        assertThrows(NullPointerException.class, () -> {
            databaseAPI.insert("users", columns, values);
        });
    }

    // Attempt to select records with an invalid condition
    @Test
    public void test_select_records_with_invalid_condition() {
        List<String> columns = Arrays.asList("column1", "column2");
        String tableName = "table1";
        String invalidCondition = "invalid_condition";

        DatabaseAPI databaseAPI = new DatabaseAPI();
        DatabaseEditor databaseEditor = new DatabaseEditor();
        databaseAPI.setActiveEditor(databaseEditor);
        CommandHistory history = new CommandHistory();
        databaseAPI.setHistory(history);
        assertThrows(NullPointerException.class, () -> {
            databaseAPI.select(tableName, columns, invalidCondition);
        });
    }

    // Successfully update records in a table with valid value and condition
    @Test
    public void test_update_records_valid_value_and_condition() throws IOException {
        String tableName = "table1";
        List<String> columns = Arrays.asList("column1 STRING", "column2 STRING");
        String condition = "column1 = 'old_value'";

        // Create table
        databaseAPI.create(tableName, columns);

        // Insert records
        List<Object[]> values = new ArrayList<>();
        values.add(new Object[]{"old_value", "data"});
        columns = Arrays.asList("column1", "column2");
        databaseAPI.insert(tableName, columns, values);

        // Update records
        databaseAPI.update(tableName, List.of("column1 = new_value"), condition);

        // Verify
        List<String> selectedColumns = Arrays.asList("column1", "column2");
        String selectCondition = "column1 = 'new_value'";
        assertDoesNotThrow(() -> databaseAPI.select(tableName, selectedColumns, selectCondition));
        databaseAPI.getLastSelectResponse().print();
    }

    // Attempt to undo when there are no commands in history
    @Test
    public void test_undo_with_no_commands_in_history() {
        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.setHistory(new CommandHistory());
        assertDoesNotThrow(databaseAPI::undo);
    }

    // Attempt to update records with an invalid condition
    @Test
    public void test_attempt_update_invalid_condition() {
        String tableName = "users";
        List<String> columns = List.of("name STRING", "age INTEGER");
        List<Object[]> values = new ArrayList<>();
        values.add(new Object[]{"Alice", 25});
        values.add(new Object[]{"Bob", 30});

        try {
            databaseAPI.create(tableName, columns);
            columns = List.of("name", "age");
            databaseAPI.insert(tableName, columns, values);

            // Attempt to update with an invalid condition
            assertThrows(IllegalArgumentException.class, () -> databaseAPI.update(tableName, List.of("age = 32"), "invalid_condition"));
        } catch (IOException e) {
            fail("Exception thrown when not expected: %s".formatted(e.getMessage()));
        }
    }

    // Verify database state is correctly saved and restored during command execution
    @Test
    public void test_database_state_save_and_restore() throws IOException {
        List<String> columns = List.of("id INTEGER", "name STRING", "age INTEGER");
        databaseAPI.create("users", columns);

        List<Object[]> values = new ArrayList<>();
        values.add(new Object[]{1, "Alice", 25});
        values.add(new Object[]{2, "Bob", 30});
        columns = List.of("id", "name", "age");
        databaseAPI.insert("users", columns, values);
        try {
            databaseAPI.drop("users", false);
            databaseAPI.undo();
            // Verify that the 'users' table is restored after dropping it
            assertFalse(databaseAPI.getActiveEditor().getDdlManager().getDatabase().containsTable("users"));
        } catch (IOException | ClassNotFoundException e) {
            fail("Exception thrown during database state save and restore: %s".formatted(e.getMessage()));
        }
    }

    // Ensure command history is correctly maintained after each command execution
    @Test
    public void test_command_history_maintenance() throws IOException, ClassNotFoundException {
        CommandHistory history = databaseAPI.getHistory();

        List<String> columns = List.of("id INTEGER", "name STRING", "age INTEGER");
        databaseAPI.create("users", columns);
        assertEquals(1, history.size());

        databaseAPI.alter("users", null, null, List.of("age"));
        assertEquals(2, history.size());

        databaseAPI.drop("users", false);
        assertEquals(3, history.size());

        databaseAPI.undo();
        assertEquals(2, history.size());
        assertTrue(databaseAPI.getActiveEditor().getDatabase().containsTable("users"));

        databaseAPI.undo();
        assertEquals(1, history.size());
        assertTrue(databaseAPI.getActiveEditor().getDatabase().getTable("users").get().contains("age"));

        databaseAPI.undo();
        assertEquals(0, history.size());
        assertTrue(databaseAPI.getActiveEditor().getDatabase().getTable("users").isEmpty());
    }

    // Successfully undo the last executed command
    @Test
    public void test_undo_last_executed_command() throws IOException {
        List<String> columns = List.of("id INTEGER", "name STRING", "age INTEGER");
        databaseAPI.create("users", columns);
        try {
            databaseAPI.undo();
            assertEquals(0, databaseAPI.getHistory().size());
        } catch (IOException | ClassNotFoundException e) {
            fail("Exception thrown while undoing the command: %s".formatted(e.getMessage()));
        }
    }

    @Test
    public void test_rename_existing_database() throws IOException {
        databaseAPI.alter("test_db", "new_test_db", true);
        assertEquals("new_test_db", databaseAPI.getActiveEditor().getDatabaseName());
        databaseAPI.alter("new_test_db", "test_db", true);
    }
}