package edu.hw5.Task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@SuppressWarnings("HideUtilityClassConstructor")
public class ComputerClubAnalytics {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    public static Duration calculateAverageDuration(String[] sessions) {
        return Stream.of(sessions)
            .map(session -> session.split(" - "))
            .map(session -> new LocalDateTime[] {
                LocalDateTime.parse(session[0], FORMATTER),
                LocalDateTime.parse(session[1], FORMATTER)}
            )
            .map(session -> Duration.between(session[0], session[1]))
            .reduce(Duration.ZERO, Duration::plus)
            .dividedBy(sessions.length);
    }
}
