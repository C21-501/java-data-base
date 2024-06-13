package database.system.core.constraints;

import database.system.core.constraints.interfaces.Constraint;
import database.system.core.constraints.listeners.CheckConstraint;
import database.system.core.constraints.listeners.NotNullConstraint;
import database.system.core.constraints.listeners.PrimaryKeyConstraint;
import database.system.core.constraints.listeners.UniqueConstraint;
import database.system.core.structures.Column;
import database.system.core.types.DataType;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Predicate;

@Data
public class ConstraintFactory {
    private String columnName;
    private String constraintName;
    private Column column;

    public ConstraintFactory(String columnName, Column column) {
        this.columnName = columnName;
        this.column = column;
    }

    public ConstraintFactory(String columnName, String constraintName) {
        this.columnName = columnName;
        this.constraintName = constraintName;
    }

    public Constraint createConstraint(String definition) {
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
            Predicate<Object> predicate = createCheckPredicate(columnName, condition);
            return new CheckConstraint(columnName, predicate);
        }

        throw new IllegalArgumentException(String.format("Error: Unsupported constraint '%s'", definition));
    }

    public static @NotNull Predicate<Object> createCheckPredicate(String columnName, String condition) {
        condition = condition.trim();
        // Split the condition into parts based on comparison operators and values
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
        // Escape special regex characters and convert SQL LIKE pattern to regex pattern
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
        // Evaluate the value against the parsedRightOperand based on operator
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
            // Directly compare boolean values
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


    public static void main(String[] args) {
        // Пример создания фабрики с указанием имени столбца и типа данных
        Column column = new Column(DataType.INTEGER);
        ConstraintFactory factory = new ConstraintFactory("col1", column);

        // Создание ограничения NOT NULL для столбца col1
        Constraint constraint1 = factory.createConstraint("NOT NULL");
        System.out.printf("Constraint 1: %s%n", constraint1.getClass().getSimpleName());

        // Создание ограничения PRIMARY KEY для столбца col1
        Constraint constraint2 = factory.createConstraint("PRIMARY KEY");
        System.out.printf("Constraint 2: %s%n", constraint2.getClass().getSimpleName());

        // Создание ограничения UNIQUE для столбца col1
        Constraint constraint3 = factory.createConstraint("UNIQUE");
        System.out.printf("Constraint 3: %s%n", constraint3.getClass().getSimpleName());

        // Создание ограничения CHECK для столбца col1 (числовое условие)
        // Условие: значение в столбце col1 должно быть больше 1
        Constraint constraint4 = factory.createConstraint("CHECK (col1 > 1)");
        System.out.printf("Constraint 4: %s%n", constraint4.getClass().getSimpleName());

        // Создание ограничения CHECK для столбца col1 (строковое условие)
        // Условие: значение в столбце col1 должно быть равно 'abc'
        Constraint constraint5 = factory.createConstraint("CHECK (col1 = 'abc')");
        System.out.printf("Constraint 5: %s%n", constraint5.getClass().getSimpleName());

        // Создание ограничения CHECK для столбца col1 (логическое условие)
        // Условие: значение в столбце col1 должно быть равно true
        Constraint constraint6 = factory.createConstraint("CHECK (col1 = true)");
        System.out.printf("Constraint 6: %s%n", constraint6.getClass().getSimpleName());

        // Демонстрация работы созданных CHECK ограничений
        Predicate<Object> checkPredicate1 = ConstraintFactory.createCheckPredicate("col1", "col1 > 1");
        System.out.printf("Check Predicate result for col1 = 2: %s%n", checkPredicate1.test(2)); // true
        System.out.printf("Check Predicate result for col1 = 1: %s%n", checkPredicate1.test(1)); // false

        Predicate<Object> checkPredicate2 = ConstraintFactory.createCheckPredicate("col1", "col1 = 'abc'");
        System.out.printf("Check Predicate result for col1 = 'abc': %s%n", checkPredicate2.test("'abc'")); // true
        System.out.printf("Check Predicate result for col1 = 'xyz': %s%n", checkPredicate2.test("'xyz'")); // false

        Predicate<Object> checkPredicate3 = ConstraintFactory.createCheckPredicate("col1", "col1 = true");
        System.out.printf("Check Predicate result for col1 = true: %s%n", checkPredicate3.test(true)); // true
        System.out.printf("Check Predicate result for col1 = false: %s%n", checkPredicate3.test(false)); // false

        Predicate<Object> checkPredicate4 = ConstraintFactory.createCheckPredicate("col1", "col1 IS NULL");
        System.out.printf("Check Predicate result for col1 IS NULL (null value): %s%n", checkPredicate4.test(null)); // true
        System.out.printf("Check Predicate result for col1 IS NULL (non-null value): %s%n", checkPredicate4.test(1)); // false
    }
}
