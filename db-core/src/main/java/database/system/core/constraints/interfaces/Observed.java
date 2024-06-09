package database.system.core.constraints.interfaces;

import database.system.core.constraints.ConstraintEnum;

public interface Observed {
    void subscribe(ConstraintEnum constraint, Listener listener);
    void unsubscribe(ConstraintEnum constraint, Listener listener);
    void notify(Object data);
}
