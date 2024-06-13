package database.system.core.constraints;

import database.system.core.constraints.interfaces.Observed;
import database.system.core.constraints.interfaces.Listener;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ConstraintManager implements Observed {
    private final Map<ConstraintEnum, Listener> subscribers;

    public ConstraintManager(){
        this.subscribers = new HashMap<>();
    }

    @Override
    public void subscribe(ConstraintEnum constraint, Listener listener) {
        if (listener == null)
            throw new NullPointerException("Subscriber can not be null");
        subscribers.put(constraint,listener);
    }

    @Override
    public void unsubscribe(ConstraintEnum constraint, Listener listener) {
        subscribers.remove(constraint, listener);
    }

    @Override
    public void notify(Object data) {
        for (Map.Entry<ConstraintEnum, Listener> entry : subscribers.entrySet()) {
            entry.getValue().update(data);
        }
    }

    public boolean contains(ConstraintEnum constraint) {
        return subscribers.containsKey(constraint);
    }
}
