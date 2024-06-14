package database.api.help;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.Serializable;

public class HELPManager {
    private final Document document;

    public HELPManager(String filePath) {
        this.document = loadXMLDocument(filePath);
    }

    private Document loadXMLDocument(String filePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new RuntimeException("File not found: %s".formatted(filePath));
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load XML file", e);
        }
    }

    public void printCommandHelp(String command) {
        NodeList commands = document.getElementsByTagName("command");
        for (int i = 0; i < commands.getLength(); i++) {
            Node node = commands.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = getTextContent(element, "name");
                if (name.equalsIgnoreCase(command)) {
                    String description = getTextContent(element, "description");
                    String example = getTextContent(element, "example");

                    System.out.printf("Command: %s%n", name);
                    System.out.println("Description:");
                    printFormattedText(description, true);
                    System.out.println("Example:");
                    printFormattedText(example, false);
                    return;
                }
            }
        }
        System.out.printf("Command '%s' not found.%n", command);
    }

    public void printAllCommandsHelp() {
        NodeList commands = document.getElementsByTagName("command");
        System.out.println("Available Commands:");
        for (int i = 0; i < commands.getLength(); i++) {
            Node node = commands.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = getTextContent(element, "name");
                String description = getTextContent(element, "description");
                String example = getTextContent(element, "example");

                System.out.printf("Command: %s%n", name);
                System.out.println("Description:");
                printFormattedText(description, true);
                System.out.println("Example:");
                printFormattedText(example, false);
                System.out.println(); // Separate commands with an empty line
            }
        }
    }

    private String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0 && nodeList.item(0) != null) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    private void printFormattedText(String text, boolean ignoredIndent) {
        if (text == null || text.isEmpty()) {
            return;
        }

        String[] lines = text.split("\n");

        for (String line : lines) {
            System.out.printf("%s%n", line);
        }
    }
}
