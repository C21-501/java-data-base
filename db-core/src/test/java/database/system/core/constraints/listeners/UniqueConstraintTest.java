package database.system.core.constraints.listeners;

import database.system.core.structures.bodies.TableBody;
import database.system.core.structures.schemes.FieldScheme;
import database.system.core.structures.schemes.TableScheme;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import java.io.NotSerializableException;
import static org.junit.jupiter.api.Assertions.*;


public class UniqueConstraintTest {
    // Verify that unique values pass the check
    @Test
    public void test_unique_values_pass_check() {
        TableScheme scheme = new TableScheme("test");
        scheme.createField("test1", new FieldScheme(DataType.STRING));
        scheme.getField("test1").addConstraint(new UniqueConstraint());

        TableBody tableBody = new TableBody(scheme);
        tableBody.setFieldValues("value1");
        tableBody.setFieldValues("value2");
        assertThrows(RuntimeException.class,()->tableBody.setFieldValues("value2"));
    }

    /*// Ensure that multiple fields with unique values are correctly validated
    @Test
    public void test_multiple_fields_unique_validation() {
        TableScheme scheme = new TableScheme("test");
        TableBody tableBody = new TableBody(scheme);
        UniqueConstraint constraint = new UniqueConstraint(tableBody);
        tableBody.setFieldValues("value1", "value2");
        assertTrue(constraint.check(, 1, "value3", ));
    }

    // Check that the constraint returns true when no values are present in the field bodies
    @Test
    public void test_true_when_no_values_present() {
        TableScheme scheme = new TableScheme("test");
        TableBody tableBody = new TableBody(scheme);
        UniqueConstraint constraint = new UniqueConstraint(tableBody);
        assertTrue(constraint.check(, 0, "value1", ));
    }

    // Test with null value input to see if it handles or throws an exception
    @Test
    public void test_null_value_input_handling() {
        TableScheme scheme = new TableScheme("test");
        TableBody tableBody = new TableBody(scheme);
        UniqueConstraint constraint = new UniqueConstraint(tableBody);
        assertThrows(NullPointerException.class, () -> constraint.check(, 0, null, ));
    }

    // Check behavior when index is out of bounds of the object list
    @Test
    public void test_index_out_of_bounds_behavior() {
        TableScheme scheme = new TableScheme("test");
        TableBody tableBody = new TableBody(scheme);
        UniqueConstraint constraint = new UniqueConstraint(tableBody);
        assertThrows(IndexOutOfBoundsException.class, () -> constraint.check(, 1, "value1", ));
    }

    // Validate behavior when the field body list is empty
    @Test
    public void test_empty_field_body_list_behavior() {
        TableScheme scheme = new TableScheme("test");
        TableBody tableBody = new TableBody(scheme);
        UniqueConstraint constraint = new UniqueConstraint(tableBody);
        assertTrue(constraint.check(, 0, "value1", ));
    }

    // Test the constraint with extreme values like very large numbers or special characters
    @Test
    public void test_extreme_values_handling() {
        TableScheme scheme = new TableScheme("test");
        TableBody tableBody = new TableBody(scheme);
        UniqueConstraint constraint = new UniqueConstraint(tableBody);
        tableBody.setFieldValues(Integer.MAX_VALUE);
        assertTrue(constraint.check(, 0, Integer.MIN_VALUE, ));
        tableBody.setFieldValues("special@#&*!");
        assertTrue(constraint.check(, 0, "normalValue", ));
    }

    // Assess performance with a large number of field bodies and values
    @Test
    public void test_performance_large_data() {
        TableScheme scheme = new TableScheme("test");
        TableBody tableBody = new TableBody(scheme);
        UniqueConstraint constraint = new UniqueConstraint(tableBody);
        for (int i = 0; i < 10000; i++) {
            tableBody.setFieldValues(STR."value\{i}");
        }
        long startTime = System.currentTimeMillis();
        assertTrue(constraint.check(, 0, "newUniqueValue", ));
        long endTime = System.currentTimeMillis();
        assertTrue((endTime - startTime) < 1000); // Check if the operation takes less than 1 second
    }

    // Check how the constraint handles concurrent modifications to the field body list
    @Test
    public void test_concurrent_modifications_handling() {
        TableScheme scheme = new TableScheme("test");
        final TableBody tableBody = new TableBody(scheme);
        final UniqueConstraint constraint = new UniqueConstraint(tableBody);
        Thread thread1 = new Thread(() -> tableBody.setFieldValues("value1"));
        Thread thread2 = new Thread(() -> assertTrue(constraint.check(, 0, "value2", )));
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

    // Evaluate the constraint's response to non-serializable objects as values
    @Test
    public void test_non_serializable_objects_response() {
        TableScheme scheme = new TableScheme("test");
        TableBody tableBody = new TableBody(scheme);
        UniqueConstraint constraint = new UniqueConstraint(tableBody);
        Object nonSerializableObject = new Object();
        assertThrows(NotSerializableException.class, () -> tableBody.setFieldValues(nonSerializableObject));
    }*/

}