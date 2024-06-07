//package database.system.core.structures.bodies;
//
//import database.system.core.structures.Value;
//import database.system.core.structures.schemes.FieldScheme;
//import database.system.core.types.DataType;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.function.Predicate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//public class FieldBodyTest {
//    // insertValue should add a valid value to objectList
//    @Test
//    public void test_insertValue_adds_valid_value() {
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        FieldBody fieldBody = new FieldBody();
//        fieldBody.insertValue(fieldScheme, Value.of("testValue"));
//        assertEquals(1, fieldBody.getValues().size());
//        assertEquals("testValue", fieldBody.getValues().get(0).getObject());
//    }
//
//    // validate should return true for a valid FieldScheme and objectList
//    @Test
//    public void test_validate_returns_true_for_valid_FieldScheme_and_objectList() {
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        FieldBody fieldBody = new FieldBody();
//        fieldBody.insertValue(fieldScheme, "testValue");
//        assertTrue(fieldBody.validate(fieldScheme));
//    }
//
//    // insertValue should throw an exception for invalid value types
//    @Test
//    public void test_insertValue_throws_exception_for_invalid_value_types() {
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        FieldBody fieldBody = new FieldBody();
//        assertThrows(IllegalArgumentException.class, () -> {
//            fieldBody.insertValue(fieldScheme, 123);
//        });
//    }
//
//    // validate should throw an exception for type mismatches in FieldScheme
//    @Test
//    public void test_validate_throws_exception_for_type_mismatches_in_FieldScheme() {
//        FieldScheme fieldScheme = new FieldScheme(DataType.STRING);
//        FieldBody fieldBody = new FieldBody();
//        fieldBody.insertValue(fieldScheme, "testValue");
//        Value invalidValue = new Value(1, 123);
//        fieldBody.getValues().add(invalidValue);
//        assertThrows(IllegalArgumentException.class, () -> {
//            fieldBody.validate(fieldScheme);
//        });
//    }
//
//    // getValue should throw an exception for out-of-bounds index
//    @Test
//    public void test_getValue_throws_exception_for_out_of_bounds_index() {
//        FieldBody fieldBody = new FieldBody();
//        assertThrows(IndexOutOfBoundsException.class, () -> {
//            fieldBody.getValue(0);
//        });
//    }
//
//    // updateValues should handle cases where updatedValues size is different from objectList size
//
//    // removeValuesIf should handle cases where no values match the predicate
//    @Test
//    public void test_remove_values_no_match_predicate() {
//        FieldBody fieldBody = new FieldBody();
//        fieldBody.insertValue(new FieldScheme(DataType.INTEGER), 10);
//        fieldBody.insertValue(new FieldScheme(DataType.STRING), "test");
//
//        fieldBody.removeValuesIf(value -> value.equals("not_present"));
//
//        assertEquals(2, fieldBody.getValues().size());
//    }
//
//    // insertValue should increment fieldId correctly for each new value
//    @Test
//    public void test_increment_fieldId_on_insertValue() {
//        FieldBody fieldBody = new FieldBody();
//        FieldScheme fieldScheme = new FieldScheme(DataType.INTEGER);
//
//        fieldBody.insertValue(fieldScheme, 10);
//        assertEquals(1, fieldBody.getFieldId());
//
//        fieldBody.insertValue(fieldScheme, 20);
//        assertEquals(2, fieldBody.getFieldId());
//    }
//
//    // validate should handle empty objectList gracefully
//    @Test
//    public void test_validate_empty_objectList_gracefully() {
//        FieldBody fieldBody = new FieldBody();
//        FieldScheme fieldScheme = new FieldScheme(DataType.INTEGER);
//
//        assertTrue(fieldBody.validate(fieldScheme));
//    }
//
//    // removeValuesIf should not affect objectList if predicate matches no values
//    @Test
//    public void test_remove_values_if_no_match() {
//        FieldBody fieldBody = new FieldBody();
//        fieldBody.insertValue(new FieldScheme(DataType.INTEGER), 10);
//        fieldBody.insertValue(new FieldScheme(DataType.STRING), "test");
//
//        Predicate<Object> filter = value -> value.equals("not_present");
//        int initialSize = fieldBody.getValues().size();
//
//        fieldBody.removeValuesIf(filter);
//
//        assertEquals(initialSize, fieldBody.getValues().size());
//    }
//
//    // updateValues should maintain the order of values in objectList
////    @Test
////    public void test_update_values_order() {
////        FieldBody fieldBody = new FieldBody();
////        fieldBody.insertValue(new FieldScheme(DataType.INTEGER), 10);
////        fieldBody.insertValue(new FieldScheme(DataType.STRING), "abc");
////        fieldBody.insertValue(new FieldScheme(DataType.REAL), 5.5);
////
////        List<Object> updatedValues = new ArrayList<>();
////        updatedValues.add(20);
////        updatedValues.add("xyz");
////        updatedValues.add(8.8);
////
////        fieldBody.updateValues(updatedValues);
////
////        assertEquals(20, fieldBody.getValue(0).getObject());
////        assertEquals("xyz", fieldBody.getValue(1).getObject());
////        assertEquals(8.8, fieldBody.getValue(2).getObject());
////    }
//
//    // getValues should return a reference to objectList, not a copy
//    @Test
//    public void test_getValues_returns_reference() {
//        FieldBody fieldBody = new FieldBody();
//        List<Value> objectList = fieldBody.getObjectList();
//        assertSame(objectList, fieldBody.getValues());
//    }
//
//    // removeValuesIf should remove values that match the given predicate
//    @Test
//    public void test_remove_values_if_predicate_matches() {
//        FieldBody fieldBody = new FieldBody();
//        FieldScheme fieldScheme = new FieldScheme(DataType.INTEGER);
//        fieldBody.insertValue(fieldScheme, 10);
//        fieldBody.insertValue(fieldScheme, 15);
//        fieldBody.insertValue(fieldScheme, 20);
//
//        Predicate<Object> filter = value -> ((Integer)value > 10);
//        fieldBody.removeValuesIf(filter);
//
//        assertEquals(1, fieldBody.getValues().size());
//        assertEquals(10, fieldBody.getValue(0).getObject());
//    }
//
//    // updateValues should correctly update the objects in objectList
////    @Test
////    public void test_update_values_correctly() {
////        FieldBody fieldBody = new FieldBody();
////        fieldBody.insertValue(new FieldScheme(DataType.INTEGER), 10);
////        fieldBody.insertValue(new FieldScheme(DataType.STRING), "Hello");
////        fieldBody.insertValue(new FieldScheme(DataType.REAL), 5.5);
////
////        List<Object> updatedValues = new ArrayList<>();
////        updatedValues.add(20);
////        updatedValues.add("World");
////        updatedValues.add(10.5);
////
////        fieldBody.updateValues(updatedValues);
////
////        assertEquals(20, fieldBody.getValue(0).getObject());
////        assertEquals("World", fieldBody.getValue(1).getObject());
////        assertEquals(10.5, fieldBody.getValue(2).getObject());
////    }
//
//    // getValue should return the correct Value object by index
//    @Test
//    public void test_get_value_by_index() {
//        FieldBody fieldBody = new FieldBody();
//        fieldBody.insertValue(new FieldScheme(DataType.INTEGER), 10);
//        fieldBody.insertValue(new FieldScheme(DataType.STRING), "Test");
//
//        assertEquals(10, fieldBody.getValue(0).getObject());
//        assertEquals("Test", fieldBody.getValue(1).getObject());
//    }
//
//}