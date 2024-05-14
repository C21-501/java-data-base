package database.system.core.managers;

import static org.junit.jupiter.api.Assertions.*;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.listeners.ForeignKeyConstraint;
import database.system.core.constraints.listeners.PrimaryKeyConstraint;
import database.system.core.constraints.listeners.UniqueConstraint;
import database.system.core.structures.Field;
import database.system.core.structures.Table;
import database.system.core.types.DataType;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

public class FieldManagerTest {

    // creating a column with a unique name and data type adds it to the columns map
    @Test
    public void test_create_column_unique_name_and_type() {
        ColumnManager columnManager = new ColumnManager();
        columnManager.createColumn("column1", DataType.INTEGER);
        Map<String, Field> columns = columnManager.getColumns();
        assertTrue(columns.containsKey("column1"));
        assertEquals(DataType.INTEGER, columns.get("column1").getType());
    }

    // adding a constraint to an existing column updates the column's constraint set
    @Test
    public void test_add_constraint_existing_column() {
        ColumnManager columnManager = new ColumnManager();
        columnManager.createColumn("column1", DataType.INTEGER);
        columnManager.addConstraint("column1",);
        Map<String, Field> columns = columnManager.getColumns();
        Set<Constraint> constraints = columns.get("column1").getConstraintSet();
        assertEquals(true, constraints.contains(UniqueConstraint.class));
    }

    // creating a column with a null name throws a NullPointerException
    @Test
    public void test_create_column_null_name() {
        ColumnManager columnManager = new ColumnManager();
        assertThrows(NullPointerException.class, () -> {
            columnManager.createColumn(null, DataType.INTEGER);
        });
    }

    // creating a column with a null data type throws a NullPointerException
    @Test
    public void test_create_column_null_type() {
        ColumnManager columnManager = new ColumnManager();
        assertThrows(NullPointerException.class, () -> {
            columnManager.createColumn("column1", null);
        });
    }

    // adding a null constraint to an existing column throws a NullPointerException
    @Test
    public void test_add_constraint_null_constraint() {
        ColumnManager columnManager = new ColumnManager();
        columnManager.createColumn("column1", DataType.INTEGER);
        assertThrows(NullPointerException.class, () -> {
            columnManager.addConstraint("column1", null);
        });
    }

}