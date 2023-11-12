package edu.hw5.Task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@SuppressWarnings("HideUtilityClassConstructor")
public class ComputerClubAnalytics {

    public static Duration calculateAverageDuration(String[] sessions) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

        return Stream.of(sessions)
            .map(session -> session.split(" - "))
            .map(session -> new LocalDateTime[] {
                LocalDateTime.parse(session[0], formatter),
                LocalDateTime.parse(session[1], formatter)}
            )
            .map(session -> Duration.between(session[0], session[1]))
            .reduce(Duration.ZERO, Duration::plus)
            .dividedBy(sessions.length);
    }
}
