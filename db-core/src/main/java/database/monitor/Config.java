package database.monitor;

import database.api.utils.OUTPUT_TYPE;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

@Getter
public class Config {
    public static final String HELP_FILE_PATH = "help.xml";
    public static final String ROOT_DATABASE_PATH = "root";
    public static final String NULL_STRING = "NULL";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final String DATABASE_PATH_FORMAT = "%s/%s/%s.instance";
    public static final OUTPUT_TYPE CURRENT_OUTPUT_TYPE = OUTPUT_TYPE.CONSOLE;
    public static final String CURRENT_OUTPUT_PATH = null;

    public static String getDatabaseFilePath(String databasePath, String databaseName) {
        return String.format(DATABASE_PATH_FORMAT, databasePath, databaseName, databaseName);
    }
}