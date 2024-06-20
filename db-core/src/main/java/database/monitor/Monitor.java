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
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Monitor {
    private static final Scanner in = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(MonitorOld.class);

    public static void readCommandsFromFile(String fileName, DatabaseAPI databaseAPI, OUTPUT_TYPE outputType, Optional<String> filePath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            StringBuilder commands = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if(!line.startsWith("//")){
                    commands.append(line).append('\n');
                }
            }

            CharStream stream = CharStreams.fromString(commands.toString());
            SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
            SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));

            parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
            lexer.removeErrorListeners();
            parser.addParseListener(new SQLListener(databaseAPI,outputType,filePath));
            parser.start();

        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public static void readCommandsFromCommandLine(DatabaseAPI databaseAPI, OUTPUT_TYPE outputType, Optional<String> filePath) {
        System.out.println("Choose output mode: Console (C) or File (F)");
        String outputMode = in.next();
        if (outputMode.equalsIgnoreCase("C")) {
            System.out.println("Outputting to console:");
            readCommandsFromCommandLine(databaseAPI, outputType, filePath);
        } else if (outputMode.equalsIgnoreCase("F")) {
            System.out.println("Enter the file name:");
            String fileName = in.next();
            readCommandsFromCommandLine(databaseAPI, outputType, filePath);
        } else {
            System.out.println("Invalid choice.");
        }

        StringBuilder commands = new StringBuilder();
        String line = in.next();
        while(!line.equals(":q")){
            commands.append(line).append(" ");
            line = in.next();
        }

        CharStream stream = CharStreams.fromString(commands.toString());
        //CharStream stream = CharStreams.fromStream(System.in);
        SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
        SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        lexer.removeErrorListeners();
        parser.addParseListener(new SQLListener(databaseAPI,outputType,filePath));

        try{
            parser.start();
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public static void handleCommandLine(DatabaseAPI databaseAPI) {
        System.out.println("You chose Command Line Interface.");
        System.out.println("Enter a command, or type ':q' to quit:");
        readCommandsFromCommandLine(databaseAPI);
    }

    public static void handleFileSystem(DatabaseAPI databaseAPI) {
        System.out.println("You chose File System.");
        System.out.println("Choose output mode: Console (C) or File (F)");
        String outputMode = in.next();

        if (outputMode.equalsIgnoreCase("C")) {
            System.out.println("Outputting to console:");
            readCommandsFromFile(databaseAPI);
        } else if (outputMode.equalsIgnoreCase("F")) {
            System.out.println("Enter the file name:");
            String fileName = in.next();
            try {
                readCommandsFromFile(fileName, databaseAPI);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public static void displayHelpCommands(Config config) {
        System.out.println("Choose the working mode: Command Line Interface (CLI) or File System (FS), or type HELP to check our cool commands");
    }

    public static void main(String[] args) {
        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.setActiveEditor(new DatabaseEditor());
        databaseAPI.setHistory(new CommandHistory());
        Config config = new Config();
        System.out.println("Greetings traveler!");
        System.out.println("Choose the working mode: Command Line Interface (CLI) or File System (FS), or type HELP to check our cool commands");
        boolean mainFlag = true;
        while (mainFlag) {
            String input = in.nextLine();
            if (input.equalsIgnoreCase("CLI")) {
                handleCommandLine(databaseAPI);
                break;
            } else if (input.equalsIgnoreCase("FS")) {
                handleFileSystem(databaseAPI);
                break;
            } else if (input.equalsIgnoreCase("HELP")) {
                displayHelpCommands(config);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}