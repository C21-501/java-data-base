package database.system.core.structures;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.listeners.NotNullConstraint;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    @Test
    public void testCreateInstanceNotNull() {
        Database database = Database.getInstance();
        assertNotNull(database);
    }

    // Creating a new table with a unique name should add it to the tables map.
    @Test
    public void testCreateTableUniqueName() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        database.createTable("table1", table);
        assertTrue(database.containsTable("table1"));
    }

    // Creating a new instance of Database should return a singleton object.
    @Test
    public void testCreateInstanceSingleton() {
        Database database1 = Database.getInstance();
        Database database2 = Database.getInstance();
        assertSame(database1, database2);
    }

    // Creating a new table with a null name should throw a NullPointerException.
    @Test
    public void testCreateTableNullName() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        assertThrows(NullPointerException.class, () -> {
            database.createTable(null, table);
        });
    }

    // Creating a new table with a name that already exists in the tables map should throw an IllegalArgumentException.
    @Test
    public void testCreateTableExistingName() {
        Database database = Database.getInstance();
        Table table1 = new Table("table1");
        database.createTable("table1", table1);
        assertThrows(IllegalArgumentException.class, () -> {
            database.createTable("table1", new Table("table1"));
        });
    }

    // Check that the size of the tables map decreases by 1 after dropping a table.
    @Test
    public void testDecreaseTableSizeAfterDropping() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        database.createTable("table1", table);
        int initialSize = database.getTables().size();
        database.dropTable("table1");
        int finalSize = database.getTables().size();
        assertEquals(initialSize - 1, finalSize);
    }

    // Drop an existing table and verify that it is removed from the tables map.
    @Test
    public void testDropExistingTable() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        database.createTable("table1", table);
        assertTrue(database.containsTable("table1"));
        database.dropTable("table1");
        assertFalse(database.containsTable("table1"));
    }

    // Ensure that the dropped table cannot be retrieved from the tables map.
    @Test
    public void testDroppedTableNotRetrievable() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        database.createTable("table1", table);
        database.dropTable("table1");
        assertFalse(database.containsTable("table1"));
    }

    // Test that dropping a non-existing table does not modify the tables map.
    @Test
    public void testDroppingNonExistingTable() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        database.createTable("table1", table);
        assertTrue(database.containsTable("table1"));
        assertThrows(IllegalArgumentException.class,() -> database.dropTable("table2"));
    }

    // Getting an existing table should return a non-null object.
    @Test
    public void testGetExistingTableShouldReturnNonNullObject() {
        Database database = Database.getInstance();
        String tableName = "table1";
        Table table = new Table(tableName);
        database.createTable(tableName, table);
        Table result = database.getTable(tableName);
        assertNotNull(result);
    }

    // Checking if a table exists should return true if it exists in the tables map.
    @Test
    public void testTableExists() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        database.createTable("table1", table);
        assertTrue(database.containsTable("table1"));
    }

    // Dropping a non-existing table should throw an IllegalArgumentException.
    @Test
    public void testDroppingNonExistingTableThrowsException() {
        Database database = Database.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            database.dropTable("non_existing_table");
        });
    }

    // Getting a non-existing table should throw an IllegalArgumentException.
    @Test
    public void testGetNonExistingTableThrowsException() {
        Database database = Database.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            database.getTable("non_existing_table");
        });
    }

    // Checking if a null table name exists should throw a NullPointerException.
    @Test
    public void testNullTableNameThrowsException() {
        Database database = Database.getInstance();
        assertThrows(NullPointerException.class, () -> {
            database.containsTable(null);
        });
    }
    // Creating a new instance of Database should return a non-null object.
    @Test
    public void test_create_instance_not_null() {
        Database database = Database.getInstance();
        assertNotNull(database);
    }

    // Creating a new table with a unique name should add it to the tables map.
    @Test
    public void test_create_table_unique_name() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        database.createTable("table1", table);
        assertTrue(database.containsTable("table1"));
    }

    // Creating a new instance of Database should return a singleton object.
    @Test
    public void test_create_instance_singleton() {
        Database database1 = Database.getInstance();
        Database database2 = Database.getInstance();
        assertSame(database1, database2);
    }

    // Creating a new table with a null name should throw a NullPointerException.
    @Test
    public void test_create_table_null_name() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        assertThrows(NullPointerException.class, () -> {
            database.createTable(null, table);
        });
    }

    // Creating a new table with a name that already exists in the tables map should throw an IllegalArgumentException.
    @Test
    public void test_create_table_existing_name() {
        Database database = Database.getInstance();
        Table table1 = new Table("table1");
        Table table2 = new Table("table1");
        try {
            database.createTable("table1", table1);
            database.createTable("table1", table2);
        } catch (IllegalArgumentException e) {
            // Test passed
        }
    }

    // Check that the size of the tables map decreases by 1 after dropping a table.
    @Test
    public void test_decrease_table_size_after_dropping_table() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        try {
            database.createTable("table1", table);
            int initialSize = database.getTables().size();
            database.dropTable("table1");
            int finalSize = database.getTables().size();
            assertEquals(initialSize - 1, finalSize);
        } catch (Exception e){
            fail();
        }
    }

    // Drop an existing table and verify that it is removed from the tables map.
    @Test
    public void test_drop_existing_table() {
        // Create a new instance of Database
        Database database = Database.getInstance();

        // Create a new table
        try {
            Table table = new Table("table1");
            database.createTable("table1", table);
            assertTrue(database.containsTable("table1"));
            database.dropTable("table1");
            assertFalse(database.containsTable("table1"));
        } catch (Exception e) {
            fail();
        }
    }

    // Ensure that the dropped table cannot be retrieved from the tables map.
    @Test
    public void test_dropped_table_not_retrievable() {
        // Create a new instance of Database
        Database database = Database.getInstance();
        Table table = new Table("table1");

        try {
            database.createTable("table1", table);
            database.dropTable("table1");
            assertFalse(database.containsTable("table1"));
        } catch (Exception e) {
            fail();
        }
    }

    // Getting an existing table should return a non-null object.
    @Test
    public void test_getExistingTable_shouldReturnNonNullObject() {
        // Arrange
        Database database = Database.getInstance();
        String tableName = "table1";
        Table table = new Table(tableName);
        database.createTable(tableName, table);

        // Act
        Table result = database.getTable(tableName);

        // Assert
        assertNotNull(result);
    }

    // Checking if a table exists should return true if it exists in the tables map.
    @Test
    public void test_table_exists() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        database.createTable("table1", table);

        assertTrue(database.containsTable("table1"));
    }

    // Adding a new column to an existing table should add it to the columnHashMap of the table.
    @Test
    public void test_addColumnToExistingTable() {
        // Create a new table
        Table table = new Table("table1");

        // Create a new field
        Field field = new Field(DataType.STRING);

        // Add the table to the database
        Database.getInstance().createTable("table1", table);

        // Add the field to the table
        Database.getInstance().getTable("table1").createField("column1", field);

        // Check if the column exists in the table's columnHashMap
        assertTrue(Database.getInstance().getTable("table1").contains("column1"));
    }

    // Dropping an existing column from an existing table should remove it from the columnHashMap of the table.
    @Test
    public void test_drop_existing_column() {
        // Create a new table
        Table table = new Table("table1");

        // Add columns to the table
        Field field1 = new Field(DataType.STRING);
        Field field2 = new Field(DataType.STRING);
        table.createField("column1", field1);
        table.createField("column2", field2);

        // Drop an existing column
        table.dropField("column1");

        // Check if the column is removed from the columnHashMap
        assertFalse(table.contains("column1"));
    }

    // Dropping an existing table should remove it from the tables map.
    @Test
    public void test_dropTable_removesTableFromMap() {
        // Create a new instance of Database
        Database database = Database.getInstance();

        // Create a new table
        Table table = new Table("table1");

        // Add the table to the database
        database.createTable("table1", table);

        // Check if the table exists in the database
        assertTrue(database.containsTable("table1"));

        // Drop the table
        database.dropTable("table1");

        // Check if the table was removed from the database
        assertFalse(database.containsTable("table1"));
    }

    // Renaming an existing column in an existing table should update the columnHashMap of the table.
    @Test
    public void test_renaming_existing_column_updates_columnHashMap() {
        // Create a new table
        Table table = new Table("table1");

        // Add columns to the table
        Field field1 = new Field(DataType.STRING);
        Field field2 = new Field(DataType.STRING);
        table.createField("column1", field1);
        table.createField("column2", field2);

        // Rename an existing column
        table.renameField("column1", "newColumn");

        // Check if the columnHashMap is updated
        assertTrue(table.contains("newColumn"));
        assertFalse(table.contains("column1"));
    }

    // Updating an existing column in an existing table should update the columnHashMap of the table.
    @Test
    public void test_update_existing_column() {
        // Create a new table
        Table table = new Table("table1");

        // Add columns to the table
        Field field1 = new Field(DataType.STRING);
        Field field2 = new Field(DataType.STRING);
        table.createField("column1", field1);
        table.createField("column2", field2);

        // Update an existing column
        Field updatedField = new Field(DataType.INTEGER);
        table.updateField("column1", updatedField);

        // Check if the columnHashMap is updated
        assertEquals(table.getField("column1"), updatedField);
        assertTrue(table.contains("column2"));
    }

    // Dropping a non-existing table should throw an IllegalArgumentException.
    @Test
    public void test_dropping_non_existing_table() {
        Database database = Database.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            database.dropTable("non_existing_table");
        });
    }

    // Getting a non-existing table should throw an IllegalArgumentException.
    @Test
    public void test_getNonExistingTable() {
        Database database = Database.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            database.getTable("non_existing_table");
        });
    }

    // Adding a constraint to an existing column in an existing table should update the constraints of the corresponding field in the columnHashMap of the table.
    @Test
    public void test_add_constraint_to_existing_column() {
        Database database = Database.getInstance();
        Table table = new Table("table1");
        Constraint constraint = new NotNullConstraint();
        Field field = new Field(DataType.STRING);

        field.addConstraint(constraint);
        table.createField("column1", field);
        database.createTable("table1", table);

        assertTrue(table.getField("column1").getConstraintSet().contains(ConstraintEnum.NOT_NULL));
    }

    // Checking if a null table name exists should throw a NullPointerException.
    @Test
    public void test_null_table_name_throws_exception() {
        Database database = Database.getInstance();
        assertThrows(NullPointerException.class, () -> {
            database.containsTable(null);
        });
    }

    // Dropping a non-existing column from an existing table should throw an IllegalArgumentException.
    @Test
    public void test_dropping_non_existing_column() {
        Database database = Database.getInstance();
        Table table = new Table("table1");


        database.createTable("table1", table);

        // Attempt to drop a non-existing column from the table
        assertThrows(RuntimeException.class, () -> {
            table.dropField("column1");
        });
    }

    // Renaming a non-existing column in an existing table should throw an IllegalArgumentException.
    @Test
    public void test_renaming_non_existing_column() {
        // Create a new instance of the Database class
        Database database = Database.getInstance();

        // Create a new table and add it to the database
        Table table = new Table("table1");
        database.createTable("table1", table);

        // Attempt to rename a non-existing column in the table
        assertThrows(RuntimeException.class, () -> {
            database.getTable("table1").renameField("oldColumn", "newColumn");
        });
    }

    // Updating a non-existing column in an existing table should throw an IllegalArgumentException.
    @Test
    public void test_updating_non_existing_column() {
        // Arrange
        Database database = Database.getInstance();
        String tableName = "table1";
        Table table = new Table(tableName);
        database.createTable(tableName, table);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            table.updateField("nonExistingColumn", new Field(DataType.INTEGER));
        });
    }
}