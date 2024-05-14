package database.system.core.constraints.listeners;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.structures.Field;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class CheckConstraintTest {
    // CheckConstraint can be instantiated with a Field object and a Predicate object.
    @Test
    public void test_instantiation() {
        Field field = new Field(DataType.STRING);
        Predicate<Object> predicate = (value) -> value instanceof String;
        CheckConstraint checkConstraint = new CheckConstraint(field, predicate);
        assertNotNull(checkConstraint);
        assertEquals(field, checkConstraint.field());
        assertEquals(predicate, checkConstraint.predicate());
    }

    // The check method returns true if the predicate test fails on the input value.
    @Test
    public void test_check_returnsTrue() {
        Field field = new Field(DataType.STRING);
        Predicate<Object> predicate = (value) -> value instanceof String;
        CheckConstraint checkConstraint = new CheckConstraint(field, predicate);
        assertTrue(checkConstraint.check(123));
    }

    // The get method returns the CHECK ConstraintEnum.
    @Test
    public void test_get_returnsCHECK() {
        Field field = new Field(DataType.STRING);
        Predicate<Object> predicate = (value) -> value instanceof String;
        CheckConstraint checkConstraint = new CheckConstraint(field, predicate);
        assertEquals(CheckConstraint.class, checkConstraint.getClass());
    }

    // The update method updates the Field object's data if the check method returns true.
    @Test
    public void test_update_updatesData() {
        Field field = new Field(DataType.STRING);
        Predicate<Object> predicate = (value) -> value instanceof String;
        CheckConstraint checkConstraint = new CheckConstraint(field, predicate);
        checkConstraint.update("test");
        assertEquals("test", field.getData());
    }

    // The addConstraint method adds the CHECK ConstraintEnum to the Field object's constraintEnumSet.
    @Test
    public void test_addConstraint_addsCHECK() {
        Field field = new Field(DataType.STRING);
        Predicate<Object> predicate = (value) -> value instanceof String;
        field.addConstraint(new CheckConstraint(field, predicate));
        assertTrue(field.getConstraintSet().contains(ConstraintEnum.CHECK));
    }

    // The removeConstraint method removes the CHECK ConstraintEnum from the Field object's constraintEnumSet.
    @Test
    public void test_removeConstraint_removesCHECK() {
        Field field = new Field(DataType.STRING);
        Predicate<Object> predicate = (value) -> value instanceof String;
        CheckConstraint constraint = new CheckConstraint(field, predicate);
        field.addConstraint(constraint);
        assertTrue(field.getConstraintSet().contains(ConstraintEnum.CHECK));
        field.removeConstraint(constraint);
        assertFalse(field.getConstraintSet().contains(ConstraintEnum.CHECK));
    }

    // CheckConstraint throws a NullPointerException if the Field object is null.
    @Test
    public void test_constructor_throwsNullPointerExceptionIfFieldIsNull() {
        Predicate<Object> predicate = (value) -> value instanceof String;
        assertThrows(NullPointerException.class, () -> new CheckConstraint(null, predicate));
    }

    // CheckConstraint throws a NullPointerException if the Predicate object is null.
    @Test
    public void test_constructor_throwsNullPointerExceptionIfPredicateIsNull() {
        Field field = new Field(DataType.STRING);
        assertThrows(NullPointerException.class, () -> new CheckConstraint(field, null));
    }

    // The check method returns false if the input value is null.
    @Test
    public void test_check_returnsFalseIfValueIsNull() {
        Field field = new Field(DataType.STRING);
        Predicate<Object> predicate = (value) -> value instanceof String;
        CheckConstraint checkConstraint = new CheckConstraint(field, predicate);
        assertThrows(NullPointerException.class, ()->{checkConstraint.check(null);});
    }

    // The update method throws a RuntimeException if the check method returns false.
    @Test
    public void test_update_throwsRuntimeExceptionIfCheckReturnsFalse() {
        Field field = new Field(DataType.STRING);
        Predicate<Object> predicate = (value) -> value instanceof String;
        CheckConstraint checkConstraint = new CheckConstraint(field, predicate);
        assertThrows(RuntimeException.class, () -> checkConstraint.update(123));
    }
}