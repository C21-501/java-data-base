package database.system.core.structures;

import static org.junit.jupiter.api.Assertions.*;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.schemes.FieldScheme;
import database.system.core.structures.schemes.TableScheme;
import org.junit.jupiter.api.Test;
import database.system.core.types.DataType;

public class TableSchemeTest {


//    // creating a new Table with a given name should set the table name
//    @Test
//    public void test_create_table_with_given_name_should_set_table_name() {
//        TableScheme tableScheme = new TableScheme("table1");
//        assertEquals("table1", tableScheme.tableName());
//    }
//
//    // adding a new field to the table should add the field to the columnHashMap
//    @Test
//    public void test_adding_new_field_should_add_field_to_columnHashMap() {
//        TableScheme tableScheme = new TableScheme("table1");
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        tableScheme.createField("column1", fieldScheme);
//        assertTrue(tableScheme.containsKey("column1"));
//        assertEquals(fieldScheme, tableScheme.getField("column1"));
//    }
//
//    // dropping an existing field from the table should remove the field from the columnHashMap
//    @Test
//    public void test_dropping_existing_field_should_remove_field_from_columnHashMap() {
//        TableScheme tableScheme = new TableScheme("table1");
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        tableScheme.createField("column1", fieldScheme);
//        tableScheme.dropField("column1");
//        assertFalse(tableScheme.containsKey("column1"));
//    }
//
//    // renaming an existing field in the table should update the columnHashMap with the new name
//    @Test
//    public void test_renaming_existing_field_should_update_columnHashMap_with_new_name() {
//        TableScheme tableScheme = new TableScheme("table1");
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        tableScheme.createField("column1", fieldScheme);
//        tableScheme.renameField("column1", "newColumn");
//        assertFalse(tableScheme.containsKey("column1"));
//        assertTrue(tableScheme.containsKey("newColumn"));
//        assertEquals(fieldScheme, tableScheme.getField("newColumn"));
//    }
//
//    // updating an existing field in the table should update the columnHashMap with the new field
//    @Test
//    public void test_updating_existing_field_should_update_columnHashMap_with_new_field() {
//        TableScheme tableScheme = new TableScheme("table1");
//        FieldScheme fieldScheme1 = new FieldScheme(DataType.STRING);
//        FieldScheme fieldScheme2 = new FieldScheme(DataType.INTEGER);
//        tableScheme.createField("column1", fieldScheme1);
//        tableScheme.updateField("column1", fieldScheme2);
//        assertEquals(fieldScheme2, tableScheme.getField("column1"));
//    }
//
//    // adding a new constraint to an existing field in the table should add the constraint to the field's constraintSet
//    @Test
//    public void test_adding_new_constraint_should_add_constraint_to_field_constraintSet() {
//        TableScheme tableScheme = new TableScheme("table1");
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        Constraint constraint = new Constraint() {
//            @Override
//            public boolean check(int index, Object value) {
//                return false;
//            }
//        };
//        tableScheme.createField("column1", fieldScheme);
//        tableScheme.addConstraint("column1", constraint);
//        assertTrue(fieldScheme.getConstraintSet().contains(constraint));
//    }
//
//    // creating a new Table with a null name should throw a NullPointerException
//    @Test
//    public void test_creating_new_table_with_null_name_should_throw_NullPointerException() {
//        assertThrows(NullPointerException.class, () -> {
//            new TableScheme(null);
//        });
//    }
//
//    // getting a field with a null column name should throw a NullPointerException
//    @Test
//    public void test_getting_field_with_null_column_name_should_throw_NullPointerException() {
//        TableScheme tableScheme = new TableScheme("table1");
//        assertThrows(NullPointerException.class, () -> {
//            tableScheme.getField(null);
//        });
//    }

}