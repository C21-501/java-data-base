package database.system.core.types;


public enum DataType {
    INTEGER,
    STRING,
    REAL,
    BOOLEAN;

    public static DataType map(Object value) {
        if (value == null)
            return null;
        return switch (value.getClass().getName()) {
            case "java.lang.String" -> STRING;
            case "java.lang.Integer" -> INTEGER;
            case "java.lang.Double" -> REAL;
            case "java.lang.Boolean" -> BOOLEAN;
            default -> throw new IllegalStateException("Unexpected value: %s.%n".formatted(value.getClass().getName()));
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