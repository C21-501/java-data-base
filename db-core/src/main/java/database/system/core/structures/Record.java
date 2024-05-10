package database.system.core.structures;

import lombok.Data;
import java.util.Map;

@Data
public class Record {
    private Map<String, Object> value;

    public Record(Map<String, Object> data) {
        this.value = data;
    }
}
