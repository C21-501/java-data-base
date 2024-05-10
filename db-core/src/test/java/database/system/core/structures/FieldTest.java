package database.system.core.structures;

import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {
    // Setting up data for a Column object updates the data attribute and notifies the ConstraintManager.
    @Test
    public void test_set_up_data() {
        Field field = new Field(DataType.INTEGER);

        field.setUpData(10);
        assertEquals(10, field.getData());
    }

    // Creating a new Column object with null type throws a NullPointerException.
    @Test
    public void test_create_column_with_null_type() {
        assertThrows(NullPointerException.class, () -> {
            new Field( null);
        });
    }
}