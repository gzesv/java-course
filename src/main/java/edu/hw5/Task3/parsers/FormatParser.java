package edu.hw5.Task3.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MagicNumber")
public class FormatParser implements Parser {
    private final static String HYPHEN_PATTERN = "^([0-9]{4})-([0-9]{2})-([1-9]|[1-3][0-9])$";
    private final static String SLASH_PATTERN = "^([1-9]|[1-3][0-9])/([1-9]|1[0-2])/([0-9]{4}|[0-9]{2})$";

    public boolean tryParse(String date) {
        return parse(date) != null;
    }

    @Override
    public LocalDate parse(String date) {
        Pattern patternHyphen = Pattern.compile(HYPHEN_PATTERN);

        Matcher matcherHyphen = patternHyphen.matcher(date.toLowerCase());

        if (matcherHyphen.find()) {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("y-M-d"));
        }

        Pattern patternSlash = Pattern.compile(SLASH_PATTERN);

        Matcher matcherSlash = patternSlash.matcher(date.toLowerCase());

        if (matcherSlash.find()) {
            return matcherSlash.group(0).length() > 6
                ? LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/y"))
                : LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yy"));
        }

        return null;
    }

}
