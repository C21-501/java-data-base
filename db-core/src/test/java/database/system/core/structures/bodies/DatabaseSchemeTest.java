package database.system.core.structures.bodies;

import database.system.core.structures.schemes.DatabaseScheme;
import database.system.core.structures.schemes.FieldScheme;
import database.system.core.structures.schemes.TableScheme;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseSchemeTest {
    // Create a single table and verify its existence
    @Test
    public void create_single_table_and_verify_existence() {
        DatabaseScheme db = DatabaseScheme.getInstance();
        TableScheme table = new TableScheme();
        db.createTable("testTable", table);
        assertTrue(db.containsTable("testTable"));
    }

    // Drop an existing table and verify it's no longer accessible
    @Test
    public void drop_existing_table_and_verify_access() {
        DatabaseScheme db = DatabaseScheme.getInstance();
        TableScheme table = new TableScheme();
        db.createTable("testTable", table);
        db.dropTable("testTable");
        assertFalse(db.containsTable("testTable"));
    }

    // Retrieve a table by name and check its properties
    @Test
    public void retrieve_table_by_name_and_check_properties() {
        DatabaseScheme db = DatabaseScheme.getInstance();
        TableScheme table = new TableScheme();
        db.createTable("testTable", table);
        TableScheme retrievedTable = db.getTable("testTable");
        assertNotNull(retrievedTable);
    }

    // Check the total number of tables after multiple creations
    @Test
    public void check_total_number_of_tables_after_multiple_creations() {
        DatabaseScheme db = DatabaseScheme.getInstance();
        db.createTable("table1", new TableScheme());
        db.createTable("table2", new TableScheme());
        assertEquals(2, db.getObjectsNumber());
    }

    // Verify that containsTable returns true for an existing table
    @Test
    public void verify_contains_table_returns_true_for_existing_table() {
        DatabaseScheme db = DatabaseScheme.getInstance();
        TableScheme table = new TableScheme();
        db.createTable("testTable", table);
        assertTrue(db.containsTable("testTable"));
    }

    // Attempt to create a table with a null name or null TableScheme
    @Test
    public void attempt_to_create_table_with_null_name_or_scheme() {
        DatabaseScheme db = DatabaseScheme.getInstance();
        assertThrows(NullPointerException.class, () -> db.createTable(null, new TableScheme()));
        assertThrows(NullPointerException.class, () -> db.createTable("testTable", null));
    }

    // Attempt to drop a table with a null name
    @Test
    public void attempt_to_drop_table_with_null_name() {
        DatabaseScheme db = DatabaseScheme.getInstance();
        assertThrows(NullPointerException.class, () -> db.dropTable(null));
    }

    // Attempt to create a table that already exists
    @Test
    public void attempt_to_create_table_that_already_exists() {
        DatabaseScheme db = DatabaseScheme.getInstance();
        TableScheme table = new TableScheme();
        db.createTable("testTable", table);
        assertThrows(IllegalArgumentException.class, () -> db.createTable("testTable", new TableScheme()));
    }

    // Attempt to drop a table that does not exist
    @Test
    public void attempt_to_drop_table_that_does_not_exist() {
        DatabaseScheme db = DatabaseScheme.getInstance();
        assertThrows(IllegalArgumentException.class, () -> db.dropTable("nonExistentTable"));
    }

    // Attempt to retrieve a table with a null name
    @Test
    public void attempt_to_retrieve_table_with_null_name() {
        DatabaseScheme db = DatabaseScheme.getInstance();
        assertThrows(NullPointerException.class, () -> db.getTable(null));
    }

    // Attempt to retrieve a non-existing table
    @Test
    public void attempt_to_retrieve_non_existing_table() {
        DatabaseScheme db = DatabaseScheme.getInstance();
        assertThrows(RuntimeException.class, () -> db.getTable("nonExistentTable"));
    }

    // Verify thread safety by concurrently accessing getInstance
    @Test
    public void verify_thread_safety_by_concurrently_accessing_get_instance() {
        Runnable task = () -> {
            DatabaseScheme instance1 = DatabaseScheme.getInstance();
            DatabaseScheme instance2 = DatabaseScheme.getInstance();
            assertSame(instance1, instance2);
        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Threads interrupted");
        }
    }
    @Test
    public void test_create_table_and_fields() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme();
        tableScheme.createField("column1", new FieldScheme(DataType.STRING));
        tableScheme.createField("column2", new FieldScheme(DataType.INTEGER));
        databaseScheme.createTable("table1", tableScheme);

        assertEquals(1, databaseScheme.getObjectsNumber());
        assertTrue(databaseScheme.containsTable("table1"));

        TableScheme retrievedTable = databaseScheme.getTable("table1");
        assertNotNull(retrievedTable);
        assertEquals(2, retrievedTable.getObjectsNumber());
        assertTrue(retrievedTable.contains("column1"));
        assertTrue(retrievedTable.contains("column2"));
    }
}