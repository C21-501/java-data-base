package database.system.core.structures;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.ConstraintManager;
import database.system.core.types.DataType;
import database.system.core.types.Integer;
import lombok.Data;

import java.util.*;

@Data
public final class Field {
    private ConstraintManager constraints;
    private DataType type;
    private Set<Constraint> constraintSet;

    public Field(DataType type) {
        if (type == null)
            throw new NullPointerException("`type` is null");
        this.type = type;
        constraints = new ConstraintManager();
        constraintSet = new HashSet<>();
    }

    public void addConstraint(Constraint constraint){
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        constraintSet.add(constraint);
    }

    public void removeConstraint(Constraint constraint){
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        constraintSet.remove(constraint);
    }

    public boolean contains(Constraint constraintClass) {
        return constraintSet.contains(constraintClass);
    }

    public boolean contains(Class<?> constraintClass) {
        return Arrays.stream(constraintSet.toArray()).anyMatch(
                (object) -> object.getClass().equals(constraintClass)
        );
    }
}
