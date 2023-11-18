package edu.hw5.Task3.parsers;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaysAgoParser implements Parser {
    private final static Pattern PATTERN = Pattern.compile("^(\\d*) day[s]? ago$");

    public boolean tryParse(String date) {
        return parse(date) != null;
    }

    @Override
    public LocalDate parse(String date) {
        Matcher matcher = PATTERN.matcher(date.toLowerCase());

        if (matcher.find()) {
            return LocalDate.now().minusDays(Integer.parseInt(matcher.group(1)));
        }

        return null;
    }
}
