package database.api.ddl;

import database.system.core.exceptions.DatabaseRuntimeException;
import database.system.core.exceptions.enums.RuntimeError;
import database.system.core.structures.Database;
import lombok.Data;

import java.util.Objects;

import static database.system.core.structures.Database.DatabaseState.CLOSED;
import static database.system.core.structures.Database.DatabaseState.IDLE;

@Data
public abstract class AbstractManager {
    protected Database database;

    protected AbstractManager(Database database){
        this.database = database;
    }

    protected void validateDatabaseState(){
        if (Objects.isNull(database))
            throw new DatabaseRuntimeException(RuntimeError.DATABASE_DOES_NOT_EXIST);
        else if (database.getState() == CLOSED || database.getState() == IDLE)
            throw new DatabaseRuntimeException(RuntimeError.INVALID_DATABASE_STATE, database.getState().name());
    }
}
