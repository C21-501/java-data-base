package database.system.core.exceptions.enums;

import lombok.Getter;

@Getter
public enum InvalidParameterError {
    DATABASE_INSTANCE_NULL("Database instance is null");

    private final String message;

    InvalidParameterError(String message) {
        this.message = message;
    }
}
