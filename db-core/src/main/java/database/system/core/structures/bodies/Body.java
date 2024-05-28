package database.system.core.structures.bodies;

import database.system.core.structures.Table;
import database.system.core.structures.Value;

import java.io.Serializable;
import java.util.List;

public interface Body extends Serializable {
    List<Value> getValues();
}
