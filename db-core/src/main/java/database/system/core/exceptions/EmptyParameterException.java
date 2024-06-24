package database.system.core.exceptions;

import database.system.core.exceptions.enums.EmptyParameterError;

public class EmptyParameterException extends DatabaseException {
    public EmptyParameterException(EmptyParameterError errorType) {
        super(errorType.getMessage());
    }
}
