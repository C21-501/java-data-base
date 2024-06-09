package database.system.core.managers.utils;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Insertion {
    private String tableName;
    private String[] columns;
    private List<Object[]> values;
}
