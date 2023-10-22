package edu.hw2.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExprTest {
    @Test
    @DisplayName("Тест Constant")
    void testConstant() {
        Expr constant = new Expr.Constant(157);

        double result = constant.evaluate();

        assertEquals(157.0, result);
    }

    @Test
    @DisplayName("Тест Negate")
    void testNegate() {
        Expr negate = new Expr.Negate(new Expr.Constant(1991.0));

        double result = negate.evaluate();

        assertEquals(-1991.0, result);
    }

    @Test
    @DisplayName("Test Exponent")
    void testExponent() {
        Expr value = new Expr.Constant(9.0);
        Expr exponent = new Expr.Exponent(value, 2);

        double result = exponent.evaluate();

        assertEquals(81.0, result);
    }

    @Test
    @DisplayName("Test Addition")
    void testAddition() {
        Expr left = new Expr.Constant(11.0);
        Expr right = new Expr.Constant(111.0);
        Expr addition = new Expr.Addition(left, right);

        double result = addition.evaluate();

        assertEquals(122.0, result);
    }

    @Test
    @DisplayName("Test Multiplication")
    void testMultiplication() {
        Expr left = new Expr.Constant(7.0);
        Expr right = new Expr.Constant(11.0);
        Expr multiplication = new Expr.Multiplication(left, right);

        double result = multiplication.evaluate();

        assertEquals(77.0, result);
    }
}
