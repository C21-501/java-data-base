package database.system.core.structures.schemes;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.bodies.Body;
import database.system.core.types.DataType;
import lombok.Data;

import java.util.*;

import static database.system.core.types.DataType.map;

@Data
public class FieldScheme implements Scheme {
    private DataType type;
    private Set<Constraint> constraintSet = new HashSet<>();

    public FieldScheme() {}

    public FieldScheme(DataType type) {
        if (type == null)
            throw new NullPointerException("Error: parameter 'type' is null.");
        this.type = type;
    }

    public FieldScheme(DataType type, Set<Constraint> constraintSet) {
        if (type == null)
            throw new NullPointerException("Error: parameter 'type' is null.");
        if (constraintSet == null || constraintSet.isEmpty())
            throw new NullPointerException("Error: parameter 'constraintSet' is null or empty.");
        this.type = type;
        this.constraintSet = constraintSet;
    }

    public void addConstraint(Constraint constraint) {
        if (constraint == null)
            throw new NullPointerException("Error: parameter 'constraint' is null.");
        constraintSet.add(constraint);
    }

    public void removeConstraint(Constraint constraint) {
        if (constraint == null)
            throw new NullPointerException("Error: parameter 'constraint' is null.");
        constraintSet.remove(constraint);
    }

    public boolean contains(Constraint constraint) {
        if (constraint == null)
            throw new NullPointerException("Error: parameter 'constraint' is null.");
        return constraintSet.contains(constraint);
    }

    public boolean contains(Class<?> constraintClass) {
        if (constraintClass == null)
            throw new NullPointerException("Error: parameter 'constraintClass' is null.");
        return constraintSet.stream().anyMatch(
                (constraint) -> constraint.getClass().equals(constraintClass)
        );
    }

    public boolean validate(Body parent, Object value) {
        DataType valueType = map(value);
        if (valueType != type) {
            throw new IllegalArgumentException(String.format(
                    "Error: Type mismatch - expected '%s', but got '%s' (%s).",
                    type, valueType, value.getClass().getName()
            ));
        }
        for (Constraint constraint : constraintSet) {
            if (!constraint.check(parent, value)) {
                throw new IllegalArgumentException(String.format(
                        "Error: Constraint violation - %s failed for value '%s'.",
                        constraint.getClass().getName(), value
                ));
            }
        }
        return true;
    }

    @Override
    public long getObjectsNumber() {
        return 1;
    }
}
