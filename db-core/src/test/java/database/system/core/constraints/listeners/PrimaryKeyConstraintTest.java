package database.system.core.constraints.listeners;


public class PrimaryKeyConstraintTest {
    // Creating a new instance of PrimaryKeyConstraint with a non-null Table object should not raise any exceptions.
//    @Test
//    public void test_createInstanceWithNonNullTableObject() {
//        TableScheme tableScheme = new TableScheme("testTable");
//        assertDoesNotThrow(() -> new PrimaryKeyConstraint(tableScheme));
//    }
//
//    // Calling the check method with a value that is not already present in the parentTable should return true.
//    @Test
//    public void test_checkMethodWithValueNotPresentInParentTable() {
//        TableScheme tableScheme = new TableScheme("testTable");
//        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(tableScheme);
//        assertFalse(constraint.check(, "value", ));
//    }
//
//    // Adding a new value to the parentTable and then calling the check method with that value should return false.
//    @Test
//    public void test_checkMethodWithValuePresentInParentTable() {
//        TableScheme tableScheme = new TableScheme("testTable");
//        tableScheme.createField("column", new FieldScheme(DataType.STRING));
//        tableScheme.updateField("column", new FieldScheme(DataType.INTEGER));
//        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(tableScheme);
//        assertFalse(constraint.check(, "value", ));
//    }
//
//    // Adding multiple values to the parentTable and then calling the check method with each value should return true for all but the last value.
//    @Test
//    public void test_checkMethodWithMultipleValuesInParentTable() {
//        TableScheme tableScheme = new TableScheme("testTable");
//        tableScheme.createField("column", new FieldScheme(DataType.INTEGER));
//        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(tableScheme);
//        tableScheme.renameField("column", "new_column");
//        tableScheme.updateField("new_column", new FieldScheme(DataType.STRING));
//        tableScheme.addConstraint("new_column", constraint);
//        assertFalse(constraint.check(, "column", ));
//        assertTrue(constraint.check(, "new_column", ));
//    }
//
//    // Adding a new PrimaryKeyConstraint to a Field object in the parentTable should not raise any exceptions.
//    @Test
//    public void test_addPrimaryKeyConstraintToFieldObject() {
//        TableScheme tableScheme = new TableScheme("testTable");
//        tableScheme.createField("column", new FieldScheme(DataType.STRING));
//        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(tableScheme);
//        assertDoesNotThrow(() -> tableScheme.addConstraint("column", constraint));
//    }
//
//    // Creating a new instance of PrimaryKeyConstraint with a null Table object should raise a NullPointerException.
//    @Test
//    public void test_createInstanceWithNullTableObject() {
//        assertThrows(NullPointerException.class, () -> new PrimaryKeyConstraint(null));
//    }
//
//    // Calling the check method with a null value should raise a NullPointerException.
//    @Test
//    public void test_checkMethodWithNullValue() {
//        TableScheme tableScheme = new TableScheme("testTable");
//        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(tableScheme);
//        assertThrows(NullPointerException.class, () -> constraint.check(, null, ));
//    }
//
//    // Calling the check method with a value that is already present in the parentTable should return false.
//    @Test
//    public void test_checkMethodWithValueAlreadyPresentInParentTable() {
//        TableScheme tableScheme = new TableScheme("testTable");
//        tableScheme.createField("column", new FieldScheme(DataType.STRING));
//        tableScheme.updateField("column", new FieldScheme(DataType.INTEGER));
//        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(tableScheme);
//        tableScheme.addConstraint("column", constraint);
//        assertFalse(constraint.check(, "value", ));
//        assertTrue(constraint.check(, "column", ));
//    }
//
//    // Adding a new PrimaryKeyConstraint to a non-existent Field object in the parentTable should raise a RuntimeException.
//    @Test
//    public void test_addPrimaryKeyConstraintToNonExistentFieldObject() {
//        TableScheme tableScheme = new TableScheme("testTable");
//        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(tableScheme);
//        assertThrows(RuntimeException.class, ()-> tableScheme.addConstraint("column", constraint));
//    }
//
//    // Removing a PrimaryKeyConstraint from a Field object in the parentTable should not raise any exceptions.
//    @Test
//    public void test_removePrimaryKeyConstraintFromFieldObject() {
//        TableScheme tableScheme = new TableScheme("testTable");
//        tableScheme.createField("column", new FieldScheme(DataType.STRING));
//        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(tableScheme);
//        tableScheme.addConstraint("column", constraint);
//        assertDoesNotThrow(() -> tableScheme.dropConstraint("column", constraint));
//    }
//
//    // Creating a new instance of PrimaryKeyConstraint with a Table object that has no columns should not raise any exceptions.
//    @Test
//    public void test_createInstanceWithTableObjectWithNoColumns() {
//        TableScheme tableScheme = new TableScheme("testTable");
//        assertDoesNotThrow(() -> new PrimaryKeyConstraint(tableScheme));
//    }
//
//    // Calling the check method with a value that is not a String should raise a ClassCastException.
//    @Test
//    public void test_checkMethodWithValueNotString() {
//        TableScheme tableScheme = new TableScheme("testTable");
//        PrimaryKeyConstraint constraint = new PrimaryKeyConstraint(tableScheme);
//        assertThrows(ClassCastException.class, () -> constraint.check(, 123, ));
//    }

}