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

    public static void readCommandsFromFile(String fileName, DatabaseAPI databaseAPI, OUTPUT_TYPE outputType, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<String> filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder commands = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("//")) {
                    commands.append(line).append('\n');
                }
            }

            CharStream stream = CharStreams.fromString(commands.toString());
            SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
            SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));

            parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
            lexer.removeErrorListeners();
            parser.addParseListener(new SQLListener(databaseAPI, outputType, filePath));
            parser.start();

        } catch (Exception e) {
            logger.error("Error reading commands from file: %s".formatted(e.getMessage()));
        }
    }

    public static void readCommandsFromCommandLine(DatabaseAPI databaseAPI, OUTPUT_TYPE outputType, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<String> filePath) {
        System.out.println("Please enter your commands. Type ':q' to quit.");
        StringBuilder commands = new StringBuilder();
        String line = in.nextLine();
        while (!line.equals(":q")) {
            commands.append(line).append(" ");
            line = in.nextLine();
        }

        CharStream stream = CharStreams.fromString(commands.toString());
        SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
        SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        lexer.removeErrorListeners();
        parser.addParseListener(new SQLListener(databaseAPI, outputType, filePath));

        try {
            parser.start();
        } catch (Exception e) {
            logger.error("Error parsing commands: %s".formatted(e.getMessage()));
        }
    }

    public static void handleCommandLine(DatabaseAPI databaseAPI) {
        System.out.println("You have selected Command Line Interface mode.");
        readCommandsFromCommandLine(databaseAPI, outputType, outputFilePath);
    }

    public static void handleFileSystem(DatabaseAPI databaseAPI) {
        if (inputFilePath.isPresent()) {
            readCommandsFromFile(inputFilePath.get(), databaseAPI, outputType, outputFilePath);
        } else {
            System.out.println("Input file not specified. Please set the input file path using 'change mod' command.");
        }
    }

    public static void displayHelpCommands() {
        System.out.println("Available commands:");
        System.out.println("  CLI - Switch to Command Line Interface mode.");
        System.out.println("  FS - Switch to File System mode.");
        System.out.println("  HELP - Display available commands.");
        System.out.println("  change mod - Change input/output mode and specify file paths.");
        System.out.println("  exit - Exit the program.");
    }

    public static void changeMode() {
        System.out.println("Please specify the input mode: CLI or FS");
        String mode = in.nextLine();
        if (mode.equalsIgnoreCase("CLI")) {
            inputFilePath = Optional.empty();
        } else if (mode.equalsIgnoreCase("FS")) {
            System.out.println("Please enter the input file path:");
            String filePath = in.nextLine();
            inputFilePath = Optional.of(filePath);
        } else {
            System.out.println("Invalid input mode specified.");
        }

        System.out.println("Please specify the output mode: Console (C) or File (F)");
        String outputMode = in.nextLine();
        if (outputMode.equalsIgnoreCase("C")) {
            outputType = OUTPUT_TYPE.CONSOLE;
            outputFilePath = Optional.empty();
        } else if (outputMode.equalsIgnoreCase("F")) {
            System.out.println("Please enter the output file path:");
            String filePath = in.nextLine();
            outputType = OUTPUT_TYPE.FILE;
            outputFilePath = Optional.of(filePath);
        } else {
            System.out.println("Invalid output mode specified.");
        }
    }

    public static void main(String[] ignoredArgs) {
        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.setActiveEditor(new DatabaseEditor());
        databaseAPI.setHistory(new CommandHistory());
        System.out.println("Welcome to the database monitor!");
        System.out.println("Please choose the working mode: Command Line Interface (CLI) or File System (FS), or type HELP for available commands.");
        try {
            while (true) {
                String input = in.nextLine();
                if (input.equalsIgnoreCase("CLI")) {
                    handleCommandLine(databaseAPI);
                } else if (input.equalsIgnoreCase("FS")) {
                    handleFileSystem(databaseAPI);
                } else if (input.equalsIgnoreCase("HELP")) {
                    displayHelpCommands();
                } else if (input.equalsIgnoreCase("change mod")) {
                    changeMode();
                } else if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid command. Please enter a valid option.");
                }
            }
        } finally {
            System.out.println("Program terminated.");
        }
    }
}
