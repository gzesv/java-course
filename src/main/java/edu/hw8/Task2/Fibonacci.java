package edu.hw8.Task2;

@SuppressWarnings("HideUtilityClassConstructor")
public class Fibonacci {
    public static int getFibonacci(int element) {
        if (element <= 1) {
            return element;
        }

        int first = 0;
        int second = 1;
        for (int i = 2; i <= element; i++) {
            int summary = first + second;
            first = second;
            second = summary;
        }
        return second;
    }
}
