package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task6 {
    private final static int KAPREKAR = 6174;
    private final static int LEFT_EDGE = 1000;
    private final static int RIGHT_EDGE = 9999;
    private final static int NUMBERS_SAME = 1111;

    @SuppressWarnings("MagicNumber")
    public static int countK(int number) {
        if (number < LEFT_EDGE || number > RIGHT_EDGE || number % NUMBERS_SAME == 0) {
            return -1;
        }

        return countK(number, 0);
    }

    @SuppressWarnings("MagicNumber")
    private static int countK(int inputNumber, int count) {
        if (inputNumber == KAPREKAR) {
            return count;
        }

        int number = inputNumber;
        List<Integer> digits = new ArrayList<>();
        while (number != 0) {
            digits.add(number % 10);
            number /= 10;
        }

        Collections.sort(digits);
        int descending = getNumber(digits);

        Collections.reverse(digits);
        int ascending = getNumber(digits);

        return countK(Math.abs(ascending - descending), count + 1);
    }

    @SuppressWarnings("MagicNumber")
    private static int getNumber(List<Integer> list) {
        int result = 0;
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            result += (int) (list.get(i) * Math.pow(10, i));
        }

        return result;
    }
}
