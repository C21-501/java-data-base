package database.service.tools;

import database.api.CommandHistory;
import database.api.DatabaseAPI;
import database.api.DatabaseEditor;
import database.api.utils.OUTPUT_TYPE;
import database.service.tools.grammar.SQLGrammarLexer;
import database.service.tools.grammar.SQLGrammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

/**
 * The Scanner class provides methods to read and parse SQL commands from a file or from the command line.
 * It utilizes ANTLR for lexical and syntactic analysis of SQL commands.
 */
public class Scanner {

    private static final java.util.Scanner in = new java.util.Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Scanner.class);

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

        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);

        parser.addParseListener(new SQLListener(databaseAPI, OUTPUT_TYPE.FILE, Optional.of("result.txt")));

        try{
            parser.start();
        } catch (Exception e){
            logger.error(e.getMessage());
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

        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);

        parser.addParseListener(new SQLListener(databaseAPI, OUTPUT_TYPE.FILE, Optional.of("result.txt")));

        try{
            parser.start();
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        DatabaseAPI databaseAPI;
        databaseAPI = new DatabaseAPI();
        databaseAPI.setActiveEditor(new DatabaseEditor());
        databaseAPI.setHistory(new CommandHistory());
        boolean flag = true;
        while(flag){
            System.out.println("Введите номер:");
            System.out.println("0. Выход");
            System.out.println("1. Ввод из консоли");
            System.out.println("2. Ввод из файла");
            int i = in.nextInt();
            switch (i){
                case 0:
                    flag = false;
                    break;
                case 1:
                    System.out.println("Введите команды, по завершении введите :q");
                    readCommandsFromCommandLine(databaseAPI);
                    break;
                case 2:
                    System.out.println("Введите название файла:");
                    String fileName = in.next();
                    readCommandsFromFile(fileName, databaseAPI);
                    break;
            }
        }
    }
}
