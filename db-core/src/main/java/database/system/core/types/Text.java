package database.system.core.types;


public class Text extends AbstractDataType {
    public Text(String data) {
        super(data, DEFAULT_DATA_LENGTH);
    }

    public Text(String data, int length) {
        super(data,length);
    }

    @Override
    public byte[] getBytes() {
        return String.valueOf(data).getBytes();
    }
}
