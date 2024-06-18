package database.system.core.exceptions;

import database.system.core.exceptions.enums.RuntimeError;

public class DatabaseRuntimeException extends DatabaseException {
    public DatabaseRuntimeException(RuntimeError errorType, String errorMessage) {
        super(String.format(errorType.getMessageTemplate(), errorMessage));
    }
}
