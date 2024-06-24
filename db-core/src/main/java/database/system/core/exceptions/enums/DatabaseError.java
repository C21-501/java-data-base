package database.system.core.exceptions.enums;

import lombok.Getter;

@Getter
public enum DatabaseError {
    DATABASE_NAME_NULL("Error while creating database: database name is null"),
    DATABASE_NAME_OR_PATH_NULL("Error while creating database: database name or path is null"),
    DATABASE_INSTANCE_NULL("Error while restoring database state: database instance is null"),
    DROPPING_DATABASE("Error while dropping database: '%s'"),
    DATABASE_DOES_NOT_EXIST("Error: Database '%s' does not exist."),
    RENAMING_DATABASE_EXISTS("Error while renaming database: '%s' already exists"),
    RENAMING_FILE("Error while renaming file: '%s'"),
    RENAMING_DATABASE("Error while renaming database: '%s'"),
    DATABASE_NAME_OR_NEW_NAME_EMPTY("Error: Database name or new name is empty");

    private final String messageTemplate;

    DatabaseError(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }
}
