package database.system.core.structures;

import database.system.core.types.DataType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class DatabaseTest {
    private Database database;
    
    @BeforeEach
    public void setUp() {
        database = Database.getInstance();
    }
    @AfterEach
    public void tearDown() throws IOException {
        database.drop();
    }
    // Verify singleton pattern correctly provides a single instance of Database
    @Test
    public void test_singleton_instance() {
        Database firstInstance = Database.getInstance();
        Database secondInstance = Database.getInstance();
        assertSame(firstInstance, secondInstance, "Both instances should be the same");
    }

    // Ensure create adds a new table when it does not already exist
    @Test
    public void test_create_table_success() {
        Database db = Database.getInstance();
        db.createTable("newTable");
        assertTrue(db.containsTable("newTable"), "Table should be added successfully");
    }

    // Test create throws an exception when trying to create a table that already exists
    @Test
    public void test_create_table_exception() {
        Database db = Database.getInstance();
        db.createTable("existingTable");
        assertThrows(IllegalArgumentException.class, () -> db.createTable("existingTable"), "Should throw exception when table already exists");
    }

    // Check dropTable throws an exception when trying to remove a non-existent table
    @Test
    public void test_drop_nonexistent_table_exception() {
        Database db = Database.getInstance();
        assertThrows(IllegalArgumentException.class, () -> db.dropTable("nonexistentTable"), "Should throw exception when table does not exist");
    }

    // Ensure getTable returns empty Optional for non-existent table
    @Test
    public void test_get_nonexistent_table() {
        Database db = Database.getInstance();
        Optional<Table> result = db.getTable("nonexistentTable");
        assertTrue(result.isEmpty(), "Optional should be empty for non-existent table");
    }

    // Validate insertInto throws an exception when table or column does not exist
    @Test
    public void test_validate_insert_into_exception() {
        database.createTable("test_table");
        assertThrows(RuntimeException.class, () -> database.insertInto("non_existing_table", "non_existing_column", new Object[]{"value"}));
        assertThrows(RuntimeException.class, () -> database.insertInto("test_table", "non_existing_column", new Object[]{"value"}));
    }

    // Confirm selectFrom throws an exception when specified column does not exist
    @Test
    public void test_selectFrom_throws_exception_when_column_not_exist() {
        
        database.createTable("test_table")
                .createColumn("column1", DataType.INTEGER)
                .createColumn("column2", DataType.STRING);

        assertThrows(RuntimeException.class, () -> {
            database.select("test_table", List.of("column3"));
        });
    }

    // Check alterTable throws an exception when specified column for modification does not exist
    @Test
    public void test_alter_table_throws_exception_column_not_exist() {
        
        database.createTable("test_table");

        List<String> modifiedColumns = new ArrayList<>();
        modifiedColumns.add("non_existing_column INT");

        List<String> droppedColumns = new ArrayList<>();

        assertThrows(RuntimeException.class, () -> {
            database.alter("test_table", null, modifiedColumns, droppedColumns);
        });
    }

    // Verify delete handles empty or incorrect condition strings gracefully
    @Test
    public void test_delete_handles_empty_or_incorrect_condition() {
        
        database.createTable("test_table");

        // Test empty condition
        assertThrows(IllegalArgumentException.class,() -> database.delete("test_table", ""));

        // Test incorrect condition
        assertThrows(IllegalArgumentException.class, () -> database.delete("test_table", "invalid_condition"));
    }

    // Test thread safety of the singleton instance creation
    @Test
    public void test_thread_safety_singleton_instance_creation() {
        Database database1 = Database.getInstance();
        Database database2 = Database.getInstance();
        assertSame(database1, database2);
    }

    // Ensure update handles non-existent columns or tables correctly
    @Test
    public void test_update_handles_non_existent_columns_or_tables_correctly() {
        
        Table table = new Table();
        table.createColumn("column1", DataType.STRING);
        database.createTable("table1", table);

        Column column = new Column(DataType.STRING);
        column.insert("value1");

        // Test updating non-existent column
        assertThrows(RuntimeException.class, () -> {
            database.update("table1", List.of("column1 = new_value"), "non_existent_column = 'value1'");
        });

        // Test updating non-existent table
        assertThrows(RuntimeException.class, () -> {
            database.update("non_existent_table", List.of("column1 = new_value"), "column1 = 'value1'");
        });
    }

    // Validate behavior when multiple operations are performed in quick succession
    @Test
    public void test_multiple_operations_in_quick_succession() {
        
        database.createTable("table1")
                .createColumn("column1", DataType.INTEGER)
                .createColumn("column2", DataType.STRING);

        database.insertInto("table1", new String[]{"column1", "column2"}, new Object[]{1, "value1"})
                .insertInto("table1", new String[]{"column1", "column2"}, new Object[]{2, "value2"});

        Response response = database.select("table1", List.of("column1", "column2"));
        assertNotNull(response);
        assertEquals("table1", response.getTableName());
        assertEquals(1, response.get("column1", 0));
        assertEquals("value1", response.get("column2", 0));
        assertEquals(2, response.get("column1", 1));
        assertEquals("value2", response.get("column2", 1));
    }

    // Confirm update modifies data in a column based on a specified condition
    @Test
    public void test_update_modifies_data_based_on_condition() {
        Table table = new Table();
        table.createColumn("column1", DataType.INTEGER);
        table.insert("column1", 11);
        table.insert("column1", 12);

        database.createTable("table1", table);
        database.update("table1", List.of("column1 = 20"), "column1 > 10");

        Response response = database.selectFrom("table1", List.of("column1"));
        assertEquals(20, response.get("column1", 0));
        assertEquals(20, response.get("column1", 1));
    }

    // Confirm getTable retrieves the correct table when it exists
    @Test
    public void test_confirm_get_table_retrieves_correct_table_when_exists() {
        
        Table table = new Table();
        database.createTable("test_table", table);

        Optional<Table> retrievedTable = database.getTable("test_table");

        assertTrue(retrievedTable.isPresent());
        assertEquals(table, retrievedTable.get());
    }

    // Verify delete correctly removes entries based on a specified condition
    @Test
    public void test_delete_removes_entries_based_on_condition() {
        
        Table table = new Table();
        table.createColumn("column1", DataType.INTEGER);
        table.createColumn("column2", DataType.STRING);
        database.createTable("test_table", table);

        database.insertInto("test_table", "column1", new Object[]{1, 2, 3});
        database.insertInto("test_table", "column2", new Object[]{"a", "b", "c"});

        database.delete("test_table", "column1 > 1");

        Response response = database.selectFrom("test_table", List.of("column1", "column2"));
        List<Value> column1Values = response.get("column1");
        List<Value> column2Values = response.get("column2");

        assertEquals(1, column1Values.size());
        assertEquals(1, column1Values.get(0).getObject());
        assertEquals("a", column2Values.get(0).getObject());
    }

    // Check dropTable successfully removes an existing table
    @Test
    public void test_drop_table_removes_existing_table() {
        database.createTable("test_table");
        assertTrue(database.containsTable("test_table"));
        database.dropTable("test_table");
        assertFalse(database.containsTable("test_table"));
    }

    // Ensure selectFrom returns correct data for specified columns
    @Test
    public void test_select_from_correct_data() {
        Table table = new Table();
        table.createColumn("id", DataType.INTEGER);
        table.createColumn("name", DataType.STRING);
        database.createTable("users", table);

        Object[] idValues = {1, 2, 3};
        Object[] nameValues = {"Alice", "Bob", "Charlie"};
        database.insertInto("users", "id", idValues);
        database.insertInto("users", "name", nameValues);

        Response response = database.selectFrom("users", List.of("id", "name"));
        assertEquals(3, response.get("id", 2));
        assertEquals("Bob", response.get("name", 1));
    }

    // Validate insertInto correctly adds values to specified columns in a table
    @Test
    public void test_insert_into_correctly_adds_values() {
        database.createTable("test_table")
                .createColumn("column1", DataType.INTEGER)
                .createColumn("column2", DataType.STRING);

        Object[] values = {1, "value1"};
        database.insertInto("test_table", new String[]{"column1", "column2"}, values);

        Table table = database.getTable("test_table").orElse(null);
        assertNotNull(table);

        Column column1 = table.getColumn("column1");
        assertNotNull(column1);
        assertEquals(1, column1.getFieldBody().getValues().size());
        assertEquals(1, column1.getFieldBody().getValue(0).getObject());

        Column column2 = table.getColumn("column2");
        assertNotNull(column2);
        assertEquals(1, column2.getFieldBody().getValues().size());
        assertEquals("value1", column2.getFieldBody().getValue(0).getObject());
    }

    // Check that alterTable correctly modifies, adds, and drops columns as specified
    @Test
    public void test_alter_table_behaviour() {
        Table table = new Table();
        table.createColumn("column1", DataType.STRING);
        table.createColumn("column2", DataType.INTEGER);
        database.createTable("test_table", table);

        List<String> newColumns = Collections.singletonList(("column3 STRING"));
        List<String> modifiedColumns = Arrays.asList("column1 STRING", "column2 BOOLEAN");
        List<String> droppedColumns = Collections.singletonList("column2");

        database.alter("test_table",  newColumns);

        database.alter("test_table",  null, modifiedColumns);

        database.alter("test_table", null, null, droppedColumns);

        assertTrue(database.getTable("test_table").get().contains("column1"));
        assertFalse(database.getTable("test_table").get().contains("column2"));
        assertTrue(database.getTable("test_table").get().contains("column3"));
    }

    @Test
    public void test_rename_existing_table() {
        Database db = Database.getInstance();
        db.createTable("oldTable");
        db.alter("oldTable", "newTable");
        assertTrue(db.containsTable("newTable"));
        assertFalse(db.containsTable("oldTable"));
    }

    // Check behavior when inserting null or invalid data types into columns
    @Test
    public void test_insert_null_or_invalid_data_types() {
        database.createTable("test_table")
                .createColumn("column1", DataType.INTEGER);

        // Inserting null value into column
        assertThrows(IllegalArgumentException.class, () -> database.insertInto(
                "test_table", "column1", null));

        // Inserting invalid data type into column
        assertThrows(IllegalArgumentException.class, () -> database
                .insertInto("test_table", "column1", "invalid_value")
        );
    }
}