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
}