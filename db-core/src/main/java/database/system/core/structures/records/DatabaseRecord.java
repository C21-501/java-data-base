package database.system.core.structures.records;

import database.system.core.structures.Database;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Data
public class DatabaseRecord implements Serializable {
    public static Map<Integer, Database> databaseTreeMap = new TreeMap<>();
}
