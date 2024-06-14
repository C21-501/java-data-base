package database.system.core.structures.schemes;

import database.system.core.constraints.Constraint;
import database.system.core.types.DataType;
import lombok.Data;

import java.util.*;

import static database.system.core.types.DataType.map;

@Data
public class ColumnScheme implements Scheme {
    private DataType type;
    private Object valueByDefault = null;
    private Map<String,Constraint> constraintHashMap = new TreeMap<>();

    public ColumnScheme() {}

    public ColumnScheme(DataType type) {
        if (type == null)
            throw new NullPointerException("Error: parameter 'type' is null.");
        this.type = type;
    }

    public ColumnScheme(DataType type, Map<String,Constraint> constraintHashMap) {
        if (type == null)
            throw new NullPointerException("Error: parameter 'type' is null.");
        if (constraintHashMap == null || constraintHashMap.isEmpty())
            throw new NullPointerException("Error: parameter 'constraintSet' is null or empty.");
        this.type = type;
        this.constraintHashMap = constraintHashMap;
    }

    public ColumnScheme(ColumnScheme other) {
        if (!this.equals(other)){
            this.type = other.type;
            this.valueByDefault = other.valueByDefault;
            this.constraintHashMap = new HashMap<>(other.constraintHashMap);
        }
    }

    public void addConstraint(Constraint constraint) {
        if (constraint == null)
            throw new NullPointerException("Error: parameter 'constraint' is null.");
        constraintHashMap.put(constraint.getConstraintName(), constraint);
    }

    public void removeConstraint(String constraintName) {
        if (constraintName == null)
            throw new NullPointerException("Error: parameter 'constraint' is null.");
        constraintHashMap.remove(constraintName);
    }

    public boolean contains(Constraint constraint) {
        if (constraint == null)
            throw new NullPointerException("Error: parameter 'constraint' is null.");
        return constraintHashMap.containsValue(constraint);
    }

    public boolean contains(Class<?> constraintClass) {
        if (constraintClass == null)
            throw new NullPointerException("Error: parameter 'constraintClass' is null.");
        return constraintHashMap.values().stream().anyMatch(
                (constraint) -> constraint.getClass().equals(constraintClass)
        );
    }

    public boolean validate(Object value) {
        DataType valueType = map(value);
        if (valueType != type && value != null) {
            throw new IllegalArgumentException(String.format(
                    "Error: Type mismatch - expected '%s', but got '%s' (%s).",
                    type, valueType, value.getClass().getName()
            ));
        }
        for (Constraint constraint : constraintHashMap.values()) {
            if (!constraint.serve(value)) {
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

    public Object convertValueToValidType(String value) {
        switch (type) {
            case INTEGER -> {
                try {
                    return Integer.valueOf(value);
                } catch (NumberFormatException e) {
                    throw new RuntimeException(String.format("Error: Expected INTEGER type, but received: %s.%n", value));
                }
            }
            case STRING -> {
                return value;
            }
            case REAL -> {
                try {
                    return Double.valueOf(value);
                } catch (NumberFormatException e) {
                    throw new RuntimeException(String.format("Error: Expected REAL type, but received: %s.%n", value));
                }
            }
            case BOOLEAN -> {
                if (!"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value)) {
                    throw new IllegalArgumentException(String.format("Error: Expected BOOLEAN type, but received: %s.%n", value));
                }
                return Boolean.valueOf(value);
            }
            default -> throw new IllegalArgumentException(String.format("Unknown type: %s.%n", type));
        }
    }
}
