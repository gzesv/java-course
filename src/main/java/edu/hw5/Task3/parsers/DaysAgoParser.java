package edu.hw5.Task3.parsers;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaysAgoParser implements Parser {

    public boolean tryParse(String date) {
        return parse(date) != null;
    }

    @Override
    public LocalDate parse(String date) {
        Pattern pattern = Pattern.compile("^(\\d*) day[s]? ago$");

        Matcher matcher = pattern.matcher(date.toLowerCase());

        if (matcher.find()) {
            return LocalDate.now().minusDays(Integer.parseInt(matcher.group(1)));
        }

        return null;
    }
}
