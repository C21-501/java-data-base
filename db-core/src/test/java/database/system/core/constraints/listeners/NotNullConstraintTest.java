package database.system.core.constraints.listeners;


public class NotNullConstraintTest {


//    // Given a non-null value, check() should return false
//    @Test
//    public void test_check_non_null_value() {
//        NotNullConstraint constraint = new NotNullConstraint();
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        fieldScheme.setUpData("data");
//        assertFalse(constraint.check(, fieldScheme, ));
//    }
//
//    // Given a null value, check() should return true
//    @Test
//    public void test_check_null_value() {
//        NotNullConstraint constraint = new NotNullConstraint();
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        fieldScheme.setUpData(null);
//        assertTrue(constraint.check(, fieldScheme, ));
//    }
//
//    // Given a non-Field object, check() should throw an IllegalArgumentException
//    @Test
//    public void test_check_non_field_object_throw_exception() {
//        NotNullConstraint constraint = new NotNullConstraint();
//        assertThrows(IllegalArgumentException.class, () -> constraint.check(, "data", ));
//    }
//
//    // Given a Field object with null data, check() should return true
//    @Test
//    public void test_check_null_data_return_true() {
//        NotNullConstraint constraint = new NotNullConstraint();
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        fieldScheme.setUpData(null);
//        assertTrue(constraint.check(, fieldScheme, ));
//    }
//
//    // Given a Field object with non-null data, check() should return false
//    @Test
//    public void test_check_non_null_data_return_false() {
//        NotNullConstraint constraint = new NotNullConstraint();
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        fieldScheme.setUpData("data");
//        assertFalse(constraint.check(, fieldScheme, ));
//    }
}