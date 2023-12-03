package edu.hw8.Task2;

@SuppressWarnings("HideUtilityClassConstructor")
public class Fibonacci {
    public static int getFibonacci(int element) {
        if (element == 1 || element == 0) {
            return element;
        }
        return getFibonacci(element - 1) + getFibonacci(element - 2);
    }
}
