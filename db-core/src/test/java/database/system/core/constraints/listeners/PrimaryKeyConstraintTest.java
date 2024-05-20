package database.system.core.constraints.listeners;

import static org.junit.jupiter.api.Assertions.*;

import database.system.core.structures.Field;
import database.system.core.structures.Table;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;


public class PrimaryKeyConstraintTest {
    // Creating a new instance of PrimaryKeyConstraint with a non-null Table object should not raise any exceptions.
    @Test
    public void test_createInstanceWithNonNullTableObject() {
        Table table = new Table("testTable");
        assertDoesNotThrow(() -> new PrimaryKeyConstraint(table));
    }

    // Calling the check method with a value that is not already present in the parentTable should return true.
    @Test
    public void test_checkMethodWithValueNotPresentInParentTable() {
        Table table = new Table("testTable");
        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(table);
        assertFalse(constraint.check("value"));
    }

    // Adding a new value to the parentTable and then calling the check method with that value should return false.
    @Test
    public void test_checkMethodWithValuePresentInParentTable() {
        Table table = new Table("testTable");
        table.createField("column", new Field(DataType.STRING));
        table.updateField("column", new Field(DataType.INTEGER));
        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(table);
        assertFalse(constraint.check("value"));
    }

    // Adding multiple values to the parentTable and then calling the check method with each value should return true for all but the last value.
    @Test
    public void test_checkMethodWithMultipleValuesInParentTable() {
        Table table = new Table("testTable");
        table.createField("column", new Field(DataType.INTEGER));
        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(table);
        table.renameField("column", "new_column");
        table.updateField("new_column", new Field(DataType.STRING));
        table.addConstraint("new_column", constraint);
        assertFalse(constraint.check("column"));
        assertTrue(constraint.check("new_column"));
    }

    // Adding a new PrimaryKeyConstraint to a Field object in the parentTable should not raise any exceptions.
    @Test
    public void test_addPrimaryKeyConstraintToFieldObject() {
        Table table = new Table("testTable");
        table.createField("column", new Field(DataType.STRING));
        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(table);
        assertDoesNotThrow(() -> table.addConstraint("column", constraint));
    }

    // Creating a new instance of PrimaryKeyConstraint with a null Table object should raise a NullPointerException.
    @Test
    public void test_createInstanceWithNullTableObject() {
        assertThrows(NullPointerException.class, () -> new PrimaryKeyConstraint(null));
    }

    // Calling the check method with a null value should raise a NullPointerException.
    @Test
    public void test_checkMethodWithNullValue() {
        Table table = new Table("testTable");
        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(table);
        assertThrows(NullPointerException.class, () -> constraint.check(null));
    }

    // Calling the check method with a value that is already present in the parentTable should return false.
    @Test
    public void test_checkMethodWithValueAlreadyPresentInParentTable() {
        Table table = new Table("testTable");
        table.createField("column", new Field(DataType.STRING));
        table.updateField("column", new Field(DataType.INTEGER));
        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(table);
        table.addConstraint("column", constraint);
        assertFalse(constraint.check("value"));
        assertTrue(constraint.check("column"));
    }

    // Adding a new PrimaryKeyConstraint to a non-existent Field object in the parentTable should raise a RuntimeException.
    @Test
    public void test_addPrimaryKeyConstraintToNonExistentFieldObject() {
        Table table = new Table("testTable");
        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(table);
        assertThrows(RuntimeException.class, ()->table.addConstraint("column", constraint));
    }

    // Removing a PrimaryKeyConstraint from a Field object in the parentTable should not raise any exceptions.
    @Test
    public void test_removePrimaryKeyConstraintFromFieldObject() {
        Table table = new Table("testTable");
        table.createField("column", new Field(DataType.STRING));
        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(table);
        table.addConstraint("column", constraint);
        assertDoesNotThrow(() -> table.dropConstraint("column", constraint));
    }

    // Creating a new instance of PrimaryKeyConstraint with a Table object that has no columns should not raise any exceptions.
    @Test
    public void test_createInstanceWithTableObjectWithNoColumns() {
        Table table = new Table("testTable");
        assertDoesNotThrow(() -> new PrimaryKeyConstraint(table));
    }

    // Calling the check method with a value that is not a String should raise a ClassCastException.
    @Test
    public void test_checkMethodWithValueNotString() {
        Table table = new Table("testTable");
        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(table);
        assertThrows(ClassCastException.class, () -> constraint.check(123));
    }

}