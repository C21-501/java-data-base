package database.system.core.structures;

import database.system.core.constraints.ConstraintEnum;
import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.ConstraintManager;
import database.system.core.types.DataType;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Field {
    private ConstraintManager constraints;
    private DataType type;
    private Object data;
    private Set<ConstraintEnum> constraintEnumSet;

    public Field(DataType type) {
        if (type == null)
            throw new NullPointerException("`type` is null");
        this.type = type;
        constraints = new ConstraintManager();
        constraintEnumSet = new HashSet<>();
    }

    public void setUpData(Object data){
        this.data = data;
        constraints.notify(data);
    }

    public void deleteData(){
        this.data = null;
        constraints.notify(null);
    }

    public void updateData(Object newData){
        this.data = newData;
        constraints.notify(newData);
    }

    public void addConstraint(Constraint constraint){
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        constraintEnumSet.add(constraint.get());
    }

    public void removeConstraint(Constraint constraint){
        if (constraint == null)
            throw new NullPointerException("parameter `constraint` is null");
        constraintEnumSet.remove(constraint.get());
    }
}
