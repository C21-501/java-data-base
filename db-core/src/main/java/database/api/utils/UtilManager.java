package database.api.utils;

import database.monitor.Config;
import database.system.core.exceptions.DatabaseIOException;
import database.system.core.serializers.DatabaseSerializer;
import database.system.core.structures.Database;
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
import java.util.stream.Stream;

public class UtilManager {
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
        for (int i = 0; i < commands.getLength(); i++) {
            Node node = commands.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = getTextContent(element, "name");
                if (name.equalsIgnoreCase(command)) {
                    String description = getTextContent(element, "description");
                    String example = getTextContent(element, "example");

                    out.printf("Command: %s%n", name);
                    out.println("Description:");
                    printFormattedText(out, description);
                    out.println("Example:");
                    printFormattedText(out, example);
                    closeOutputStream(out);
                    return;
                }
            }
        }
        out.printf("Command '%s' not found.%n", command);
        closeOutputStream(out);
    }

    public void printAllCommandsHelp(OUTPUT_TYPE outputType, String filePath) {
        PrintStream out = getOutputStream(outputType, filePath);

        NodeList commands = document.getElementsByTagName("command");
        out.println("Available Commands:");
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
                out.println(); // Separate commands with an empty line
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
            out.printf("%s%n", line);
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

    public void openDatabase(String databaseName) {
        openDatabase(databaseName, this.databasePath);
    }

    public void openDatabase(String databaseName, String path) {
        if (databaseName.isEmpty() || path.isEmpty()) {
            throw new IllegalArgumentException("Error while opening database: database name or path is null");
        }
        this.databasePath = path;
        this.database = Database.getInstance();
        this.database.setFilePath(String.format("%s/%s/%s.instance", path, databaseName, databaseName));
        DatabaseSerializer databaseSerializer = DatabaseSerializer.getCompleteSerializerInstance(database, databaseName, path);

        try {
            Database tmpDatabase = databaseSerializer.readInstanceFromFile(path, databaseName);
            if (tmpDatabase == null) {
                throw new RuntimeException("Error while opening database: database instance is null");
            }
            this.database = tmpDatabase;
        } catch (DatabaseIOException e) {
            throw new RuntimeException(String.format("Error while opening database: %s%n", e.getMessage()));
        }
    }

    public void showAvailableDatabases(String databasePath, OUTPUT_TYPE outputType, String filePath) {
        PrintStream out = getOutputStream(outputType, filePath);

        Path path = Paths.get(databasePath);
        if (!Files.exists(path)) {
            throw new RuntimeException(String.format("Path %s does not exist.%n", databasePath));
        }

        try (Stream<Path> paths = Files.walk(path, 1)) {
            paths.filter(Files::isDirectory)
                    .filter(p -> !p.equals(path))
                    .forEach(p -> out.println(p.getFileName().toString()));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error while listing databases: %s%n", e.getMessage()));
        }

        closeOutputStream(out);
    }

    public void showAvailableTables(OUTPUT_TYPE outputType, String filePath) {
        PrintStream out = getOutputStream(outputType, filePath);
        database.getTables().keySet().forEach(out::println);
        closeOutputStream(out);
    }
}
