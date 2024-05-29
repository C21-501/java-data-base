package database.system.core.types;


import database.system.core.structures.Column;

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
}