package edu.hw7.Task2;

import java.util.stream.IntStream;

@SuppressWarnings("HideUtilityClassConstructor")
public class Factorial {
    public static int factorial(int number) {
        return IntStream
            .rangeClosed(1, number)
            .parallel()
            .reduce((a, b) -> a * b)
            .orElse(1);
    }
}
