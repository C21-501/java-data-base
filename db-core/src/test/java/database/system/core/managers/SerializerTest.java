package database.system.core.managers;

import database.system.core.structures.bodies.FieldBody;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class SerializerTest {
//    // Serialize a FieldBody object with valid data and write to file
//    @Test
//    public void serialize_valid_data() throws IOException {
//        FieldBody fieldBody = new FieldBody();
//        fieldBody.setFieldId(1);
//        fieldBody.getObjectList().add("Test Data");
//        String filePath = "testfile.ser";
//        ColumnSerializer.writeToFile(fieldBody, filePath);
//        File file = new File(filePath);
//        assertTrue(file.exists());
//        file.delete();
//    }
//
//    // Deserialize a FieldBody object from a file and verify data integrity
//    @Test
//    public void deserialize_verify_integrity() throws IOException, ClassNotFoundException {
//        FieldBody fieldBody = new FieldBody();
//        fieldBody.setFieldId(1);
//        fieldBody.getObjectList().add("Test Data");
//        String filePath = "testfile.ser";
//        ColumnSerializer.writeToFile(fieldBody, filePath);
//        FieldBody deserialized = (FieldBody) ColumnSerializer.readFromFile(filePath);
//        assertEquals(fieldBody.getFieldId(), deserialized.getFieldId());
//        assertEquals(fieldBody.getObjectList(), deserialized.getObjectList());
//        new File(filePath).delete();
//    }
//
//    // Handle serialization and deserialization of an empty FieldBody object
//    @Test
//    public void serialize_deserialize_empty_object() throws IOException, ClassNotFoundException {
//        FieldBody fieldBody = new FieldBody();
//        String filePath = "empty.ser";
//        ColumnSerializer.writeToFile(fieldBody, filePath);
//        FieldBody deserialized = (FieldBody) ColumnSerializer.readFromFile(filePath);
//        assertNotNull(deserialized);
//        assertTrue(deserialized.getObjectList().isEmpty());
//        new File(filePath).delete();
//    }
//
//    // Serialize and deserialize a FieldBody object with multiple entries in objectList
//    @Test
//    public void serialize_deserialize_multiple_entries() throws IOException, ClassNotFoundException {
//        FieldBody fieldBody = new FieldBody();
//        fieldBody.getObjectList().add("Data1");
//        fieldBody.getObjectList().add("Data2");
//        String filePath = "multientry.ser";
//        ColumnSerializer.writeToFile(fieldBody, filePath);
//        FieldBody deserialized = (FieldBody) ColumnSerializer.readFromFile(filePath);
//        assertEquals(2, deserialized.getObjectList().size());
//        assertTrue(deserialized.getObjectList().contains("Data1"));
//        assertTrue(deserialized.getObjectList().contains("Data2"));
//        new File(filePath).delete();
//    }
//
//    // Attempt to serialize a FieldBody object with null values in objectList
//    @Test
//    public void serialize_with_null_values() throws IOException {
//        FieldBody fieldBody = new FieldBody();
//        fieldBody.getObjectList().add(null);
//        String filePath = "nullvalue.ser";
//        ColumnSerializer.writeToFile(fieldBody, filePath);
//        File file = new File(filePath);
//        assertTrue(file.exists());
//        file.delete();
//    }
//
//    // Attempt to deserialize from a non-existent file path
//    @Test
//    public void deserialize_non_existent_file() {
//        String filePath = "nonexistent.ser";
//        assertThrows(IOException.class, () -> {
//            ColumnSerializer.readFromFile(filePath);
//        });
//    }
//
//    // Attempt to write to a file with restricted permissions
//    @Test
//    public void write_to_restricted_file() {
//        String filePath = "/root/restricted.ser";
//        assertThrows(IOException.class, () -> {
//            FieldBody fieldBody = new FieldBody();
//            ColumnSerializer.writeToFile(fieldBody, filePath);
//        });
//    }
//
//    // Deserialize data that was not serialized as a FieldBody object
//    @Test
//    public void deserialize_non_fieldbody_data() throws IOException {
//        String filePath = "wrongdata.ser";
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
//            oos.writeObject("This is not a FieldBody object");
//        }
//        assertThrows(ClassCastException.class, () -> {
//            ColumnSerializer.readFromFile(filePath);
//        });
//        new File(filePath).delete();
//    }
//
//    // Handle file paths with special characters or invalid formats
//    @Test
//    public void handle_special_character_paths() {
//        String filePath = "invalid/:*?.ser";
//        assertThrows(IOException.class, () -> {
//            FieldBody fieldBody = new FieldBody();
//            ColumnSerializer.writeToFile(fieldBody, filePath);
//        });
//    }
//
//    // Verify the behavior when file I/O operations throw IOException
//    @Test
//    public void handle_io_exception_in_file_operations() {
//        String filePath = "/dev/null/testfile.ser"; // Typically unwritable location
//        assertThrows(IOException.class, () -> {
//            FieldBody fieldBody = new FieldBody();
//            ColumnSerializer.writeToFile(fieldBody, filePath);
//            ColumnSerializer.readFromFile(filePath);
//        });
//    }
//
//    // Check serialization and deserialization with large data volumes in objectList
//    @Test
//    public void large_data_volume_serialization_deserialization() throws IOException, ClassNotFoundException {
//        FieldBody fieldBody = new FieldBody();
//        for (int i = 0; i < 10000; i++) {
//            fieldBody.getObjectList().add(STR."Data\{i}");
//        }
//        String filePath = "largevolume.ser";
//        ColumnSerializer.writeToFile(fieldBody, filePath);
//        FieldBody deserialized = (FieldBody) ColumnSerializer.readFromFile(filePath);
//        assertEquals(10000, deserialized.getObjectList().size());
//        assertEquals("Data9999", deserialized.getObjectList().get(9999));
//        new File(filePath).delete();
//    }
//
//    // Assess the performance of serialization and deserialization processes
//    @Test
//    public void assess_performance_serialization_deserialization() throws IOException, ClassNotFoundException {
//        long startTime, endTime, duration;
//        // Test serialization performance
//        FieldBody fieldBody = new FieldBody();
//        for (int i = 0; i < 5000; i++) {
//            fieldBody.getObjectList().add(STR."PerfData\{i}");
//        }
//        String filePath = "performance.ser";
//        startTime = System.currentTimeMillis();
//        ColumnSerializer.writeToFile(fieldBody, filePath);
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        assertTrue(duration < 1000); // Expecting it to be less than 1000 milliseconds
//        // Test deserialization performance
//        startTime = System.currentTimeMillis();
//        ColumnSerializer.readFromFile(filePath);
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        assertTrue(duration < 1000); // Expecting it to be less than 1000 milliseconds
//        new File(filePath).delete();
//
//    }
}