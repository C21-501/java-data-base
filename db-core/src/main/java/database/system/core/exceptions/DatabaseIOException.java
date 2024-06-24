package database.system.core.exceptions;

import java.io.IOException;

public class DatabaseIOException extends IOException {
    public DatabaseIOException(String message, Throwable cause) {
        super(message, cause);
    }
}

