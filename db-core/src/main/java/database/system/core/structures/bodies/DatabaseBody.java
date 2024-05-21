package database.system.core.structures.bodies;

import database.system.core.structures.schemes.DatabaseScheme;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class DatabaseBody implements Body {
    public static Map<Integer, DatabaseScheme> databaseTreeMap = new TreeMap<>();

    @Override
    public List<Object> getInnerObjects() {
        return List.of();
    }
}
