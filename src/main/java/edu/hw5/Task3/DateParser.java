package edu.hw5.Task3;

import edu.hw5.Task3.parsers.DaysAgoParser;
import edu.hw5.Task3.parsers.FormatParser;
import edu.hw5.Task3.parsers.WordParser;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("HideUtilityClassConstructor")
public class DateParser {
    public static Optional<LocalDate> parseDate(String date) {

        return Stream.of(
                new FormatParser(),
                new WordParser(),
                new DaysAgoParser()
            )
            .filter(item -> item.tryParse(date))
            .map(item -> item.parse(date))
            .findFirst();
    }
}
