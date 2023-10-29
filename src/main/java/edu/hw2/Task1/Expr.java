package edu.hw2.Task1;

public interface Expr {
    double evaluate();

    public record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }
    }

    public record Negate(Expr value) implements Expr {
        @Override
        public double evaluate() {
            return -value.evaluate();
        }
    }

    public record Exponent(Expr value, double power) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(value.evaluate(), power);
        }
    }

    public record Addition(Expr leftValue, Expr rightValue) implements Expr {
        @Override
        public double evaluate() {
            return leftValue.evaluate() + rightValue.evaluate();
        }
    }

    public record Multiplication(Expr leftValue, Expr rightValue) implements Expr {
        @Override
        public double evaluate() {
            return leftValue.evaluate() * rightValue.evaluate();
        }
    }
}
