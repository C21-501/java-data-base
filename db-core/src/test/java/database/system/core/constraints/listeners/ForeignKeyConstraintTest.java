package database.system.core.constraints.listeners;

public class ForeignKeyConstraintTest {


//    // Creating a new instance of ForeignKeyConstraint with a non-null Table object as parameter should not raise any exceptions.
//    @Test
//    public void test_createInstanceWithNonNullTableObject() {
//        TableScheme parentTableScheme = new TableScheme("ParentTable");
//        assertDoesNotThrow(() -> new ForeignKeyConstraint(parentTableScheme));
//    }
//
//    // Calling the check method with a value that is not present in the parentTable should return true.
//    @Test
//    public void test_checkMethodWithValueNotPresentInParentTable() {
//        TableScheme parentTableScheme = new TableScheme("ParentTable");
//        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTableScheme);
//        boolean result = foreignKeyConstraint.check(0, "Value", );
//        assertTrue(result);
//    }
//
//    // Calling the check method with a value that is present in the parentTable should return false.
//    @Test
//    public void test_checkMethodWithValuePresentInParentTable() {
//        TableScheme parentTableScheme = new TableScheme("ParentTable");
//        parentTableScheme.createField("Column", new FieldScheme(DataType.INTEGER));
//        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTableScheme);
//        boolean result = foreignKeyConstraint.check(0, "Value", );
//        assertFalse(result);
//    }
//
//    // Adding ForeignKeyConstraint as a constraint to a Field object should not raise any exceptions.
//    @Test
//    public void test_addForeignKeyConstraintToFieldObject() {
//        TableScheme parentTableScheme = new TableScheme("ParentTable");
//        FieldScheme fieldScheme = new FieldScheme(DataType.INTEGER);
//        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTableScheme);
//        assertDoesNotThrow(() -> fieldScheme.addConstraint(foreignKeyConstraint));
//    }
//
//    // Removing ForeignKeyConstraint as a constraint from a Field object should not raise any exceptions.
//    @Test
//    public void test_removeForeignKeyConstraintFromFieldObject() {
//        TableScheme parentTableScheme = new TableScheme("ParentTable");
//        FieldScheme fieldScheme = new FieldScheme(DataType.INTEGER);
//        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTableScheme);
//        fieldScheme.addConstraint(foreignKeyConstraint);
//        assertDoesNotThrow(() -> fieldScheme.removeConstraint(foreignKeyConstraint));
//    }
//
//    // Creating a new instance of ForeignKeyConstraint with a null Table object as parameter should raise a NullPointerException.
//    @Test
//    public void test_createInstanceWithNullTableObject() {
//        assertThrows(NullPointerException.class, () -> new ForeignKeyConstraint(null));
//    }
//
//    // Calling the check method with a null value should return true.
//    @Test
//    public void test_checkMethodWithNullValue() {
//        TableScheme parentTableScheme = new TableScheme("ParentTable");
//        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTableScheme);
//        boolean result = foreignKeyConstraint.check(, 0, null, );
//        assertTrue(result);
//    }
//
//    // Adding ForeignKeyConstraint as a constraint to a non-existent Field object should raise a RuntimeException.
//    @Test
//    public void test_addForeignKeyConstraintToNonExistentFieldObject() {
//        TableScheme parentTableScheme = new TableScheme("ParentTable");
//        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTableScheme);
//        assertThrows(RuntimeException.class, () -> parentTableScheme.addConstraint("NonExistentColumn", foreignKeyConstraint));
//    }
//
//    // Removing ForeignKeyConstraint as a constraint from a non-existent Field object should raise a RuntimeException.
//    @Test
//    public void test_removeForeignKeyConstraintFromNonExistentFieldObject() {
//        TableScheme parentTableScheme = new TableScheme("ParentTable");
//        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTableScheme);
//        assertThrows(RuntimeException.class, () -> parentTableScheme.dropConstraint("NonExistentColumn", foreignKeyConstraint));
//    }
//
//    // Creating a new instance of ForeignKeyConstraint with a Table object that has no columns should not raise any exceptions.
//    @Test
//    public void test_createInstanceWithTableObjectWithNoColumns() {
//        TableScheme parentTableScheme = new TableScheme("ParentTable");
//        assertDoesNotThrow(() -> new ForeignKeyConstraint(parentTableScheme));
//    }
//
//    // Adding ForeignKeyConstraint as a constraint to a Field object that already has it should not raise any exceptions.
//    @Test
//    public void test_addForeignKeyConstraintToFieldObjectAlreadyHasIt() {
//        TableScheme parentTableScheme = new TableScheme("ParentTable");
//        FieldScheme fieldScheme = new FieldScheme(DataType.INTEGER);
//        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(parentTableScheme);
//        fieldScheme.addConstraint(foreignKeyConstraint);
//        assertDoesNotThrow(() -> fieldScheme.addConstraint(foreignKeyConstraint));
//    }

}