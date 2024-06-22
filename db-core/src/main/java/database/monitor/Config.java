package database.monitor;

import database.api.utils.OUTPUT_TYPE;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class Config {
    public static final OUTPUT_TYPE DEFAULT_OUTPUT_TYPE = OUTPUT_TYPE.CONSOLE;
    public static final String CURRENT_OUTPUT_PATH = null;

    public static final String HELP_FILE_PATH = "help.xml";
    public static final String ROOT_DATABASE_PATH = "root";
    public static final String NULL_STRING = "NULL";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final String DATABASE_PATH_FORMAT = "%s/%s/%s.instance";

    @Getter
    public enum COLOR {
        ANSI_BLUE("\u001B[34m"),
        ANSI_GREEN("\u001B[32m"),
        ANSI_RESET("\u001B[0m"),
        ANSI_CYAN("\u001B[36m"),
        ANSI_PURPLE("\u001B[35m"),
        ANSI_YELLOW("\u001B[33m"),
        ANSI_RED("\u001B[31m");

        private final String code;

        COLOR(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return getCode();
        }
    }

    public static String getDatabaseFilePath(String databasePath, String databaseName) {
        return String.format(DATABASE_PATH_FORMAT, databasePath, databaseName, databaseName);
    }
}