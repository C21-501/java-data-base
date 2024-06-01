package database.system.core.types;


public enum DataType {
    INTEGER,
    STRING,
    REAL,
    BOOLEAN;

    public static DataType map(Object value) {
        return switch (value.getClass().getName()) {
            case "java.lang.String" -> STRING;
            case "java.lang.Integer" -> INTEGER;
            case "java.lang.Double" -> REAL;
            case "java.lang.Boolean" -> BOOLEAN;
            default -> null;
        };
    }

    public static boolean validate(String columnType) {
        try {
            DataType.valueOf(columnType.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}