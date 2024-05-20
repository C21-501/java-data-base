package database.system.core.constraints.listeners;

import database.system.core.structures.Field;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class NotNullConstraintTest {


    // Given a non-null value, check() should return false
    @Test
    public void test_check_non_null_value() {
        NotNullConstraint constraint = new NotNullConstraint();
        Field field = new Field(DataType.STRING);
        field.setUpData("data");
        assertFalse(constraint.check(field));
    }

    // Given a null value, check() should return true
    @Test
    public void test_check_null_value() {
        NotNullConstraint constraint = new NotNullConstraint();
        Field field = new Field(DataType.STRING);
        field.setUpData(null);
        assertTrue(constraint.check(field));
    }

    // Given a non-Field object, check() should throw an IllegalArgumentException
    @Test
    public void test_check_non_field_object_throw_exception() {
        NotNullConstraint constraint = new NotNullConstraint();
        assertThrows(IllegalArgumentException.class, () -> constraint.check("data"));
    }

    // Given a Field object with null data, check() should return true
    @Test
    public void test_check_null_data_return_true() {
        NotNullConstraint constraint = new NotNullConstraint();
        Field field = new Field(DataType.STRING);
        field.setUpData(null);
        assertTrue(constraint.check(field));
    }

    // Given a Field object with non-null data, check() should return false
    @Test
    public void test_check_non_null_data_return_false() {
        NotNullConstraint constraint = new NotNullConstraint();
        Field field = new Field(DataType.STRING);
        field.setUpData("data");
        assertFalse(constraint.check(field));
    }
}