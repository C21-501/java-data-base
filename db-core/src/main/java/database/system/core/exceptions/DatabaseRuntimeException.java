package database.system.core.exceptions;

import database.system.core.exceptions.enums.RuntimeError;

public class DatabaseRuntimeException extends DatabaseException {

    public DatabaseRuntimeException(RuntimeError errorType) {
        super(errorType.getMessageTemplate());
    }

    public DatabaseRuntimeException(RuntimeError errorType, String message) {
        super(String.format(errorType.getMessageTemplate(), message));
    }

    public DatabaseRuntimeException(RuntimeError errorType, String message, String errorMessage) {
        super(String.format(errorType.getMessageTemplate(), message, errorMessage));
    }
}
