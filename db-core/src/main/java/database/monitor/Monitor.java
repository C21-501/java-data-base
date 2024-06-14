package database.monitor;
import database.monitor.Config;
import database.api.CommandHistory;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import database.service.tools.SQLListener;
import database.service.tools.grammar.SQLGrammarLexer;
import database.service.tools.grammar.SQLGrammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import database.service.tools.Scanner;

import java.io.IOException;

public class Monitor {
    private static final java.util.Scanner in = new java.util.Scanner(System.in);
    /**
     * Reads SQL commands from a file and parses them.
     *
     * @param fileName the name of the file containing SQL commands.
     * @throws IOException if an I/O error occurs when reading the file.
     */
    public static void readCommandsFromFile(String fileName, DatabaseAPI databaseAPI) throws IOException {
        CharStream stream = CharStreams.fromFileName(fileName);
        SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
        SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));
        parser.addParseListener(new SQLListener(databaseAPI));

        try{
            parser.start();
        } catch (Exception ignored){

        }
    }

    /**
     * Reads SQL commands from the command line until the user enters ":q".
     * Parses the accumulated commands.
     */
    public static void readCommandsFromCommandLine(DatabaseAPI databaseAPI) {
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
        parser.addParseListener(new SQLListener(databaseAPI));

        try{
            parser.start();
        } catch (Exception ignored){

        }
    }

//    public static void main(String[] args) {
//        DatabaseAPI databaseAPI;
//        databaseAPI = new DatabaseAPI();
//        databaseAPI.setActiveEditor(new DatabaseEditor());
//        databaseAPI.setHistory(new CommandHistory());
//        final java.util.Scanner scanner = new java.util.Scanner(System.in);
//        Config config = new Config();
//        System.out.println("Greetings traveler!");
//        System.out.println("Choose the working mode: Command Line Interface (CLI) or File System (FS), or type HELP to check our cool commands");
//        boolean flag = true;
//        boolean mainFlag = true;
//        while (mainFlag) {
//            String input = scanner.nextLine();
//            if (input.equalsIgnoreCase("CLI")) {
//                System.out.println("You chose Command Line Interface.");
//                while (flag) {
//                    System.out.println("Enter a command, or type ':q' to quit:");
//                    readCommandsFromCommandLine(databaseAPI);
//                    break;
//                }
//                break;
//            } else if (input.equalsIgnoreCase("FS")) {
//                System.out.println("You chose File System.");
//                System.out.println("Enter the file name:");
//                String fileName = in.next();
//                try {
//                    readCommandsFromFile(fileName, databaseAPI);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                break;
//            } else if (input.equalsIgnoreCase("HELP")) {
//                for (String command  {
//                    System.out.println(command);
//                }
//                System.out.println("Choose the working mode: Command Line Interface (CLI) or File System (FS), or type HELP to check our cool commands");
//            } else {
//                System.out.println("Invalid choice.");
//            }
//        }
//
//    }

    /*private static void runCommandInThread(String command) {
        Thread commandThread = new Thread(() -> {
            System.out.println("Running command: " + command);
        });
        commandThread.start();
        try {
            commandThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}