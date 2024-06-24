package database.system.core.exceptions.enums;

import lombok.Getter;

@Getter
public enum EmptyParameterError {
    DATABASE_NAME_NULL("Error: Database name is null"),
    DATABASE_PATH_NULL("Error: Database path is null"),
    DATABASE_NAME_OR_NEW_NAME_EMPTY("Error: Database name or new name is empty");

    private final String message;

    EmptyParameterError(String message) {
        this.message = message;
    }
}
