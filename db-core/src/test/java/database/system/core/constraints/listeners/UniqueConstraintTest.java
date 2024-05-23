package database.system.core.constraints.listeners;

import database.system.core.structures.bodies.TableBody;
import database.system.core.structures.schemes.FieldScheme;
import database.system.core.structures.schemes.TableScheme;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import java.io.NotSerializableException;
import static org.junit.jupiter.api.Assertions.*;


public class UniqueConstraintTest {
    // Verify that unique values pass the check
    @Test
    public void test_unique_values_pass_check() {
        TableScheme scheme = new TableScheme();
        scheme.createField("id", new FieldScheme(DataType.INTEGER));
        scheme.createField("test1", new FieldScheme(DataType.STRING));
        scheme.getField("id").addConstraint(new UniqueConstraint());
        scheme.getField("id").getFieldBody().setField();setFieldValues(1,"value1");
        tableBody.setFieldValues(2,"value2");

        assertThrows(RuntimeException.class,()->tableBody.setFieldValues(1, "value2"));
    }
}