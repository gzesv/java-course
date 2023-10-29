package edu.hw3.Task4;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("HideUtilityClassConstructor")
public class RomanNumerals {
    @SuppressWarnings("MagicNumber")
    private static final Map<Integer, String> ROMAN_NUMERALS = new LinkedHashMap<>() {{
        put(1000, "M");
        put(900, "CM");
        put(500, "D");
        put(400, "CD");
        put(100, "C");
        put(90, "XC");
        put(50, "L");
        put(40, "XL");
        put(10, "X");
        put(9, "IX");
        put(5, "V");
        put(4, "IV");
        put(1, "I");
    }};

    private static final String ROMAN_ZERO = "N";

    private static final int MAX_NUMBER = 3999;

    private static final int MIN_NUMBER = 0;

    public static String convertToRoman(int number) {
        if (number > MAX_NUMBER || number < MIN_NUMBER) {
            return null;
        }

        if (number == 0) {
            return ROMAN_ZERO;
        }

        int currentNumber = number;
        StringBuilder romanNumber = new StringBuilder();

        for (Integer elem : ROMAN_NUMERALS.keySet()) {
            int n = currentNumber / elem;
            for (int i = 0; i < n; i++) {
                romanNumber.append(ROMAN_NUMERALS.get(elem));
            }
            currentNumber %= elem;
        }

        return romanNumber.toString();
    }
}
