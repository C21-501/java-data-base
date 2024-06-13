package database.system.core.constraints.listeners;

import database.system.core.structures.schemes.FieldScheme;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class CheckConstraintTest {
    // CheckConstraint can be instantiated with a Field object and a Predicate object.
//    @Test
//    public void test_instantiation() {
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        Predicate<Object> predicate = (value) -> value instanceof String;
//        CheckConstraint checkConstraint = new CheckConstraint("field_check", predicate);
//        assertNotNull(checkConstraint);
//        fieldScheme.addConstraint(checkConstraint);
//        assertTrue(fieldScheme.getConstraintSet().contains(checkConstraint));
//        assertEquals(predicate, checkConstraint.predicate());
//    }
//
//    // The check method returns true if the predicate test fails on the input value.
//    @Test
//    public void test_check_returnsTrue() {
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        Predicate<Object> predicate = (value) -> value instanceof String;
//        CheckConstraint checkConstraint = new CheckConstraint("field_check", predicate);
//        assertFalse(checkConstraint.check(0, 123, ));
//    }
//
//    // The get method returns the CHECK ConstraintEnum.
//    @Test
//    public void test_get_returnsCHECK() {
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        Predicate<Object> predicate = (value) -> value instanceof String;
//        CheckConstraint checkConstraint = new CheckConstraint("field", predicate);
//        assertEquals(CheckConstraint.class, checkConstraint.getClass());
//    }
//
//    // The update method updates the Field object's data if the check method returns true.
//
//    // The addConstraint method adds the CHECK ConstraintEnum to the Field object's constraintEnumSet.
//    @Test
//    public void test_addConstraint_addsCHECK() {
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        Predicate<Object> predicate = (value) -> value instanceof String;
//        fieldScheme.addConstraint(new CheckConstraint("field", predicate));
//        assertTrue(fieldScheme.contains(CheckConstraint.class));
//    }
//
//    // The removeConstraint method removes the CHECK ConstraintEnum from the Field object's constraintEnumSet.
//    @Test
//    public void test_removeConstraint_removesCHECK() {
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        Predicate<Object> predicate = (value) -> value instanceof String;
//        CheckConstraint constraint = new CheckConstraint("field", predicate);
//        fieldScheme.addConstraint(constraint);
//        assertTrue(fieldScheme.contains(CheckConstraint.class));
//        fieldScheme.removeConstraint(constraint);
//        assertFalse(fieldScheme.getConstraintSet().contains(CheckConstraint.class));
//    }
//
//    // CheckConstraint throws a NullPointerException if the Field object is null.
//    @Test
//    public void test_constructor_throwsNullPointerExceptionIfFieldIsNull() {
//        Predicate<Object> predicate = (value) -> value instanceof String;
//        assertThrows(NullPointerException.class, () -> new CheckConstraint(null, predicate));
//    }
//
//    // CheckConstraint throws a NullPointerException if the Predicate object is null.
//    @Test
//    public void test_constructor_throwsNullPointerExceptionIfPredicateIsNull() {
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        assertThrows(NullPointerException.class, () -> new CheckConstraint("field", null));
//    }
//
//    // The check method returns false if the input value is null.
//    @Test
//    public void test_check_returnsFalseIfValueIsNull() {
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        Predicate<Object> predicate = (value) -> value instanceof String;
//        CheckConstraint checkConstraint = new CheckConstraint("field", predicate);
//        assertThrows(NullPointerException.class, ()->{checkConstraint.check(0, null, );});
//    }
}