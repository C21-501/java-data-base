package database.system.core.constraints.listeners;

import database.system.core.structures.Field;
import database.system.core.structures.Table;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UniqueConstraintTest {
    // create a new instance of UniqueConstraint with a non-null parentTable
    @Test
    public void test_create_instance_non_null_parentTable() {
        Table table = new Table("table");
        UniqueConstraint uniqueConstraint = new UniqueConstraint(table);
        assertNotNull(uniqueConstraint);
    }

    // check if a value is present in the set of unique values
    @Test
    public void test_check_value_present() {
        Table table = new Table("table");
        Field field = new Field(DataType.STRING);
        table.createField("column", field);
        table.addConstraint("column", new UniqueConstraint(table));
        field.setUpData("some data");
        assertEquals("some data",table.getField("column").getObjectList());
    }

    @Test
    public void test_check_not_unique_value_present() {
        Table table1 = new Table("table1");
        Field field1 = new Field(DataType.STRING);
        table1.createField("column", field1);
        table1.addConstraint("column", new UniqueConstraint(table1));
        field1.setUpData("some data");



    }

    // create a new instance of UniqueConstraint with a null parentTable and expect a NullPointerException to be thrown
    @Test
    public void test_create_instance_null_parentTable() {
        assertThrows(NullPointerException.class, () -> new UniqueConstraint(null));
    }

    // check if a null value is present in the set of unique values
    @Test
    public void test_check_null_value_present() {
        Table table = new Table("table");
        Field field = new Field(DataType.STRING);
        table.addConstraint("column", new UniqueConstraint(table));
        table.createField("column", field);
        field.setUpData("some data");
    }

    // check if a non-existent value is present in the set of unique values
    @Test
    public void test_check_non_existent_value_present() {
        Table table = new Table("table");
        table.createField("column", new Field(DataType.STRING));
        table.addConstraint("column", new UniqueConstraint(table));
//        assertFalse(table.getField("column").getConstraints().get(0).check("value"));
    }

    // add a constraint to a non-existent column and expect a RuntimeException to be thrown
    @Test
    public void test_add_constraint_non_existent_column() {
        Table table = new Table("table");
        assertThrows(RuntimeException.class, () -> table.addConstraint("column", new UniqueConstraint(table)));
    }

    // remove a constraint from a non-existent column and expect a RuntimeException to be thrown
    @Test
    public void test_remove_constraint_non_existent_column() {
        Table table = new Table("table");
        assertThrows(RuntimeException.class, () -> table.dropConstraint("column", new UniqueConstraint(table)));
    }

    // add a constraint to an existing column
    @Test
    public void test_add_constraint_existing_column() {
        Table table = new Table("table");
        table.createField("column", new Field(DataType.STRING));
        table.addConstraint("column", new UniqueConstraint(table));
        assertTrue(table.getField("column").contains(UniqueConstraint.class));
    }

    // remove a constraint from an existing column
    @Test
    public void test_remove_constraint_existing_column() {
        Table table = new Table("table");
        table.createField("column", new Field(DataType.STRING));
        UniqueConstraint uniqueConstraint = new UniqueConstraint(table);
        table.addConstraint("column", uniqueConstraint);
        table.dropConstraint("column", uniqueConstraint);
        assertTrue(table.getField("column").contains(UniqueConstraint.class));
    }

    // create a new instance of UniqueConstraint with a non-null parentTable and check if the parentTable is set correctly
    @Test
    public void test_create_instance_check_parentTable() {
        Table table = new Table("table");
        UniqueConstraint uniqueConstraint = new UniqueConstraint(table);
        assertEquals(table, uniqueConstraint.parentTable());
    }

    // create a new instance of UniqueConstraint with a non-null parentTable and check if the check method returns the expected value
    @Test
    public void test_check_method_returns_expected_value() {
        Table table = new Table("table");
        table.createField("column", new Field(DataType.STRING));
        table.addConstraint("column", new UniqueConstraint(table));
//        assertTrue(table.getField("column").getConstraints().get(0).check("value"));
    }

}