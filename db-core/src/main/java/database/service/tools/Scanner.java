package database.service.tools;

import database.service.tools.grammar.SQLGrammarLexer;
import database.service.tools.grammar.SQLGrammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

/**
 * The Scanner class provides methods to read and parse SQL commands from a file or from the command line.
 * It utilizes ANTLR for lexical and syntactic analysis of SQL commands.
 */
public class Scanner {

    private static final java.util.Scanner in = new java.util.Scanner(System.in);

    /**
     * Reads SQL commands from a file and parses them.
     *
     * @param fileName the name of the file containing SQL commands.
     * @throws IOException if an I/O error occurs when reading the file.
     */
    public static void readCommandsFromFile(String fileName) throws IOException {
        CharStream stream = CharStreams.fromFileName(fileName);
        SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
        SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));
        parser.addParseListener(new SQLListener());

        try{
            parser.start();
        } catch (Exception ignored){

        }
    }

    /**
     * Reads SQL commands from the command line until the user enters ":q".
     * Parses the accumulated commands.
     */
    public static void readCommandsFromCommandLine() {
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
        parser.addParseListener(new SQLListener());

        try{
            parser.start();
        } catch (Exception ignored){

        }
    }

    public static void main(String[] args) throws IOException {
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
                    readCommandsFromCommandLine();
                    break;
                case 2:
                    System.out.println("Введите название файла:");
                    String fileName = in.next();
                    readCommandsFromFile(fileName);
                    break;
            }
        }
    }
}
