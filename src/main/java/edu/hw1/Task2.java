package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {
    @SuppressWarnings("MagicNumber")
    public static int countDigits(int inputNumber) {
        int number = inputNumber;

        if (number == 0) {
            return 1;
        }

        int count = 0;

        while (number != 0) {
            number /= 10;
            count++;
        }

        return count;
    }
}
