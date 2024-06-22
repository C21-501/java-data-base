package database.monitor;

import database.api.CommandHistory;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import database.api.utils.OUTPUT_TYPE;
import database.service.tools.SQLListener;
import database.service.tools.grammar.SQLGrammarLexer;
import database.service.tools.grammar.SQLGrammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Optional;
import java.util.Scanner;

public class Monitor {
    private static final Scanner in = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Monitor.class);
    private static OUTPUT_TYPE outputType = OUTPUT_TYPE.CONSOLE; // Начальное состояние - вывод в консоль
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private static Optional<String> inputFilePath = Optional.empty(); // Начальное состояние - ввод с консоли
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private static Optional<String> outputFilePath = Optional.empty(); // Начальное состояние - вывод в консоль
    private static MonitorState monitorState = MonitorState.CLI; // Начальное состояние - CLI

    public enum MonitorState {
        CLI,
        FS,
        RESET,
    }

    public static void readCommandsFromFile(String fileName, DatabaseAPI databaseAPI, OUTPUT_TYPE outputType, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<String> filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder commands = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("//")) {
                    commands.append(line).append('\n');
                }
            }

            String[] commandsList = commands.toString().split(";");
            for (String cmd : commandsList) {
                if (!cmd.equals("\n")) {
                    CharStream stream = CharStreams.fromString(cmd);
                    SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
                    SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));

                    parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
                    lexer.removeErrorListeners();
                    parser.addParseListener(new SQLListener(databaseAPI, outputType, filePath));
                    try {
                        parser.start();
                    } catch (Exception e) {
                        logger.error("Error in parser: {}", e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error reading commands from file: {}", e.getMessage());
        }
    }

    public static void readCommandsFromCommandLine(DatabaseAPI databaseAPI, OUTPUT_TYPE outputType, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<String> filePath) {
        System.out.println("\u001B[34m" + "Please enter your commands. Type ':r' to run commands ':q' to quit CLI mode or 'change mod' to change mode." + "\u001B[0m");
        StringBuilder commands = new StringBuilder();
        String line = in.nextLine();
        while (!line.equalsIgnoreCase(":q")) {
            if (line.equalsIgnoreCase("change mod")) {
                changeMode();
                return;
            }
            if (line.equalsIgnoreCase(":r")) {
                runSQLCommands(databaseAPI, outputType, filePath, commands);
                commands.setLength(0); // Clear commands after running
            } else {
                commands.append(line).append(" ");
            }
            line = in.nextLine();
        }
        if (!commands.isEmpty()) {
            runSQLCommands(databaseAPI, outputType, filePath, commands);
        }
        System.out.println("\u001B[32m" + "Exiting CLI mode." + "\u001B[0m");
    }

    private static void runSQLCommands(
            DatabaseAPI databaseAPI,
            OUTPUT_TYPE outputType,
            @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<String> filePath,
            StringBuilder commands
    ) {
        String[] commandList = commands.toString().split(";");
        for (String command : commandList) {
            command = command.trim();
            if (command.isEmpty()) {
                continue;
            }
            CharStream stream = CharStreams.fromString(command);
            SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
            SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));
            parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
            lexer.removeErrorListeners();
            parser.addParseListener(new SQLListener(databaseAPI, outputType, filePath));
            try {
                parser.start();
            } catch (Exception e) {
                logger.error("Error parsing command: {}", e.getMessage());
            }
        }
    }


    public static void handleCommandLine(DatabaseAPI databaseAPI) {
        System.out.println("\u001B[36m" + "You have selected Command Line Interface mode." + "\u001B[0m");
        readCommandsFromCommandLine(databaseAPI, outputType, outputFilePath);
    }

    public static void handleFileSystem(DatabaseAPI databaseAPI) {
        System.out.println("\u001B[36m" + "You have selected File System Interface mode." + "\u001B[0m");
        if (inputFilePath.isPresent()) {
            readCommandsFromFile(inputFilePath.get(), databaseAPI, outputType, outputFilePath);
        } else {
            System.out.println("\u001B[31m" + "Input file not specified. Please set the input file path using 'change mod' command." + "\u001B[0m");
        }
    }

    public static void displayHelpCommands() {
        System.out.println("\u001B[33m" + "Available commands:" + "\u001B[0m");
        System.out.println("  \u001B[33m" + "CLI" + "\u001B[0m" + " - Switch to Command Line Interface mode.");
        System.out.println("  \u001B[33m" + "FS" + "\u001B[0m" + " - Switch to File System mode.");
        System.out.println("  \u001B[33m" + "HELP" + "\u001B[0m" + " - Display available commands.");
        System.out.println("  \u001B[33m" + "change mod" + "\u001B[0m" + " - Change input/output mode and specify file paths.");
        System.out.println("  \u001B[33m" + "exit" + "\u001B[0m" + " - Exit the program.");
    }

    public static void changeMode() {
        System.out.println("\u001B[35m" + "Please specify the input mode: CLI or FS" + "\u001B[0m");
        monitorState = MonitorState.RESET;
        String mode = in.nextLine();
        if (mode.equalsIgnoreCase("CLI")) {
            inputFilePath = Optional.empty();
            monitorState = MonitorState.CLI;
        } else if (mode.equalsIgnoreCase("FS")) {
            System.out.println("\u001B[35m" + "Please enter the input file path:" + "\u001B[0m");
            String filePath = in.nextLine();
            inputFilePath = Optional.of(filePath);
            monitorState = MonitorState.FS;
        } else {
            System.out.println("\u001B[31m" + "Invalid input mode specified." + "\u001B[0m");
        }

        System.out.println("\u001B[35m" + "Please specify the output mode: Console (C) or File (F)" + "\u001B[0m");
        String outputMode = in.nextLine();
        if (outputMode.equalsIgnoreCase("C")) {
            outputType = OUTPUT_TYPE.CONSOLE;
            outputFilePath = Optional.empty();
        } else if (outputMode.equalsIgnoreCase("F")) {
            System.out.println("\u001B[35m" + "Please enter the output file path:" + "\u001B[0m");
            String filePath = in.nextLine();
            outputType = OUTPUT_TYPE.FILE;
            outputFilePath = Optional.of(filePath);
        } else {
            System.out.println("\u001B[31m" + "Invalid output mode specified." + "\u001B[0m");
        }
    }

    public static void main(String[] ignoredArgs) {
        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.setActiveEditor(new DatabaseEditor());
        databaseAPI.setHistory(new CommandHistory());
        System.out.println("\u001B[32m" + "Welcome to the database monitor!" + "\u001B[0m");
        System.out.printf("\u001B[32m" + "Current working mode: %s%n" + "\u001B[0m", monitorState);
        try {
            while (true) {
                if (monitorState == MonitorState.CLI) {
                    handleCommandLine(databaseAPI);
                } else if (monitorState == MonitorState.FS) {
                    handleFileSystem(databaseAPI);
                }
                System.out.println("\u001B[33m" + "Enter 'HELP' for available commands, 'change mod' to change mode, or 'exit' to quit:" + "\u001B[0m");
                String input = in.nextLine();
                if (input.equalsIgnoreCase("change mod")) {
                    changeMode();
                    System.out.printf("\u001B[32m" + "Mode changed to: %s%n" + "\u001B[0m", monitorState);
                } else if (input.equalsIgnoreCase("exit")) {
                    System.out.println("\u001B[32m" + "Exiting the program. Goodbye!" + "\u001B[0m");
                    break;
                } else if (input.equalsIgnoreCase("HELP")) {
                    displayHelpCommands();
                } else {
                    System.out.println("\u001B[31m" + "Invalid command. Please enter a valid option." + "\u001B[0m");
                }
            }
        } finally {
            System.out.println("\u001B[32m" + "Program terminated." + "\u001B[0m");
        }
    }
}
