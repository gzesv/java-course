package edu.hw2.Task2;

public class Square extends Rectangle {
    public Square() { }

    public Square(double side) {
        super(side, side);
    }

    public Rectangle setSide(double side) {
        return new Square(side);
    }
}
