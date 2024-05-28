package database.system.core.structures;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.structures.bodies.FieldBody;
import database.system.core.structures.schemes.FieldScheme;
import database.system.core.types.DataType;
import lombok.Data;

import java.util.function.Predicate;

@Data
public class Column implements DatabaseStructure {
    private FieldScheme fieldScheme = new FieldScheme();
    private FieldBody fieldBody = new FieldBody();

    public Column(DataType type){
        fieldScheme.setType(type);
    }

    public Column setConstraint(Constraint constraint){
        fieldScheme.addConstraint(constraint);
        fieldBody.validate(fieldScheme);
        return this;
    }

    public Column removeConstraint(Constraint constraint){
        fieldScheme.removeConstraint(constraint);
        return this;
    }

    public void insert(byte[] value) {
        fieldBody.insertValue(fieldScheme, value);
    }

    public void delete(Predicate<Object[]> filter) {

    }

    public void update(Object value, Predicate<Object[]> filter) {
    }
}
