package database.system.core.managers;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseSerializerTest {
    // Successfully creates a new directory if it does not exist
    @Test
    public void test_create_new_directory() {
        DatabaseSerializer serializer = new DatabaseSerializer("testDB");
        serializer.create();
        File directory = new File("testDB");
        assertTrue(directory.exists());
        directory.delete(); // Cleanup
    }

    // Successfully writes the database scheme to a file
    @Test
    public void test_write_database_scheme_to_file() throws IOException {
        DatabaseSerializer serializer = new DatabaseSerializer("testDB");
        serializer.create();
        serializer.save();
        File file = new File("db-core/src/main/resources/root/db/testDB/testDB.schm");
        assertTrue(file.exists());
        file.delete(); // Cleanup
    }

    // Correctly reads the database scheme from a file
    @Test
    public void test_read_database_scheme_from_file() throws IOException, ClassNotFoundException {
        DatabaseSerializer serializer = new DatabaseSerializer("testDB");
        serializer.create();
        serializer.save(); // First write to ensure there is something to read
        serializer.read();
        assertNotNull(serializer.getDatabaseScheme());
        new File("db-core/src/main/resources/root/db/testDB/testDB.schm").delete(); // Cleanup
    }

    // Handles directory creation with correct directory name from constructor
    @Test
    public void test_directory_creation_with_correct_name() {
        DatabaseSerializer serializer = new DatabaseSerializer("validName");
        serializer.create();
        File directory = new File("validName");
        assertTrue(directory.exists());
        directory.delete(); // Cleanup
    }

    // Retrieves the singleton instance of DatabaseScheme during construction
    @Test
    public void test_singleton_instance_retrieval() {
        DatabaseSerializer serializer1 = new DatabaseSerializer("testDB1");
        DatabaseSerializer serializer2 = new DatabaseSerializer("testDB2");
        assertSame(serializer1.getDatabaseScheme(), serializer2.getDatabaseScheme());
    }

    // Throws NullPointerException if the directory name is null
    @Test
    public void test_null_directory_name_exception() {
        assertThrows(NullPointerException.class, () -> {
            new DatabaseSerializer(null);
        });
    }

    // Throws NullPointerException if the directory name is empty
    @Test
    public void test_empty_directory_name_exception() {
        assertThrows(NullPointerException.class, () -> {
            new DatabaseSerializer("");
        });
    }

    // Does not create a directory if it already exists
    @Test
    public void test_no_duplicate_directory_creation() {
        File directory = new File("db-core/src/main/resources/root/db/existingDB");
        directory.mkdirs(); // Pre-create the directory
        DatabaseSerializer serializer = new DatabaseSerializer("existingDB");
        assertThrows(RuntimeException.class, serializer::create); // No additional files should be created in the directory
        directory.delete(); // Cleanup
    }

    // Throws IOException if file writing fails due to file system issues
    @Test()
    public void test_io_exception_on_file_write_failure() throws IOException {
        DatabaseSerializer serializer = new DatabaseSerializer("testDB");
        File file = new File("db-core/src/main/resources/root/db/testDB/testDB.schm");
        file.createNewFile();
        file.setReadOnly(); // Make file read-only to induce IOException on write
        try {
            assertThrows(RuntimeException.class, serializer::save);
        } finally {
            file.setWritable(true);
            file.delete(); // Cleanup
        }
    }
}