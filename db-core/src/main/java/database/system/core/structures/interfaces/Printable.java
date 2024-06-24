package database.system.core.structures.interfaces;

import database.api.utils.OUTPUT_TYPE;

import java.util.Optional;

@FunctionalInterface
public interface Printable {
    void print(OUTPUT_TYPE outputType, Optional<String> filePath);
}
