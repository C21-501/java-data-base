package database.system.core.structures;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.listeners.NotNullConstraint;
import database.system.core.structures.schemes.DatabaseScheme;
import database.system.core.structures.schemes.FieldScheme;
import database.system.core.structures.schemes.TableScheme;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseSchemeTest {
    @Test
    public void testCreateInstanceNotNull() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        assertNotNull(databaseScheme);
    }

    // Creating a new table with a unique name should add it to the tables map.
    @Test
    public void testCreateTableUniqueName() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        databaseScheme.createTable("table1", tableScheme);
        assertTrue(databaseScheme.containsTable("table1"));
    }

    // Creating a new instance of Database should return a singleton object.
    @Test
    public void testCreateInstanceSingleton() {
        DatabaseScheme databaseScheme1 = DatabaseScheme.getInstance();
        DatabaseScheme databaseScheme2 = DatabaseScheme.getInstance();
        assertSame(databaseScheme1, databaseScheme2);
    }

    // Creating a new table with a null name should throw a NullPointerException.
    @Test
    public void testCreateTableNullName() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        assertThrows(NullPointerException.class, () -> {
            databaseScheme.createTable(null, tableScheme);
        });
    }

    // Creating a new table with a name that already exists in the tables map should throw an IllegalArgumentException.
    @Test
    public void testCreateTableExistingName() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme1 = new TableScheme("table1");
        databaseScheme.createTable("table1", tableScheme1);
        assertThrows(IllegalArgumentException.class, () -> {
            databaseScheme.createTable("table1", new TableScheme("table1"));
        });
    }

    // Check that the size of the tables map decreases by 1 after dropping a table.
    @Test
    public void testDecreaseTableSizeAfterDropping() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        databaseScheme.createTable("table1", tableScheme);
        int initialSize = databaseScheme.getTables().size();
        databaseScheme.dropTable("table1");
        int finalSize = databaseScheme.getTables().size();
        assertEquals(initialSize - 1, finalSize);
    }

    // Drop an existing table and verify that it is removed from the tables map.
    @Test
    public void testDropExistingTable() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        databaseScheme.createTable("table1", tableScheme);
        assertTrue(databaseScheme.containsTable("table1"));
        databaseScheme.dropTable("table1");
        assertFalse(databaseScheme.containsTable("table1"));
    }

    // Ensure that the dropped table cannot be retrieved from the tables map.
    @Test
    public void testDroppedTableNotRetrievable() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        databaseScheme.createTable("table1", tableScheme);
        databaseScheme.dropTable("table1");
        assertFalse(databaseScheme.containsTable("table1"));
    }

    // Test that dropping a non-existing table does not modify the tables map.
    @Test
    public void testDroppingNonExistingTable() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        databaseScheme.createTable("table1", tableScheme);
        assertTrue(databaseScheme.containsTable("table1"));
        assertThrows(IllegalArgumentException.class,() -> databaseScheme.dropTable("table2"));
    }

    // Getting an existing table should return a non-null object.
    @Test
    public void testGetExistingTableShouldReturnNonNullObject() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        String tableName = "table1";
        TableScheme tableScheme = new TableScheme(tableName);
        databaseScheme.createTable(tableName, tableScheme);
        TableScheme result = databaseScheme.getTable(tableName);
        assertNotNull(result);
    }

    // Checking if a table exists should return true if it exists in the tables map.
    @Test
    public void testTableExists() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        databaseScheme.createTable("table1", tableScheme);
        assertTrue(databaseScheme.containsTable("table1"));
    }

    // Dropping a non-existing table should throw an IllegalArgumentException.
    @Test
    public void testDroppingNonExistingTableThrowsException() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            databaseScheme.dropTable("non_existing_table");
        });
    }

    // Getting a non-existing table should throw an IllegalArgumentException.
    @Test
    public void testGetNonExistingTableThrowsException() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            databaseScheme.getTable("non_existing_table");
        });
    }

    // Checking if a null table name exists should throw a NullPointerException.
    @Test
    public void testNullTableNameThrowsException() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        assertThrows(NullPointerException.class, () -> {
            databaseScheme.containsTable(null);
        });
    }
    // Creating a new instance of Database should return a non-null object.
    @Test
    public void test_create_instance_not_null() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        assertNotNull(databaseScheme);
    }

    // Creating a new table with a unique name should add it to the tables map.
    @Test
    public void test_create_table_unique_name() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        databaseScheme.createTable("table1", tableScheme);
        assertTrue(databaseScheme.containsTable("table1"));
    }

    // Creating a new instance of Database should return a singleton object.
    @Test
    public void test_create_instance_singleton() {
        DatabaseScheme databaseScheme1 = DatabaseScheme.getInstance();
        DatabaseScheme databaseScheme2 = DatabaseScheme.getInstance();
        assertSame(databaseScheme1, databaseScheme2);
    }

    // Creating a new table with a null name should throw a NullPointerException.
    @Test
    public void test_create_table_null_name() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        assertThrows(NullPointerException.class, () -> {
            databaseScheme.createTable(null, tableScheme);
        });
    }

    // Creating a new table with a name that already exists in the tables map should throw an IllegalArgumentException.
    @Test
    public void test_create_table_existing_name() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme1 = new TableScheme("table1");
        TableScheme tableScheme2 = new TableScheme("table1");
        try {
            databaseScheme.createTable("table1", tableScheme1);
            databaseScheme.createTable("table1", tableScheme2);
        } catch (IllegalArgumentException e) {
            // Test passed
        }
    }

    // Check that the size of the tables map decreases by 1 after dropping a table.
    @Test
    public void test_decrease_table_size_after_dropping_table() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        try {
            databaseScheme.createTable("table1", tableScheme);
            int initialSize = databaseScheme.getTables().size();
            databaseScheme.dropTable("table1");
            int finalSize = databaseScheme.getTables().size();
            assertEquals(initialSize - 1, finalSize);
        } catch (Exception e){
            fail();
        }
    }

    // Drop an existing table and verify that it is removed from the tables map.
    @Test
    public void test_drop_existing_table() {
        // Create a new instance of Database
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();

        // Create a new table
        try {
            TableScheme tableScheme = new TableScheme("table1");
            databaseScheme.createTable("table1", tableScheme);
            assertTrue(databaseScheme.containsTable("table1"));
            databaseScheme.dropTable("table1");
            assertFalse(databaseScheme.containsTable("table1"));
        } catch (Exception e) {
            fail();
        }
    }

    // Ensure that the dropped table cannot be retrieved from the tables map.
    @Test
    public void test_dropped_table_not_retrievable() {
        // Create a new instance of Database
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");

        try {
            databaseScheme.createTable("table1", tableScheme);
            databaseScheme.dropTable("table1");
            assertFalse(databaseScheme.containsTable("table1"));
        } catch (Exception e) {
            fail();
        }
    }

    // Getting an existing table should return a non-null object.
    @Test
    public void test_getExistingTable_shouldReturnNonNullObject() {
        // Arrange
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        String tableName = "table1";
        TableScheme tableScheme = new TableScheme(tableName);
        databaseScheme.createTable(tableName, tableScheme);

        // Act
        TableScheme result = databaseScheme.getTable(tableName);

        // Assert
        assertNotNull(result);
    }

    // Checking if a table exists should return true if it exists in the tables map.
    @Test
    public void test_table_exists() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        databaseScheme.createTable("table1", tableScheme);

        assertTrue(databaseScheme.containsTable("table1"));
    }

    // Adding a new column to an existing table should add it to the columnHashMap of the table.
    @Test
    public void test_addColumnToExistingTable() {
        // Create a new table
        TableScheme tableScheme = new TableScheme("table1");

        // Create a new field
        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);

        // Add the table to the database
        DatabaseScheme.getInstance().createTable("table1", tableScheme);

        // Add the field to the table
        DatabaseScheme.getInstance().getTable("table1").createField("column1", fieldScheme);

        // Check if the column exists in the table's columnHashMap
        assertTrue(DatabaseScheme.getInstance().getTable("table1").contains("column1"));
    }

    // Dropping an existing column from an existing table should remove it from the columnHashMap of the table.
    @Test
    public void test_drop_existing_column() {
        // Create a new table
        TableScheme tableScheme = new TableScheme("table1");

        // Add columns to the table
        FieldScheme fieldScheme1 = new FieldScheme(DataType.STRING);
        FieldScheme fieldScheme2 = new FieldScheme(DataType.STRING);
        tableScheme.createField("column1", fieldScheme1);
        tableScheme.createField("column2", fieldScheme2);

        // Drop an existing column
        tableScheme.dropField("column1");

        // Check if the column is removed from the columnHashMap
        assertFalse(tableScheme.contains("column1"));
    }

    // Dropping an existing table should remove it from the tables map.
    @Test
    public void test_dropTable_removesTableFromMap() {
        // Create a new instance of Database
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();

        // Create a new table
        TableScheme tableScheme = new TableScheme("table1");

        // Add the table to the database
        databaseScheme.createTable("table1", tableScheme);

        // Check if the table exists in the database
        assertTrue(databaseScheme.containsTable("table1"));

        // Drop the table
        databaseScheme.dropTable("table1");

        // Check if the table was removed from the database
        assertFalse(databaseScheme.containsTable("table1"));
    }

    // Renaming an existing column in an existing table should update the columnHashMap of the table.
    @Test
    public void test_renaming_existing_column_updates_columnHashMap() {
        // Create a new table
        TableScheme tableScheme = new TableScheme("table1");

        // Add columns to the table
        FieldScheme fieldScheme1 = new FieldScheme(DataType.STRING);
        FieldScheme fieldScheme2 = new FieldScheme(DataType.STRING);
        tableScheme.createField("column1", fieldScheme1);
        tableScheme.createField("column2", fieldScheme2);

        // Rename an existing column
        tableScheme.renameField("column1", "newColumn");

        // Check if the columnHashMap is updated
        assertTrue(tableScheme.contains("newColumn"));
        assertFalse(tableScheme.contains("column1"));
    }

    // Updating an existing column in an existing table should update the columnHashMap of the table.
    @Test
    public void test_update_existing_column() {
        // Create a new table
        TableScheme tableScheme = new TableScheme("table1");

        // Add columns to the table
        FieldScheme fieldScheme1 = new FieldScheme(DataType.STRING);
        FieldScheme fieldScheme2 = new FieldScheme(DataType.STRING);
        tableScheme.createField("column1", fieldScheme1);
        tableScheme.createField("column2", fieldScheme2);

        // Update an existing column
        FieldScheme updatedFieldScheme = new FieldScheme(DataType.INTEGER);
        tableScheme.updateField("column1", updatedFieldScheme);

        // Check if the columnHashMap is updated
        assertEquals(tableScheme.getField("column1"), updatedFieldScheme);
        assertTrue(tableScheme.contains("column2"));
    }

    // Dropping a non-existing table should throw an IllegalArgumentException.
    @Test
    public void test_dropping_non_existing_table() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            databaseScheme.dropTable("non_existing_table");
        });
    }

    // Getting a non-existing table should throw an IllegalArgumentException.
    @Test
    public void test_getNonExistingTable() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            databaseScheme.getTable("non_existing_table");
        });
    }

    // Adding a constraint to an existing column in an existing table should update the constraints of the corresponding field in the columnHashMap of the table.
    @Test
    public void test_add_constraint_to_existing_column() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");
        Constraint constraint = new NotNullConstraint();
        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);

        fieldScheme.addConstraint(constraint);
        tableScheme.createField("column1", fieldScheme);
        databaseScheme.createTable("table1", tableScheme);

        assertTrue(tableScheme.getField("column1").getConstraintSet().contains(ConstraintEnum.NOT_NULL));
    }

    // Checking if a null table name exists should throw a NullPointerException.
    @Test
    public void test_null_table_name_throws_exception() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        assertThrows(NullPointerException.class, () -> {
            databaseScheme.containsTable(null);
        });
    }

    // Dropping a non-existing column from an existing table should throw an IllegalArgumentException.
    @Test
    public void test_dropping_non_existing_column() {
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        TableScheme tableScheme = new TableScheme("table1");


        databaseScheme.createTable("table1", tableScheme);

        // Attempt to drop a non-existing column from the table
        assertThrows(RuntimeException.class, () -> {
            tableScheme.dropField("column1");
        });
    }

    // Renaming a non-existing column in an existing table should throw an IllegalArgumentException.
    @Test
    public void test_renaming_non_existing_column() {
        // Create a new instance of the Database class
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();

        // Create a new table and add it to the database
        TableScheme tableScheme = new TableScheme("table1");
        databaseScheme.createTable("table1", tableScheme);

        // Attempt to rename a non-existing column in the table
        assertThrows(RuntimeException.class, () -> {
            databaseScheme.getTable("table1").renameField("oldColumn", "newColumn");
        });
    }

    // Updating a non-existing column in an existing table should throw an IllegalArgumentException.
    @Test
    public void test_updating_non_existing_column() {
        // Arrange
        DatabaseScheme databaseScheme = DatabaseScheme.getInstance();
        String tableName = "table1";
        TableScheme tableScheme = new TableScheme(tableName);
        databaseScheme.createTable(tableName, tableScheme);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            tableScheme.updateField("nonExistingColumn", new FieldScheme(DataType.INTEGER));
        });
    }
}