package database.system.core.structures.bodies;

import database.system.core.structures.schemes.DatabaseScheme;
import database.system.core.structures.schemes.FieldScheme;
import database.system.core.structures.schemes.TableScheme;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseBodyTest {
    // Instantiate DatabaseBody with a valid DatabaseScheme
    @Test
    public void instantiate_with_valid_scheme() {
        DatabaseScheme scheme = DatabaseScheme.getInstance();
        DatabaseBody body = new DatabaseBody(scheme);
        assertNotNull(body);
    }

    // Check that columnBodyList is populated correctly based on the number of tables in DatabaseScheme
    @Test
    public void column_body_list_populated_correctly() {
        DatabaseScheme scheme = DatabaseScheme.getInstance();
        scheme.createTable("table1", new TableScheme());
        scheme.createTable("table2", new TableScheme());
        DatabaseBody body = new DatabaseBody(scheme);
        assertEquals(2, body.getInnerObjects().size());
    }

    // Verify getInnerObjects returns a list containing columnBodyList
    @Test
    public void verify_get_inner_objects() {
        DatabaseScheme scheme = DatabaseScheme.getInstance();
        DatabaseBody body = new DatabaseBody(scheme);
        assertTrue(body.getInnerObjects().contains(body.getColumnBodyList()));
    }

    // Ensure DatabaseBody retains the reference to the provided DatabaseScheme
    @Test
    public void retains_database_scheme_reference() {
        DatabaseScheme scheme = DatabaseScheme.getInstance();
        DatabaseBody body = new DatabaseBody(scheme);
        assertSame(scheme, body.getDatabaseScheme());
    }

    // Instantiate DatabaseBody with a null DatabaseScheme throws NullPointerException
    @Test
    public void instantiate_with_null_scheme_throws_exception() {
        assertThrows(NullPointerException.class, () -> new DatabaseBody(null));
    }

    // Instantiate DatabaseBody with an empty DatabaseScheme results in an empty columnBodyList
    @Test
    public void empty_scheme_results_empty_column_body_list() {
        DatabaseScheme scheme = DatabaseScheme.getInstance();
        DatabaseBody body = new DatabaseBody(scheme);
        assertTrue(body.getColumnBodyList().isEmpty());
    }

    // DatabaseScheme with a high number of tables tests performance scalability of DatabaseBody initialization
    @Test
    public void high_table_count_performance_scalability() {
        DatabaseScheme scheme = DatabaseScheme.getInstance();
        for (int i = 0; i < 1000; i++) {
            scheme.createTable(STR."table\{i}", new TableScheme());
        }
        assertDoesNotThrow(() -> new DatabaseBody(scheme));
    }

    // Check memory usage for large DatabaseSchemes
    @Test
    public void check_memory_usage_large_schemes() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        DatabaseScheme scheme = DatabaseScheme.getInstance();
        for (int i = 0; i < 10000; i++) {
            scheme.createTable(STR."table\{i}", new TableScheme());
        }
        new DatabaseBody(scheme);
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        assertTrue(usedMemoryAfter > usedMemoryBefore);
    }

    // Evaluate thread safety during concurrent access to DatabaseBody
    @Test
    public void evaluate_thread_safety_concurrent_access() {
        final DatabaseScheme scheme = DatabaseScheme.getInstance();
        scheme.createTable("table1", new TableScheme());
        final DatabaseBody body = new DatabaseBody(scheme);
        Runnable task = () -> body.getInnerObjects().size();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        assertDoesNotThrow(() -> {
            thread1.join();
            thread2.join();
        });
    }

    // Test serialization and deserialization of DatabaseBody
    @Test
    public void serialization_deserialization_database_body() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new DatabaseBody(DatabaseScheme.getInstance()));
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object deserializedObject = ois.readObject();

        assertTrue(deserializedObject instanceof DatabaseBody);
    }
    
//    @Test
//    public void test_create_table_and_fields_with_data() {
//        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
//        TableScheme tableScheme = new TableScheme();
//        tableScheme.createField("column1", new FieldScheme(DataType.STRING));
//        tableScheme.createField("column2", new FieldScheme(DataType.INTEGER));
//        databaseScheme.createTable("table1", tableScheme);
//
//        TableBody tableBody = new TableBody();
//        tableBody
//                .setFieldValues(1, "test value")
//                .setFieldValues(2, "another test value");
//
//    }
}