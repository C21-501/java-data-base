package database.api;

import database.system.core.structures.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DatabaseEditorTest {
    DatabaseEditor editor;
    static String path = "C:\\Users\\Евгений\\IdeaProjects\\java-data-base\\db-core\\src\\test\\resources";

    @BeforeEach
    public void setUp() {
        editor = new DatabaseEditor();
        editor.createDatabase("test_db", path);
    }

    @AfterEach
    public void tearDown() throws IOException {
        editor.dropDatabase("test_db" , path);
    }

    // Creating a new database instance with a valid name
    @Test
    public void test_create_database_with_valid_name() {
        assertNotNull(editor.getDatabase());
        assertEquals("test_db", editor.getDatabaseName());
    }

    // Saving the current state of the database
    @Test
    public void test_save_database_state() {
        editor.saveDatabaseState();
        File file = new File("%s/test_db/test_db.instance".formatted(editor.getDatabaseSerializer().getDatabaseDirPath()));
        assertTrue(file.exists());
    }

    // Restoring the database state from a saved file
    @Test
    public void test_restore_database_state() {
        editor.saveDatabaseState();
        System.out.println(editor.getDatabase());
        editor.getDatabase().create("new_table");
        editor.restoreDatabaseState();
        System.out.println(editor.getDatabase());
        assertFalse(editor.getDatabase().containsTable("new_table"));
    }

    // Performing DDL operations like create, alter, and drop table
    @Test
    public void test_perform_ddl_operations() {
        editor.getDdlManager().create("test_table", List.of("id INTEGER", "name STRING"));
        assertTrue(editor.getDatabase().containsTable("test_table"));
        editor.getDdlManager().drop("test_table");
        assertFalse(editor.getDatabase().containsTable("test_table"));
    }

    // Performing DML operations like insert, update, delete, and select
    @Test
    public void test_perform_dml_operations() {


        List<Object[]> values = List.of(
                new Object[]{1, "John"},
                new Object[]{2, "Tom"}
        );

        editor.getDdlManager().create("test_table", List.of("id INTEGER", "name STRING"));
        editor.getDmlManager().insert(
                "test_table",
                List.of("id", "name"),
                values
        );
        Response response = editor.getDmlManager().select("test_table", List.of("id", "name"), "id = 1");
        assertEquals(1, response.get("name").size());
        assertEquals("John", response.get("name",0));
    }

    // Starting, committing, and rolling back transactions
    @Test
    public void test_transaction_operations() {
        editor.getTclManager().begin();
        editor.getDdlManager().create("test_table", List.of("id INTEGER", "name STRING"));
        editor.getTclManager().rollback();
        assertFalse(editor.getDatabase().containsTable("test_table"));
        editor.getTclManager().begin();
        editor.getDdlManager().create("test_table", List.of("id INTEGER", "name STRING"));
        editor.getTclManager().commit();
        assertTrue(editor.getDatabase().containsTable("test_table"));
    }


    // Saving the database state when no changes have been made
    @Test
    public void test_save_database_state_no_changes() {
        editor.saveDatabaseState();
        File file = new File("%s/test_db/test_db.instance".formatted(editor.getDatabaseSerializer().getDatabaseDirPath()));
        assertTrue(file.exists());
    }

    // Restoring the database state when the file is corrupted or missing
    @Test
    public void test_restore_database_state_corrupted_or_missing_file() {
        File file = new File("%s/test_db/test_db.instance".formatted(editor.getDatabaseSerializer().getDatabaseDirPath()));
        file.delete();
        assertThrows(RuntimeException.class, editor::restoreDatabaseState);
    }

    // Performing DDL operations on non-existent tables
    @Test
    public void test_perform_ddl_operations_on_non_existent_tables() {

        assertThrows(RuntimeException.class, () -> editor.getDdlManager().drop("non_existent_table"));
    }

    // Performing DML operations with invalid column names or data types
    @Test
    public void test_perform_dml_operations_with_invalid_column_names_or_data_types() {
        editor.getDdlManager().create("test_table", List.of("id INTEGER", "name STRING"));
        assertThrows(RuntimeException.class, () -> editor.getDmlManager().insert(
                "test_table",
                List.of("invalid_column"),
                List.of(new Object[]{1},new Object[]{2}))
        );
    }

    // Handling IOExceptions during save and restore operations
    @Test
    public void test_handle_ioexceptions_during_save_and_restore_operations() {
        DatabaseEditor editor = new DatabaseEditor();
        assertThrows(RuntimeException.class, editor::saveDatabaseState);
        assertThrows(RuntimeException.class, editor::restoreDatabaseState);
    }

    // Handling large volumes of data efficiently
    /**
     * Test the efficiency of DatabaseEditor when handling large volumes of data.
     */
    @Test
    public void test_handling_large_volumes_of_data_efficiently() {
        // Создание большого объема данных для тестирования эффективности
        List<Object[]> largeData = new ArrayList<>();
        int largeVolume = 100000; // Количество записей
        for (int i = 0; i < largeVolume; i++) {
            largeData.add(new Object[]{i, "Name_%d".formatted(i)});
        }

        // Операции с большим объемом данных
        long startTime = System.currentTimeMillis();

        editor.getDdlManager().create("large_table", List.of("id INTEGER", "name STRING"));
        editor.getDmlManager().insert("large_table", List.of("id", "name"), largeData);

        long insertTime = System.currentTimeMillis();
        System.out.printf("Insertion time for %d records: %d ms%n", largeVolume, insertTime - startTime);

        Response response = editor.getDmlManager().select("large_table", List.of("id", "name"), "id < 10");
        response.print();

        long selectTime = System.currentTimeMillis();
        System.out.printf("Selection time for %d records: %d ms%n", largeVolume, selectTime - insertTime);

        // Убедиться, что выборка верна
        assertEquals(1, response.getResponseMap().size() / 2); // 10 записей для "id" и "name"
        for (int i = 0; i < 10; i++) {
            assertEquals(i, response.get("id", i));
            assertEquals("Name_%d".formatted(i), response.get("name", i));
        }

        // Вывод общей длительности операций
        long endTime = System.currentTimeMillis();
        System.out.printf("Total time for handling large volume of data: %d ms%n", endTime - startTime);

        // Добавить утверждение о максимальной длительности операции для эффективности (например, менее 5 секунд)
        assertTrue((endTime - startTime) < 5000, "Handling large volume of data took too long");
    }
        // Ensuring proper cleanup of temporary transaction files
    @Test
    public void test_ensuring_proper_cleanup_of_temporary_transaction_files() {
        editor.saveDatabaseState();
        editor.getTclManager().begin();
        editor.getTclManager().commit();
        System.out.println(editor.getDatabasePath());
        assertFalse(new File(editor.getFullPath()).exists());
    }

    // Validating the integrity of data after multiple transactions
    @Test
    public void test_validating_data_integrity_after_transactions() {
        editor.saveDatabaseState();

        List<Object[]> values = List.of(
                new Object[]{3, "alice"},
                new Object[]{4, "bob"}
        );

        // Perform transactions
        editor.getTclManager().begin();
        editor.getDdlManager().create("1_table", List.of("id INTEGER", "name STRING"));
        editor.getDmlManager().insert("1_table", Arrays.asList("id", "name"), values);
        editor.getTclManager().commit();

        // Validate data integrity
        Response response = editor.getDmlManager().select("1_table", Arrays.asList("id", "name"));
        response.print();
        assertEquals(2, response.getResponseMap().size());
        assertEquals(3, response.get("id",0));
        assertEquals("alice", response.get("name",0));
        assertEquals(4, response.get("id",1));
        assertEquals("bob", response.get("name",1));
    }

    @Test
    public void test_successfully_drops_existing_database_directory() {
        String databaseName = "testDb";
        editor.createDatabase(databaseName);
        editor.dropDatabase(databaseName);
        Path databaseDir = Paths.get(databaseName);
        assertFalse(Files.exists(databaseDir));
    }
}