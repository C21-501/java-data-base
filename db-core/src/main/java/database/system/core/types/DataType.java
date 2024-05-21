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
            // Добавьте другие типы данных по аналогии
            default -> null;
        };
    }

    public static void main(String[] args) {
        String string = "sssssq";
        System.out.println(map(string));
    }
}