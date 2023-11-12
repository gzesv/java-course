package edu.hw5.Task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("HideUtilityClassConstructor")
public class SubsequenceRegex {
    public static boolean isSubsequence(String sequence, String subsequence) {
        Pattern pattern = Pattern.compile(subsequence);

        Matcher matcher = pattern.matcher(sequence);

        return matcher.find();
    }
}
