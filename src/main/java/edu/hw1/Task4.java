package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4 {
    public static String fixString(String brokenLine) {
        StringBuilder fixedLine = new StringBuilder();
        for (int i = 0; i < brokenLine.length() - 1; i += 2) {
            fixedLine.append(brokenLine.charAt(i + 1));
            fixedLine.append(brokenLine.charAt(i));
        }

        if (brokenLine.length() % 2 == 1) {
            fixedLine.append(brokenLine.charAt(brokenLine.length() - 1));
        }

        return fixedLine.toString();
    }
}
