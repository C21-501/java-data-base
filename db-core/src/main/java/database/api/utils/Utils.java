package database.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class providing methods for parsing a string into an array of objects.
 */
public class Utils {
    private Utils(){} // Private constructor to prevent instantiation of the class

    /**
     * Parses the input string into an array of objects.
     * <p>
     * This method splits the input string by single quotes and then processes each part individually,
     * splitting it further by comma and trimming leading and trailing whitespace.
     * Numeric values are parsed as integers or doubles, boolean values are parsed as booleans,
     * and the string "null" is parsed as null.
     *
     * @param input The input string to be parsed.
     * @return An array of objects parsed from the input string.
     */
    public static Object[] parseStringToObjectArray(String input) {
        List<Object> result = new ArrayList<>();
        String regex = "'[^']*'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int lastIndex = 0;
        while (matcher.find()) {
            String substring = input.substring(lastIndex, matcher.start()).trim();
            if (!substring.isEmpty()) {
                for (String subpart : substring.split(",")) {
                    subpart = subpart.trim();
                    if (!subpart.isEmpty()) {
                        result.add(parseValue(subpart));
                    }
                }
            }
            result.add(matcher.group().trim());
            lastIndex = matcher.end();
        }
        String remainingSubstring = input.substring(lastIndex).trim();
        if (!remainingSubstring.isEmpty()) {
            for (String subpart : remainingSubstring.split(",")) {
                subpart = subpart.trim();
                if (!subpart.isEmpty()) {
                    result.add(parseValue(subpart));
                }
            }
        }
        return result.toArray();
    }

    /**
     * Parses a string value into its corresponding object type.
     *
     * @param value The string value to be parsed.
     * @return The parsed object (Integer, Double, Boolean, or null).
     */
    public static Object parseValue(String value) {
        if (value.matches("\\d+\\.\\d+")) {
            return Double.parseDouble(value);
        } else if (value.matches("\\d+")) {
            return Integer.parseInt(value);
        } else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(value);
        } else if (value.equalsIgnoreCase("null")) {
            return null;
        } else {
            return value;
        }
    }

    public static String camelCaseToSnakeCase(String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                if (!result.isEmpty()) {
                    result.append('_');
                }
                result.append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
