package edu.hw5.Task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"HideUtilityClassConstructor", "MagicNumber"})
public class Friday13 {

    public static List<LocalDate> findAllFriday13InYear(int year) {
        List<LocalDate> fridays = new ArrayList<>();

        for (int i = 1; i <= Month.values().length; i++) {
            LocalDate day = LocalDate.of(year, i, 13);
            if (day.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                fridays.add(day);
            }
        }

        return fridays;
    }

    public static LocalDate findNextFriday13(LocalDate current) {
        TemporalAdjuster nextFriday = TemporalAdjusters.next(DayOfWeek.FRIDAY);

        LocalDate next = current.with(nextFriday);
        while (next.getDayOfMonth() != 13) {
            next = next.with(nextFriday);
        }

        return next;
    }

}
