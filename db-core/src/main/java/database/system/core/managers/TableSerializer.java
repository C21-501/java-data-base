package database.system.core.managers;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TableSerializer extends Serializer{
    private List<String> tableNames;

    @Override
    void save() {

    }

    @Override
    void create() {

    }

    @Override
    void read() {

    }
}
