package database.system.core.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ValueTest {
    // Ensure that Value objects with different byte arrays are not equal
    @Test
    public void test_different_byte_arrays() {
        byte[] data1 = {1, 2, 3};
        byte[] data2 = {4, 5, 6};
        Value value1 = Value.of(data1);
        Value value2 = Value.of(data2);
        assertNotEquals(value1, value2);
    }

    // Confirm that the constructor correctly assigns a unique ID from an atomic generator
    @Test
    public void test_unique_id_assignment() {
        byte[] data = {1, 2, 3};
        Value value1 = new Value(data);
        Value value2 = new Value(data);
        assertNotEquals(value1.getId(), value2.getId());
    }

    // Check that the static factory method 'of' correctly creates a new Value instance
    @Test
    public void test_factory_method_creation() {
        byte[] data = {1, 2, 3};
        Value value = Value.of(data);
        assertNotNull(value);
        assertEquals(data, value.getObject());
    }

    // Test serialization and deserialization of the Value object
    @Test
    public void test_serialization_deserialization() throws IOException, ClassNotFoundException {
        byte[] data = {1, 2, 3};
        Value originalValue = new Value(data);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(originalValue);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Value deserializedValue = (Value) ois.readObject();

        assertEquals(originalValue, deserializedValue);
    }

    // Verify behavior when byte arrays have leading zeros which might affect comparison
    @Test
    public void test_leading_zeros_comparison() {
        byte[] data1 = {0, 1, 2};
        byte[] data2 = {1, 2};
        Value value1 = new Value(data1);
        Value value2 = new Value(data2);
        assertNotEquals(value1, value2);
    }

    // Check the behavior when two Value objects have the same ID but different byte arrays
    @Test
    public void test_same_id_different_bytes() {
        byte[] data1 = {1, 2, 3};
        byte[] data2 = {4, 5, 6};
        int id = 100;
        Value value1 = new Value(id, data1);
        Value value2 = new Value(id, data2);
        assertNotEquals(value1, value2);
    }

    // Assess thread safety when multiple threads are creating Value instances simultaneously
    @Test
    public void test_thread_safety_on_creation() throws InterruptedException {
        Set<Integer> ids = ConcurrentHashMap.newKeySet();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                Value value = new Value(new byte[]{(byte) Thread.currentThread().getId()});
                ids.add(value.getId());
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(100, ids.size());
    }

}