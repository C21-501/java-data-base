package database.system.core.structures.bodies;

import java.io.Serializable;
import java.util.List;

public interface Body extends Serializable {
    List<Object> getInnerObjects();
}
