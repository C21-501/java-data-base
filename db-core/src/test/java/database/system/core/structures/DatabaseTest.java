package database.system.core.structures;

import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    // Create a new table and verify it exists in the database
    @Test
    public void create_table_and_verify_existence() {
        Database db = Database.getInstance();
        Table newTable = new Table();
        db.createTable("testTable", newTable);
        assertTrue(db.containsTable("testTable"));
    }

    // Drop an existing table and verify it no longer exists
    @Test
    public void drop_table_and_verify_nonexistence() {
        Database db = Database.getInstance();
        Table newTable = new Table();
        db.createTable("testTable", newTable);
        db.dropTable("testTable");
        assertFalse(db.containsTable("testTable"));
    }

    // Insert data into a table and verify the data is stored correctly
    @Test
    public void insert_data_and_verify_storage() {
        Database db = Database.getInstance();
        Table table = new Table()
                .createColumn("column1", DataType.INTEGER);

        db.createTable("testTable", table);
        String[] columns = {"column1"};
        Object[] values = {1,2,3,3,4,5};

        Value value = new Value(3,3);
        db.insertInto("testTable", columns, values);
        Response response = db.selectFrom("testTable", columns);
        assertEquals(value, response.get("column1", 3));
    }

    // Rename a column in a table and verify the column name is updated
    @Test
    public void rename_column_and_verify_update() {
        Database db = Database.getInstance();
        Table newTable = new Table().createColumn("oldName", new Column(DataType.STRING));
        db.createTable("testTable", newTable);
        Table table = db.getTable("testTable").get();
        table.renameColumn("oldName", "newName");
        assertNotNull(db.getTable("testTable").get().getColumn("newName"));
    }

    // Attempt to create a table that already exists and handle the error
    @Test
    public void attempt_create_existing_table() {
        Database db = Database.getInstance();
        Table newTable = new Table();
        db.createTable("testTable", newTable);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            db.createTable("testTable", newTable);
        });
        assertTrue(exception.getMessage().contains("already exists"));
    }

    // Attempt to drop a table that does not exist and handle the error
    @Test
    public void attempt_drop_nonexistent_table() {
        Database db = Database.getInstance();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            db.dropTable("nonexistentTable");
        });
        assertTrue(exception.getMessage().contains("does not exist"));
    }

    // Insert data into a non-existent table and handle the error
    @Test
    public void insert_into_nonexistent_table() {
        Database db = Database.getInstance();
        String[] columns = {"column1"};
        Object[] values = {123};
        Exception exception = assertThrows(RuntimeException.class, () -> {
            db.insertInto("nonexistentTable", columns, values);
        });
        assertTrue(exception.getMessage().contains("doesn't exists"));
    }

    // Select from a non-existent table and handle the error
    @Test
    public void select_from_nonexistent_table() {
        Database db = Database.getInstance();
        String[] columns = {"column1"};
        Exception exception = assertThrows(RuntimeException.class, () -> {
            db.selectFrom("nonexistentTable", columns);
        });
        assertTrue(exception.getMessage().contains("doesn't not exists"));
    }
}