package database.system.core.constraints;

import database.system.core.structures.Column;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class ConstraintFactory implements Serializable {
    public static Constraint createConstraint(Column column, String columnName, String definition) {
        if (definition.equalsIgnoreCase("NOT NULL")) {
            return new NotNullConstraint(columnName, column);
        }

        if (definition.equalsIgnoreCase("PRIMARY KEY")) {
            return new PrimaryKeyConstraint(columnName, column);
        }

        if (definition.equalsIgnoreCase("UNIQUE")) {
            return new UniqueConstraint(columnName, column);
        }

        if (definition.toUpperCase().startsWith("CHECK")) {
            String condition = definition.substring(definition.indexOf("(") + 1, definition.lastIndexOf(")"));
            SerializablePredicate<Object> predicate = createCheckPredicate(columnName, condition);
            return new CheckConstraint(columnName, predicate);
        }

        if (definition.toUpperCase().startsWith("DEFAULT")) {
            String defaultValue = definition.substring(definition.indexOf(" ") + 1);
            Object parsedDefaultValue = parseOperand(defaultValue);
            return new DefaultConstraint(columnName, column, parsedDefaultValue);
        }

        throw new IllegalArgumentException(String.format("Error: Unsupported constraint '%s'", definition));
    }

    public static @NotNull SerializablePredicate<Object> createCheckPredicate(String columnName, String condition) {
        condition = condition.trim();
        String[] parts = condition.split("\\s+", 3);
        if (parts.length != 3) {
            throw new IllegalArgumentException(String.format("Error: Invalid CHECK constraint condition '%s'", condition));
        }
        if (!columnName.equals(parts[0])) {
            throw new IllegalArgumentException(String.format("Error: Invalid column name '%s' in constraint.", parts[0]));
        }
        String operator = parts[1];
        if (operator.equalsIgnoreCase("IS")) {
            if (parts[2].equalsIgnoreCase("NULL")) {
                return Objects::isNull;
            } else {
                throw new IllegalArgumentException(String.format("Error: Unsupported operator 'IS' with operand '%s' in condition '%s'", parts[2], condition));
            }
        }
        String rightOperand = parts[2];
        Object parsedRightOperand = parseOperand(rightOperand);
        return switch (operator.toUpperCase()) {
            case ">=" -> value -> compareValues(value, operator, parsedRightOperand) >= 0;
            case "<=" -> value -> compareValues(value, operator, parsedRightOperand) <= 0;
            case ">"  -> value -> compareValues(value, operator, parsedRightOperand) > 0;
            case "<"  -> value -> compareValues(value, operator, parsedRightOperand) < 0;
            case "="  -> value -> compareValues(value, operator, parsedRightOperand) == 0;
            case "LIKE" -> {
                String pattern = convertLikePatternToRegex((String) parsedRightOperand);
                yield value -> value instanceof String && ((String) value).matches(pattern);
            }
            default   -> throw new IllegalArgumentException(String.format("Error: Unsupported operator '%s' in condition '%s'", operator, condition));
        };
    }

    private static String convertLikePatternToRegex(String likePattern) {
        String regex = likePattern
                .replace(".", "\\.")
                .replace("%", ".*")
                .replace("_", ".");
        return "^%s$".formatted(regex);
    }

    private static Object parseOperand(String operand) {
        if (operand.equalsIgnoreCase("true") || operand.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(operand);
        }
        try {
            return Integer.parseInt(operand);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(operand);
            } catch (NumberFormatException ex) {
                return operand;
            }
        }
    }

    private static int compareValues(Object value, String operator, Object parsedRightOperand) {
        if (value instanceof Number && parsedRightOperand instanceof Number) {
            double val1 = ((Number) value).doubleValue();
            double val2 = ((Number) parsedRightOperand).doubleValue();
            return switch (operator) {
                case ">=", "<=", ">", "<", "=" -> Double.compare(val1, val2);
                default -> throw new IllegalArgumentException(String.format("Error: Unsupported operator '%s' for numeric comparison", operator));
            };
        } else if (value instanceof String && parsedRightOperand instanceof String) {
            return switch (operator.toUpperCase()) {
                case "=" -> ((String) value).compareTo((String) parsedRightOperand) == 0 ? 0 : -1;
                case "LIKE" -> {
                    String pattern = convertLikePatternToRegex((String) parsedRightOperand);
                    yield ((String) value).matches(pattern) ? 0 : -1;
                }
                default -> throw new IllegalArgumentException(String.format("Error: Unsupported operator '%s' for string comparison", operator));
            };
        } else if (value instanceof Boolean && parsedRightOperand instanceof Boolean) {
            if (operator.equalsIgnoreCase("="))
                return value.equals(parsedRightOperand) ? 0 : -1;
            throw new IllegalArgumentException(String.format("Error: Unsupported operator '%s' for boolean comparison", operator));
        } else if (value == null && parsedRightOperand == null) {
            return 0;
        } else {
            assert value != null;
            throw new IllegalArgumentException(String.format("Error: Incompatible types for comparison between '%s' and '%s'", value.getClass().getSimpleName(), parsedRightOperand.getClass().getSimpleName()));
        }
    }
}
