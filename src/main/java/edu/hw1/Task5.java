package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task5 {
    @SuppressWarnings("MagicNumber")
    public static boolean isPalindromeDescendant(int inputNumber) {
        String number = String.valueOf(inputNumber);
        while (number.length() > 1) {
            if (isPalindrome(number)) {
                return true;
            }
            number = getDescendant(number);
        }
        return false;
    }

    private static boolean isPalindrome(String number) {
        return number.contentEquals(new StringBuilder(number).reverse());
    }

    @SuppressWarnings("MagicNumber")
    private static String getDescendant(String number) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < number.length() - 1; i += 2) {
            int digit1 = Character.getNumericValue(number.charAt(i));
            int digit2 = Character.getNumericValue(number.charAt(i + 1));
            result.append(digit1 + digit2);
        }

        if (number.length() % 2 == 1) {
            result.append(number.charAt(number.length() - 1));
        }
        return result.toString();
    }
}
