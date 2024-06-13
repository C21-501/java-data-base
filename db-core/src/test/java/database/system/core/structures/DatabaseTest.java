package database.system.core.structures;

import database.system.core.types.DataType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class DatabaseTest {
    private Database database;
    
    @BeforeEach
    public void setUp() {
        database = Database.getInstance();
    }
    @AfterEach
    public void tearDown() throws IOException {
        database.drop();
    }


    // Correctly parses column definitions with name, type, and multiple constraints
    @Test
    public void test_parses_column_with_multiple_constraints() {
        
        String columnDefinition = "id INT NOT NULL PRIMARY KEY";
        String[] result = database.parseColumnDefinition(columnDefinition);
        assertArrayEquals(new String[]{"id", "INT", "NOT NULL", "PRIMARY KEY"}, result);
    }

    // Correctly parses column definitions with name and type only
    @Test
    public void test_parses_column_with_name_and_type_only() {
        
        String columnDefinition = "name VARCHAR";
        String[] result = database.parseColumnDefinition(columnDefinition);
        assertArrayEquals(new String[]{"name", "VARCHAR"}, result);
    }

    // Correctly handles and splits constraints like NOT NULL, PRIMARY KEY, UNIQUE, CHECK, FOREIGN KEY
    @Test
    public void test_handles_and_splits_constraints() {
        
        String columnDefinition = "age INT NOT NULL UNIQUE";
        String[] result = database.parseColumnDefinition(columnDefinition);
        assertArrayEquals(new String[]{"age", "INT", "NOT NULL", "UNIQUE"}, result);
    }

    // Returns an array with column name, type, and constraints in the correct order
    @Test
    public void test_returns_array_in_correct_order() {
        
        String columnDefinition = "salary DECIMAL CHECK (salary > 0)";
        String[] result = database.parseColumnDefinition(columnDefinition);
        assertArrayEquals(new String[]{"salary", "DECIMAL", "CHECK (salary > 0)"}, result);
    }

    // Handles leading and trailing spaces in the column definition string
    @Test
    public void test_handles_leading_and_trailing_spaces() {
        
        String columnDefinition = "  description TEXT NOT NULL  ";
        String[] result = database.parseColumnDefinition(columnDefinition);
        assertArrayEquals(new String[]{"description", "TEXT", "NOT NULL"}, result);
    }

    // Throws an exception for column definitions with missing column name
    @Test()
    public void test_throws_exception_for_missing_column_name() {
        
        String columnDefinition = " INT NOT NULL";
        database.parseColumnDefinition(columnDefinition);
    }

    // Throws an exception for column definitions with missing column type
    @Test()
    public void test_throws_exception_for_missing_column_type() {
        String columnDefinition = "age ";
        assertThrows(IllegalArgumentException.class,()->database.parseColumnDefinition(columnDefinition));
    }

    // Handles column definitions with no constraints gracefully
    @Test
    public void test_handles_no_constraints_gracefully() {
        
        String columnDefinition = "email VARCHAR";
        String[] result = database.parseColumnDefinition(columnDefinition);
        assertArrayEquals(new String[]{"email", "VARCHAR"}, result);
    }

    // Correctly parses column definitions with complex CHECK constraints containing spaces
    @Test
    public void test_parses_complex_check_constraints() {
        
        String columnDefinition = "age INT CHECK (age >= 18 AND age <= 65)";
        String[] result = database.parseColumnDefinition(columnDefinition);
        assertArrayEquals(new String[]{"age", "INT", "CHECK (age >= 18 AND age <= 65)"}, result);
    }

    // Handles column definitions with multiple constraints of the same type (e.g., multiple CHECK constraints)
    @Test
    public void test_handles_multiple_constraints_of_same_type() {
        
        String columnDefinition = "price DECIMAL CHECK (price > 0) CHECK (price < 1000)";
        String[] result = database.parseColumnDefinition(columnDefinition);
        assertArrayEquals(new String[]{"price", "DECIMAL", "CHECK (price > 0)", "CHECK (price < 1000)"}, result);
    }

    // Correctly handles column definitions with unusual but valid characters in constraints
    @Test
    public void test_handles_unusual_but_valid_characters_in_constraints() {
        
        String columnDefinition = "path VARCHAR CHECK (path LIKE '/home/%')";
        String[] result = database.parseColumnDefinition(columnDefinition);
        assertArrayEquals(new String[]{"path", "VARCHAR", "CHECK (path LIKE '/home/%')"}, result);
    }
}