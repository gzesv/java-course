package edu.hw2.Task2;

public class Rectangle {
    private final double width;
    private final double height;

    public Rectangle() {
        this(0.0, 0.0);
    }

    public Rectangle(double width, double height) {
        this.height = height;
        this.width = width;
    }

    public Rectangle setWidth(double width) {
        return new Rectangle(width, height);
    }

    public Rectangle setHeight(double height) {
        return new Rectangle(width, height);
    }

    public double getArea() {
        return width * height;
    }
}
