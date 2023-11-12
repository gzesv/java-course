package edu.hw5.Task3.parsers;

import java.time.LocalDate;

public class WordParser implements Parser {
    private final static String YESTERDAY = "yesterday";
    private final static String TODAY = "today";
    private final static String TOMORROW = "tomorrow";

    public boolean tryParse(String date) {
        return parse(date) != null;
    }

    @Override
    public LocalDate parse(String date) {
        return switch (date) {
            case YESTERDAY -> getYesterday();
            case TODAY -> getToday();
            case TOMORROW -> getTomorrow();
            default -> null;
        };
    }

    private LocalDate getYesterday() {
        return LocalDate.now().minusDays(1);
    }

    private LocalDate getToday() {
        return LocalDate.now();
    }

    private LocalDate getTomorrow() {
        return LocalDate.now().plusDays(1);
    }
}
