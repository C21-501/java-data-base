package database.system.core.exceptions;

import database.system.core.exceptions.enums.InvalidParameterError;

public class InvalidParameterException extends DatabaseException {
    public InvalidParameterException(InvalidParameterError errorType) {
        super(errorType.getMessage());
    }
}
