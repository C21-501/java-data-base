package database.service.tools;

import database.service.tools.grammar.SQLGrammarLexer;
import database.service.tools.grammar.SQLGrammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scanner {
    public static void readCommandsFromFile(String fileName) throws IOException {
        CharStream stream = CharStreams.fromFileName(fileName);
        SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
        SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));
        parser.addParseListener(new SQLListener());
        parser.start();
    }

    public static void readCommandsFromCommandLine() throws IOException {
        CharStream stream = CharStreams.fromStream(System.in);
        SQLGrammarLexer lexer = new SQLGrammarLexer(stream);
        SQLGrammarParser parser = new SQLGrammarParser(new CommonTokenStream(lexer));
        parser.addParseListener(new SQLListener());
        parser.start();
    }

    public static void main(String[] args) throws IOException {
        java.util.Scanner in = new java.util.Scanner(System.in);
        readCommandsFromCommandLine();
        int x = in.nextInt();
        System.out.println(x);
    }
}
