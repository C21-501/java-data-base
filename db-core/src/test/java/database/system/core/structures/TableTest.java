package database.system.core.structures;

import database.api.utils.OUTPUT_TYPE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {
    private Table table;

    @BeforeEach
    public void setUp() {
        table = new Table();
    }

    @Test
    public void test_create_table_with_constraints() {
        table.create("id", "INTEGER", new String[]{"PRIMARY KEY", "NOT NULL"});
        table.create("name", "STRING", new String[]{"NOT NULL", "UNIQUE"});
        table.create("age", "INTEGER", new String[]{"CHECK (age > 18)"});
        table.create("email", "STRING", new String[]{"NOT NULL", "UNIQUE", "CHECK (email LIKE '%@%')"});
        table.create("status", "BOOLEAN", new String[]{"DEFAULT true"});

        assertTrue(table.contains("id"));
        assertTrue(table.contains("name"));
        assertTrue(table.contains("age"));
        assertTrue(table.contains("email"));
        assertTrue(table.contains("status"));
    }

    @Test
    public void test_insert_valid_data() {
        test_create_table_with_constraints(); // Ensure table is created with constraints
        try {
            table.insert(
                    Arrays.asList("id", "name", "age", "email", "status"),
                    new Object[]{1, "'John Doe'", 25, "'john.doe@example.com'", true}
            );
            table.insert(
                    Arrays.asList("id", "name", "age", "email", "status"),
                    new Object[]{2, "'Jane Smith'", 30, "'jane.smith@example.com'", false}
            );
        } catch (Exception e) {
            fail("Exception thrown for valid data insertion: %s".formatted(e.getMessage()));
        }

        // Verify data insertion
        Response response = table.select("TestTable", Arrays.asList("id", "name", "age", "email", "status"));
        assertEquals(2, response.get("id").size());
    }

    @Test
    public void test_insert_invalid_data_not_null_constraint() {
        test_create_table_with_constraints(); // Ensure table is created with constraints
        // Attempt to insert null into NOT NULL column
        assertThrows(
                RuntimeException.class, () -> table.insert(
                Arrays.asList("id", "name", "age", "email", "status"),
                        new Object[]{3, null, 22, "someone@example.com", true}
                ), "Expected exception for NOT NULL constraint violation."
        );
    }

    @Test
    public void test_insert_invalid_data_unique_constraint() {
        test_create_table_with_constraints(); // Ensure table is created with constraints
        table.insert(
                Arrays.asList("id", "name", "age", "email", "status"),
                new Object[]{4, "'John Doe'", 28, "'john.doe@example.com'", false}
        );
        // Attempt to insert duplicate email
        assertThrows(
                RuntimeException.class,
                () -> table.insert(
                        Arrays.asList("id", "name", "age", "email", "status"),
                        new Object[]{4, "'John Doe'", 28, "'john.doe@example.com'", false}
                ), "Expected exception for UNIQUE constraint violation."
        );
    }

    @Test
    public void test_insert_invalid_data_check_constraint() {
        test_create_table_with_constraints(); // Ensure table is created with constraints
        // Attempt to insert age < 18
        assertThrows(Exception.class, () -> table.insert(
                Arrays.asList("id", "name", "age", "email", "status"),
                        new Object[]{5, "'Young Person'", 16, "young.person@example.com", true}
                ),
                "Expected exception for CHECK constraint violation.");
    }

    @Test
    public void test_default_constraint() {
        test_create_table_with_constraints(); // Ensure table is created with constraints

        try {
            table.insert(
                    List.of("id", "name", "age", "email"),
                    new Object[]{6, "'Default Status User'", 35, "'default.status@example.com'"}
            );
            table.insert(
                    List.of("id", "name", "age", "email"),
                    new Object[]{7, "'New Status User'", 35, "'new.status@example.com'"}
            );
        } catch (Exception e) {
            fail("Exception thrown for DEFAULT constraint: %s".formatted(e.getMessage()));
        }

        // Verify default value insertion
        Response response = table.select("TestTable", Arrays.asList("id", "name", "age", "email", "status"));
        response.print(OUTPUT_TYPE.CONSOLE, Optional.empty());
        assertEquals("'Default Status User'", response.get("name", 0));
    }

    @Test
    public void test_delete_data_with_condition() {
        test_insert_valid_data(); // Ensure table is populated with data
        Response response = table.select("TestTable", List.of("id", "name", "age", "email", "status"));
        response.print(OUTPUT_TYPE.CONSOLE, Optional.empty());
        try {
            table.delete("age > 28");
        } catch (Exception e) {
            fail("Exception thrown during delete operation: %s".formatted(e.getMessage()));
        }

        // Verify data deletion based on condition
        response = table.select("TestTable", List.of("id", "name", "age", "email", "status"));
        response.print(OUTPUT_TYPE.CONSOLE, Optional.empty());

        assertEquals(1, response.get("id",0));
    }

    @Test
    public void test_update_data_with_condition() {
        test_insert_valid_data(); // Ensure table is populated with data

        try {
            table.update(List.of("name = 'Updated Name'"), "id = 1");
        } catch (Exception e) {
            fail("Exception thrown during update operation: %s".formatted(e.getMessage()));
        }

        // Verify data update based on condition
        Response response = table.select("TestTable", Arrays.asList("id", "name", "age", "email", "status"));
        assertEquals("'Updated Name'",response.get("name",0));
    }
}
