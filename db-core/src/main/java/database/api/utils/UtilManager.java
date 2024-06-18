package database.api.utils;

import database.monitor.Config;
import database.system.core.structures.Database;
import lombok.Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

@Data
public class UtilManager {
    public static final String X = "=================================";
    private final Document document;
    private Database database;
    private String databasePath = Config.ROOT_DATABASE_PATH;  // Using default path from Config

    public UtilManager(Database database) {
        this.database = database;
        this.document = loadXMLDocument();
    }

    private Document loadXMLDocument() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Config.HELP_FILE_PATH)) {
            if (inputStream == null) {
                throw new RuntimeException(String.format("File not found: %s", Config.HELP_FILE_PATH));
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load XML file", e);
        }
    }

    public void printCommandHelp(String command, OUTPUT_TYPE outputType, String filePath) {
        PrintStream out = getOutputStream(outputType, filePath);

        NodeList commands = document.getElementsByTagName("command");
        boolean commandFound = false;
        for (int i = 0; i < commands.getLength(); i++) {
            Node node = commands.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = getTextContent(element, "name");
                if (name.equalsIgnoreCase(command)) {
                    String description = getTextContent(element, "description");
                    String example = getTextContent(element, "example");

                    out.printf("========== Command: %s ==========%n", name);
                    out.println("Description:");
                    printFormattedText(out, description);
                    out.println("Example:");
                    printFormattedText(out, example);
                    out.println(X);
                    commandFound = true;
                    break;
                }
            }
        }
        if (!commandFound) {
            out.printf("Command '%s' not found.%n", command);
        }
        closeOutputStream(out);
    }

    public void printAllCommandsHelp(OUTPUT_TYPE outputType, String filePath) {
        PrintStream out = getOutputStream(outputType, filePath);

        NodeList commands = document.getElementsByTagName("command");
        out.println("Available Commands:");
        out.println("===================");
        for (int i = 0; i < commands.getLength(); i++) {
            Node node = commands.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = getTextContent(element, "name");
                String description = getTextContent(element, "description");
                String example = getTextContent(element, "example");

                out.printf("Command: %s%n", name);
                out.println("Description:");
                printFormattedText(out, description);
                out.println("Example:");
                printFormattedText(out, example);
                out.println("--------------------");
            }
        }
        closeOutputStream(out);
    }

    private String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0 && nodeList.item(0) != null) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    private void printFormattedText(PrintStream out, String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        String[] lines = text.split("\n");
        for (String line : lines) {
            out.printf("    %s%n", line);
        }
    }

    private PrintStream getOutputStream(OUTPUT_TYPE outputType, String filePath) {
        if (outputType == OUTPUT_TYPE.FILE && filePath != null && !filePath.isEmpty()) {
            try {
                PrintStream fileOut = new PrintStream(filePath);
                LocalDateTime now = LocalDateTime.now();
                fileOut.printf("Date: %s%n", now.format(Config.DATE_TIME_FORMATTER));
                fileOut.println();
                return fileOut;
            } catch (IOException e) {
                throw new RuntimeException(String.format("Error opening file: %s", filePath), e);
            }
        } else {
            return System.out;
        }
    }

    private void closeOutputStream(PrintStream out) {
        if (out != System.out) {
            out.close();
        }
    }

    public void showAvailableDatabases(String databasePath, OUTPUT_TYPE outputType, String filePath) {
        PrintStream out = getOutputStream(outputType, filePath);

        Path path = Paths.get(databasePath);
        if (!Files.exists(path)) {
            throw new RuntimeException(String.format("Path %s does not exist.%n", databasePath));
        }

        try (Stream<Path> paths = Files.walk(path, 1)) {
            out.printf("%nAvailable Databases:%n");
            out.println(X);
            paths.filter(Files::isDirectory)
                    .filter(p -> !p.equals(path))
                    .forEach(p -> out.printf("\t%s%n", p.getFileName().toString()));
            out.printf("%s%n%n", X);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error while listing databases: %s%n", e.getMessage()));
        }

        closeOutputStream(out);
    }

    public void showAvailableTables(OUTPUT_TYPE outputType, String filePath) {
        PrintStream out = getOutputStream(outputType, filePath);

        out.printf("%nAvailable Tables:%n");
        out.printf("%s%n", X);
        if (Objects.isNull(database))
            out.println("    No tables available.");
        else if (database.getTables().isEmpty()) {
            out.println("    No tables available.");
        } else {
            database.getTables()
                    .keySet()
                    .forEach(table -> out.printf("    %s%n", table));
        }
        out.printf("%s%n%n", X);
        closeOutputStream(out);
    }
}
