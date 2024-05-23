package database.service.tools;

import database.service.tools.grammar.SQLGrammarLexer;
import database.service.tools.grammar.SQLGrammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Scanner {

    private static final java.util.Scanner in = new java.util.Scanner(System.in);

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
