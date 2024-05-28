package database.system.core.structures;

import java.io.Serializable;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Value implements Serializable, Comparable {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    private final int id;
    private final byte[] object;

    public Value(byte[] o) {
        this.id = idGenerator.incrementAndGet();
        this.object = o;
    }

    public Value(int id, Object o) {
        this.id = id;
        this.object = (byte[]) o;
    }

    public static Value of(byte[] o) {
        return new Value(o);
    }

    @Override
    public int compareTo(@NotNull Object o) {
        if (o instanceof Value) {
            Value other = (Value) o;
            return Arrays.compare(this.object, other.object);
        }
        throw new IllegalArgumentException("Object is not an instance of Value");
    }
}


