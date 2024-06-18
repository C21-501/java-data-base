package database.system.core.exceptions;

public class DatabaseIOException extends RuntimeException {
    public DatabaseIOException(String message, Throwable cause) {
        super(message, cause);
    }
}

