package edu.hw1;

import java.util.Arrays;
import java.util.OptionalInt;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {
    public static boolean isNestable(int[] array1, int[] array2) {
        if (array1.length == 0 || array2.length == 0) {
            return false;
        }

        OptionalInt min1 = Arrays.stream(array1).min();
        OptionalInt max1 = Arrays.stream(array1).max();
        OptionalInt min2 = Arrays.stream(array2).min();
        OptionalInt max2 = Arrays.stream(array2).max();

        return (min1.getAsInt() > min2.getAsInt()) && (max1.getAsInt() < max2.getAsInt());
    }
}
