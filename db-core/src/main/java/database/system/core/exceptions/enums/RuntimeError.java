package database.system.core.exceptions.enums;

import lombok.Getter;

@Getter
public enum RuntimeError {
    DROPPING_DATABASE("Error while dropping database: '%s'"),
    DATABASE_DOES_NOT_EXIST("Error: Database does not exist. Please create new one."),
    DATABASE_WITH_NAME_DOES_NOT_EXIST("Error: Database with name '%s' does not exist."),
    RENAMING_DATABASE_EXISTS("Error while renaming database: '%s' already exists"),
    RENAMING_FILE("Error while renaming file: '%s'"),
    RENAMING_DATABASE("Error while renaming database: '%s'"),
    DATABASE_ALREADY_EXIST("Error: database with name %s already exists."),
    TABLE_ALREADY_EXIST("Error: table with name '%s' already exists."),
    COLUMN_ALREADY_EXIST("Error: column with name '%s' already exists."),
    INVALID_DATABASE_STATE("Error: invalid database state '%s'."),
    TABLE_DOES_NOT_EXIST("Error: table with name '%s' does not exists."),
    CANT_SAVE_INSTANCE_TO_FILE_WITH_MESSAGE("Error: can't save instance to file '%s' with message: %s."),
    VALUE_VALIDATION_ERROR("Error: can't update column, because value '%s' is incorrect."),
    CANT_REMOVE_VALUES("Error: can't remove values, because of invalid condition.");

    private final String messageTemplate;

    RuntimeError(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }
}
