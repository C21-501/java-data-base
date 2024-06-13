package database.system.core.structures;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Value implements Comparable<Object>, Serializable {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    private final int id;
    private Object object;

    public Value(Object o) {
        this.id = idGenerator.incrementAndGet();
        this.object = o;
    }

    public Value(int id, Object o) {
        this.id = id;
        this.object = o;
    }

    public static Value of(Object o) {
        if (o == null) {
            throw new NullPointerException("Error: parameter 'o' is null in Value.of method.");
        }
        return new Value(o);
    }

    public Value setObject(Object object) {
        this.object = object;
        return this;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return compareValues(this.object, o);
    }

    @SuppressWarnings("unchecked")
    private int compareValues(Object thisObject, Object otherObject) {
        if (thisObject instanceof Comparable && otherObject instanceof Comparable) {
            if (thisObject.getClass().equals(otherObject.getClass())) {
                return ((Comparable<Object>) thisObject).compareTo(otherObject);
            } else if (isNumberStringPair(thisObject, otherObject)) {
                Double thisValue = convertToDouble(thisObject);
                Double otherValue = convertToDouble(otherObject);
                return thisValue.compareTo(otherValue);
            } else {
                throw new IllegalArgumentException(String.format("Error: Objects '%s' and '%s' are not comparable in compareValues method.", thisObject.getClass().getName(), otherObject.getClass().getName()));
            }
        } else {
            throw new IllegalArgumentException(String.format("Error: Objects '%s' and '%s' are not instances of Comparable in compareValues method.", thisObject.getClass().getName(), otherObject.getClass().getName()));
        }
    }

    private boolean isNumberStringPair(Object obj1, Object obj2) {
        return (obj1 instanceof String && obj2 instanceof Number) || (obj1 instanceof Number && obj2 instanceof String);
    }

    private Double convertToDouble(Object obj) {
        if (obj instanceof String) {
            try {
                return Double.parseDouble((String) obj);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format("Error: String '%s' is not a valid number in convertToDouble method.", obj));
            }
        } else if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        } else {
            throw new IllegalArgumentException(String.format("Error: Object '%s' is neither a String nor a Number in convertToDouble method.", obj));
        }
    }
}
