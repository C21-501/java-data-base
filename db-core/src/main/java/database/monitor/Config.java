package database.monitor;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Config {
    @Getter
    private final String[] commands;

    public Config() {
        commands = readCommandsFromResource("help.txt");
    }

    private String[] readCommandsFromResource(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found! " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public String[] getCommands() {
        return commands;
    }

    public static void main(String[] args) {
        Config config = new Config();
        for (String command : config.getCommands()) {
            System.out.println(command);
        }
    }
}