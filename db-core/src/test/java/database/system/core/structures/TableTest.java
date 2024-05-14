package database.system.core.structures;

import static org.junit.jupiter.api.Assertions.*;

import database.system.core.constraints.interfaces.Constraint;
import org.junit.jupiter.api.Test;
import database.system.core.types.DataType;

public class TableTest {


    // creating a new Table with a given name should set the table name
    @Test
    public void test_create_table_with_given_name_should_set_table_name() {
        Table table = new Table("table1");
        assertEquals("table1", table.tableName());
    }

    // adding a new field to the table should add the field to the columnHashMap
    @Test
    public void test_adding_new_field_should_add_field_to_columnHashMap() {
        Table table = new Table("table1");
        Field field = new Field(DataType.STRING);
        table.addField("column1", field);
        assertTrue(table.containsKey("column1"));
        assertEquals(field, table.getField("column1"));
    }

    // dropping an existing field from the table should remove the field from the columnHashMap
    @Test
    public void test_dropping_existing_field_should_remove_field_from_columnHashMap() {
        Table table = new Table("table1");
        Field field = new Field(DataType.STRING);
        table.addField("column1", field);
        table.dropField("column1");
        assertFalse(table.containsKey("column1"));
    }

    // renaming an existing field in the table should update the columnHashMap with the new name
    @Test
    public void test_renaming_existing_field_should_update_columnHashMap_with_new_name() {
        Table table = new Table("table1");
        Field field = new Field(DataType.STRING);
        table.addField("column1", field);
        table.renameField("column1", "newColumn");
        assertFalse(table.containsKey("column1"));
        assertTrue(table.containsKey("newColumn"));
        assertEquals(field, table.getField("newColumn"));
    }

    // updating an existing field in the table should update the columnHashMap with the new field
    @Test
    public void test_updating_existing_field_should_update_columnHashMap_with_new_field() {
        Table table = new Table("table1");
        Field field1 = new Field(DataType.STRING);
        Field field2 = new Field(DataType.INTEGER);
        table.addField("column1", field1);
        table.updateField("column1", field2);
        assertEquals(field2, table.getField("column1"));
    }

    // adding a new constraint to an existing field in the table should add the constraint to the field's constraintSet
    @Test
    public void test_adding_new_constraint_should_add_constraint_to_field_constraintSet() {
        Table table = new Table("table1");
        Field field = new Field(DataType.STRING);
        Constraint constraint = new Constraint() {
            @Override
            public boolean check(Object value) {
                return false;
            }
        };
        table.addField("column1", field);
        table.addConstraint("column1", constraint);
        assertTrue(field.getConstraintSet().contains(constraint));
    }

    // creating a new Table with a null name should throw a NullPointerException
    @Test
    public void test_creating_new_table_with_null_name_should_throw_NullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            new Table(null);
        });
    }

    // getting a field with a null column name should throw a NullPointerException
    @Test
    public void test_getting_field_with_null_column_name_should_throw_NullPointerException() {
        Table table = new Table("table1");
        assertThrows(NullPointerException.class, () -> {
            table.getField(null);
        });
    }

}