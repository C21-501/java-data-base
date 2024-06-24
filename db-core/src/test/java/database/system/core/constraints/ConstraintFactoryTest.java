package database.system.core.constraints;


class ConstraintFactoryTest {
    // Create NotNullConstraint from definition "NOT NULL"
//    @Test
//    public void test_create_not_null_constraint() {
//        Constraint constraint = ConstraintFactory.createConstraint("NOT NULL");
//        assertInstanceOf(NotNullConstraint.class, constraint);
//    }
//
//    // Create PrimaryKeyConstraint from definition "PRIMARY KEY"
//    @Test
//    public void test_create_primary_key_constraint() {
//        Constraint constraint = ConstraintFactory.createConstraint("PRIMARY KEY");
//        assertInstanceOf(PrimaryKeyConstraint.class, constraint);
//    }
//
//    // Create UniqueConstraint from definition "UNIQUE"
//    @Test
//    public void test_create_unique_constraint() {
//        Constraint constraint = ConstraintFactory.createConstraint("UNIQUE");
//        assertInstanceOf(UniqueConstraint.class, constraint);
//    }
//
//    // Create CheckConstraint with valid predicate from definition "CHECK (condition)"
//    @Test
//    public void test_create_serve_constraint_with_valid_predicate() {
//        Constraint constraint = ConstraintFactory.createConstraint("CHECK (value > 10)");
//        assertInstanceOf(CheckConstraint.class, constraint);
//    }

    // CheckConstraint correctly evaluates numeric conditions
//    @Test
//    public void test_check_constraint_numeric_conditions() {
//        Constraint constraint = ConstraintFactory.createConstraint("CHECK (value > 10)");
//        Body body = new FieldBody();
//        assertTrue(constraint.check(body, 15));
//        assertThrows(RuntimeException.class, () -> constraint.check(body, 5));
//    }

    // CheckConstraint correctly evaluates string conditions with LIKE operator
//    @Test
//    public void test_check_constraint_string_conditions_like_operator() {
//        Constraint constraint = ConstraintFactory.createConstraint("CHECK (value LIKE 'test%')");
//        Body body = new FieldBody();
//        assertTrue(constraint.check(body, "test123"));
//        assertThrows(RuntimeException.class, () -> constraint.check(body, "example"));
//    }

    // Invalid CHECK constraint condition format
//    @Test
//    public void test_invalid_check_constraint_condition_format() {
//        assertThrows(IllegalArgumentException.class, () -> ConstraintFactory.createConstraint("CHECK value > 10"));
//    }

    // Unsupported operator in CHECK constraint condition
//    @Test
//    public void test_unsupported_operator_in_serve_constraint_condition() {
//        assertThrows(IllegalArgumentException.class, () -> ConstraintFactory.createConstraint("CHECK (value != 10)"));
//    }
//
//    // Null definition input for createConstraint method
//    @Test
//    public void test_null_definition_input_for_create_constraint() {
//        assertThrows(NullPointerException.class, () -> ConstraintFactory.createConstraint(null));
//    }
//
//    // Empty string definition input for createConstraint method
//    @Test
//    public void test_empty_string_definition_input_for_create_constraint() {
//        assertThrows(IllegalArgumentException.class, () -> ConstraintFactory.createConstraint(""));
//    }
//
//    // Invalid constraint definition string
//    @Test
//    public void test_invalid_constraint_definition_string() {
//        assertThrows(IllegalArgumentException.class, () -> ConstraintFactory.createConstraint("INVALID CONSTRAINT"));
//    }

    // CheckConstraint correctly evaluates string conditions with equality operator
//    @Test
//    public void test_check_constraint_string_conditions_equality_operator() {
//        Constraint constraint = ConstraintFactory.createConstraint("CHECK (value = 'test')");
//        Body body = new FieldBody();
//        assertTrue(constraint.check(body, "test"));
//        assertThrows(RuntimeException.class, () -> constraint.check(body, "example"));
//    }
}