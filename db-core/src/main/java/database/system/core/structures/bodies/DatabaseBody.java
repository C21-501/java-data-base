package database.system.core.structures.bodies;

import database.system.core.structures.schemes.DatabaseScheme;
import database.system.core.structures.schemes.TableScheme;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class DatabaseBody implements Body {
    private Integer tableId = 0;
    private DatabaseScheme databaseScheme;
    private List<TableBody> columnBodyList;

    public DatabaseBody(DatabaseScheme databaseScheme) {
        if (databaseScheme == null)
            throw new NullPointerException("`table` is null");
        this.databaseScheme = databaseScheme;
        this.columnBodyList = Stream.generate(TableBody::new)
                .limit(databaseScheme.getObjectsNumber())
                .collect(Collectors.toList());
    }

    @Override
    public List<Object> getInnerObjects() {
        return Collections.singletonList(columnBodyList);
    }
}
