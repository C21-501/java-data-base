package database.system.core.constraints.listeners;

import database.system.core.structures.Field;
import database.system.core.structures.Table;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForeignKeyConstraintTest {


    // Creating a new instance of ForeignKeyConstraint with a non-null Table object as parameter should not raise any exceptions.
    @Test
    public void test_createInstanceWithNonNullTableObject() {
        Table parentTable = new Table("ParentTable");
        assertDoesNotThrow(() -> new ForeignKeyConstraint(parentTable));
    }

    // Calling the check method with a value that is not present in the parentTable should return true.
    @Test
    public void test_checkMethodWithValueNotPresentInParentTable() {
        Table parentTable = new Table("ParentTable");
        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTable);
        boolean result = foreignKeyConstraint.check("Value");
        assertTrue(result);
    }

    // Calling the check method with a value that is present in the parentTable should return false.
    @Test
    public void test_checkMethodWithValuePresentInParentTable() {
        Table parentTable = new Table("ParentTable");
        parentTable.createField("Column", new Field(DataType.INTEGER));
        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTable);
        boolean result = foreignKeyConstraint.check("Column");
        assertFalse(result);
    }

    // Adding ForeignKeyConstraint as a constraint to a Field object should not raise any exceptions.
    @Test
    public void test_addForeignKeyConstraintToFieldObject() {
        Table parentTable = new Table("ParentTable");
        Field field = new Field(DataType.INTEGER);
        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTable);
        assertDoesNotThrow(() -> field.addConstraint(foreignKeyConstraint));
    }

    // Removing ForeignKeyConstraint as a constraint from a Field object should not raise any exceptions.
    @Test
    public void test_removeForeignKeyConstraintFromFieldObject() {
        Table parentTable = new Table("ParentTable");
        Field field = new Field(DataType.INTEGER);
        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTable);
        field.addConstraint(foreignKeyConstraint);
        assertDoesNotThrow(() -> field.removeConstraint(foreignKeyConstraint));
    }

    // Creating a new instance of ForeignKeyConstraint with a null Table object as parameter should raise a NullPointerException.
    @Test
    public void test_createInstanceWithNullTableObject() {
        assertThrows(NullPointerException.class, () -> new ForeignKeyConstraint(null));
    }

    // Calling the check method with a null value should return true.
    @Test
    public void test_checkMethodWithNullValue() {
        Table parentTable = new Table("ParentTable");
        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTable);
        boolean result = foreignKeyConstraint.check(null);
        assertTrue(result);
    }

    // Adding ForeignKeyConstraint as a constraint to a non-existent Field object should raise a RuntimeException.
    @Test
    public void test_addForeignKeyConstraintToNonExistentFieldObject() {
        Table parentTable = new Table("ParentTable");
        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTable);
        assertThrows(RuntimeException.class, () -> parentTable.addConstraint("NonExistentColumn", foreignKeyConstraint));
    }

    // Removing ForeignKeyConstraint as a constraint from a non-existent Field object should raise a RuntimeException.
    @Test
    public void test_removeForeignKeyConstraintFromNonExistentFieldObject() {
        Table parentTable = new Table("ParentTable");
        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTable);
        assertThrows(RuntimeException.class, () -> parentTable.dropConstraint("NonExistentColumn", foreignKeyConstraint));
    }

    // Creating a new instance of ForeignKeyConstraint with a Table object that has no columns should not raise any exceptions.
    @Test
    public void test_createInstanceWithTableObjectWithNoColumns() {
        Table parentTable = new Table("ParentTable");
        assertDoesNotThrow(() -> new ForeignKeyConstraint(parentTable));
    }

    // Adding ForeignKeyConstraint as a constraint to a Field object that already has it should not raise any exceptions.
    @Test
    public void test_addForeignKeyConstraintToFieldObjectAlreadyHasIt() {
        Table parentTable = new Table("ParentTable");
        Field field = new Field(DataType.INTEGER);
        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTable);
        field.addConstraint(foreignKeyConstraint);
        assertDoesNotThrow(() -> field.addConstraint(foreignKeyConstraint));
    }

}