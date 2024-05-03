package database.types;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractDataType implements Serializable {
    public static final int INTEGER_BYTE_LENGTH = 4;
    public static final int DEFAULT_DATA_LENGTH = 1024;

    protected Object data;
    private int byteLength;


    AbstractDataType(String data){
        if (data == null) throw new IllegalArgumentException("Data cannot be null.");
        if (data.length() > DEFAULT_DATA_LENGTH) throw new IllegalArgumentException("Data length exceeds specified length.");
        this.data = (String) data;
        this.byteLength = DEFAULT_DATA_LENGTH;
    }

    AbstractDataType(String data, int byteLength){
        if (byteLength > DEFAULT_DATA_LENGTH) throw new IllegalArgumentException("Data length exceeds specified length.");
        this(data);
        this.byteLength = byteLength;
    }

    AbstractDataType(Number data){
        this.data = data.intValue();
        this.byteLength = INTEGER_BYTE_LENGTH;
    }

    public abstract byte[] getBytes();
}
