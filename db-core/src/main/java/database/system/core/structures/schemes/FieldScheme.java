package database.system.core.structures.schemes;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.bodies.Body;
import database.system.core.types.DataType;
import lombok.Data;

import java.util.*;

import static database.system.core.types.DataType.map;

@Data
public class FieldScheme implements Scheme{
    private DataType type;
    private Set<Constraint> constraintSet = new HashSet<>();

    public FieldScheme(DataType type) {
        if (type == null)
            throw new NullPointerException("`type` is null");
        this.type = type;
    }

    public FieldScheme(DataType type, Set<Constraint> constraintSet) {
        if (type == null || constraintSet.isEmpty())
            throw new NullPointerException("`type` is null or `constraintSet` is empty");
        this.type = type;
        this.constraintSet = constraintSet;
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

    public boolean validate(Body parent, Object value) {
        if (map(value) == null)
            throw new IllegalArgumentException(STR."\{value.getClass()} not instance of class DataType");
        for (Constraint constraint: constraintSet){
            if (!constraint.check(parent, value))
                return false;
        }
        return true;
    }


    @Override
    public long getObjectsNumber() {
        return 1;
    }
}
