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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean containsUnwantedCharacters(String s) {
        String pattern = "[^A-Za-z0-9 (),\n\"'/_=]";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(s);
        return matcher.find();
    }

    public static void readCommandsFromFile(
            String fileName,
            DatabaseAPI databaseAPI,
            OUTPUT_TYPE outputType,
            @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<String> filePath
    ) {
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
                if (!cmd.trim().isEmpty()) {
                    try {
                        if(containsUnwantedCharacters(cmd)){
                            throw new RuntimeException("Syntax Error");
                        }

                        CharStream stream = CharStreams.fromString(cmd);
                        SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
                        SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));

                        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
                        lexer.removeErrorListeners();
                        parser.addParseListener(new SQLListener(databaseAPI, outputType, filePath));
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

    public static void readCommandsFromCommandLine(
            DatabaseAPI databaseAPI,
            OUTPUT_TYPE outputType,
            @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<String> filePath
    ) {
        System.out.printf("%sPlease enter your commands. Type ':r' to run commands, ':q' to quit CLI mode, or 'change mod' to change mode.%s%n", Config.COLOR.ANSI_BLUE, Config.COLOR.ANSI_RESET);
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
        System.out.printf("%sExiting CLI mode.%s%n", Config.COLOR.ANSI_GREEN, Config.COLOR.ANSI_RESET);
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
            if (command.startsWith("//"))
                continue;
            if (!command.isEmpty()) {
                try {
                    if(containsUnwantedCharacters(command)){
                        throw new RuntimeException("Syntax Error");
                    }
                    CharStream stream = CharStreams.fromString(command);
                    SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
                    SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));
                    parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
                    lexer.removeErrorListeners();
                    parser.addParseListener(new SQLListener(databaseAPI, outputType, filePath));
                    parser.start();
                } catch (Exception e) {
                    logger.error("Error parsing command: {}", e.getMessage());
                }
            }
        }
    }

    public static void handleCommandLine(DatabaseAPI databaseAPI) {
        System.out.printf("%sYou have selected Command Line Interface mode.%s%n", Config.COLOR.ANSI_CYAN, Config.COLOR.ANSI_RESET);
        readCommandsFromCommandLine(databaseAPI, outputType, outputFilePath);
    }

    public static void handleFileSystem(DatabaseAPI databaseAPI) {
        System.out.printf("%sYou have selected File System Interface mode.%s%n", Config.COLOR.ANSI_CYAN, Config.COLOR.ANSI_RESET);
        if (inputFilePath.isPresent()) {
            readCommandsFromFile(inputFilePath.get(), databaseAPI, outputType, outputFilePath);
        } else {
            System.out.printf("%sInput file not specified. Please set the input file path using 'change mod' command.%s%n", Config.COLOR.ANSI_RED, Config.COLOR.ANSI_RESET);
        }
    }

    public static void displayHelpCommands() {
        System.out.printf("%sAvailable commands:%s%n", Config.COLOR.ANSI_YELLOW, Config.COLOR.ANSI_RESET);
        System.out.printf("  %sCLI%s - Switch to Command Line Interface mode.%n", Config.COLOR.ANSI_YELLOW, Config.COLOR.ANSI_RESET);
        System.out.printf("  %sFS%s - Switch to File System mode.%n", Config.COLOR.ANSI_YELLOW, Config.COLOR.ANSI_RESET);
        System.out.printf("  %sHELP%s - Display available commands.%n", Config.COLOR.ANSI_YELLOW, Config.COLOR.ANSI_RESET);
        System.out.printf("  %schange mod%s - Change input/output mode and specify file paths.%n", Config.COLOR.ANSI_YELLOW, Config.COLOR.ANSI_RESET);
        System.out.printf("  %sexit%s - Exit the program.%n", Config.COLOR.ANSI_YELLOW, Config.COLOR.ANSI_RESET);
    }

    public static void changeMode() {
        System.out.printf("%sPlease specify the input mode: CLI or FS%s%n", Config.COLOR.ANSI_PURPLE, Config.COLOR.ANSI_RESET);
        monitorState = MonitorState.RESET;
        String mode = in.nextLine();
        if (mode.equalsIgnoreCase("CLI")) {
            inputFilePath = Optional.empty();
            monitorState = MonitorState.CLI;
        } else if (mode.equalsIgnoreCase("FS")) {
            System.out.printf("%sPlease enter the input file path:%s%n", Config.COLOR.ANSI_PURPLE, Config.COLOR.ANSI_RESET);
            String filePath = in.nextLine();
            inputFilePath = Optional.of(filePath);
            monitorState = MonitorState.FS;
        } else {
            System.out.printf("%sInvalid input mode specified.%s%n", Config.COLOR.ANSI_RED, Config.COLOR.ANSI_RESET);
        }

        System.out.printf("%sPlease specify the output mode: Console (C) or File (F)%s%n", Config.COLOR.ANSI_PURPLE, Config.COLOR.ANSI_RESET);
        String outputMode = in.nextLine();
        if (outputMode.equalsIgnoreCase("C")) {
            outputType = OUTPUT_TYPE.CONSOLE;
            outputFilePath = Optional.empty();
        } else if (outputMode.equalsIgnoreCase("F")) {
            System.out.printf("%sPlease enter the output file path:%s%n", Config.COLOR.ANSI_PURPLE, Config.COLOR.ANSI_RESET);
            String filePath = in.nextLine();
            outputType = OUTPUT_TYPE.FILE;
            outputFilePath = Optional.of(filePath);
        } else {
            System.out.printf("%sInvalid output mode specified.%s%n", Config.COLOR.ANSI_RED, Config.COLOR.ANSI_RESET);
        }
    }

    public static void main(String[] ignoredArgs) {
        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.setActiveEditor(new DatabaseEditor());
        databaseAPI.setHistory(new CommandHistory());
        System.out.printf("%sWelcome to the database monitor!%s%n", Config.COLOR.ANSI_GREEN, Config.COLOR.ANSI_RESET);
        System.out.printf("%sCurrent working mode: %%s%%n%s".formatted(Config.COLOR.ANSI_GREEN, Config.COLOR.ANSI_RESET), monitorState);
        try {
            while (true) {
                if (monitorState == MonitorState.CLI) {
                    handleCommandLine(databaseAPI);
                } else if (monitorState == MonitorState.FS) {
                    handleFileSystem(databaseAPI);
                }
                System.out.printf("%sEnter 'HELP' for available commands, 'change mod' to change mode, or 'exit' to quit:%s%n", Config.COLOR.ANSI_YELLOW, Config.COLOR.ANSI_RESET);
                String input = in.nextLine();
                if (input.equalsIgnoreCase("change mod")) {
                    changeMode();
                    System.out.printf("%sMode changed to: %%s%%n%s".formatted(Config.COLOR.ANSI_GREEN, Config.COLOR.ANSI_RESET), monitorState);
                } else if (input.equalsIgnoreCase("exit")) {
                    System.out.printf("%sExiting the program. Goodbye!%s%n", Config.COLOR.ANSI_GREEN, Config.COLOR.ANSI_RESET);
                    break;
                } else if (input.equalsIgnoreCase("HELP")) {
                    displayHelpCommands();
                } else {
                    System.out.printf("%sInvalid command. Please enter a valid option.%s%n", Config.COLOR.ANSI_RED, Config.COLOR.ANSI_RESET);
                }
            }
        } finally {
            System.out.printf("%sProgram terminated.%s%n", Config.COLOR.ANSI_GREEN, Config.COLOR.ANSI_RESET);
        }
    }
}
