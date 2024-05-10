package database.system.core.types;

public class Integer extends AbstractDataType {

    public Integer(int data) {
        super(data);
    }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }
}
