package database.system.core.exceptions.enums;

import lombok.Getter;

@Getter
public enum RuntimeError {
    DROPPING_DATABASE("Error while dropping database: %s"),
    DATABASE_DOES_NOT_EXIST("Error: Database %s does not exist."),
    RENAMING_DATABASE_EXISTS("Error while renaming database: %s already exists"),
    RENAMING_FILE("Error while renaming file: %s"),
    RENAMING_DATABASE("Error while renaming database: %s"),
    DATABASE_ALREADY_EXIST("Error: database with name %s already exists."),
    TABLE_ALREADY_EXIST("Error: table with name %s already exists."),
    COLUMN_ALREADY_EXIST("Error: column with name %s already exists.");
    private final String messageTemplate;

    RuntimeError(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }
}
