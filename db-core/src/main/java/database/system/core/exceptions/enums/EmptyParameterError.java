package database.system.core.exceptions.enums;

import lombok.Getter;

@Getter
public enum EmptyParameterError {
    DATABASE_NAME_NULL("Database name is null"),
    DATABASE_PATH_NULL("Database path is null"),
    DATABASE_NAME_OR_NEW_NAME_EMPTY("Database name or new name is empty");

    private final String message;

    EmptyParameterError(String message) {
        this.message = message;
    }
}
