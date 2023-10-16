package edu.hw1;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task1 {
    private static final int SECOND_IN_MINUTE = 60;

    public static int minutesToSeconds(@NotNull String time) {
        String[] minutesAndSeconds = time.split(":");
        if (minutesAndSeconds.length != 2) {
            return -1;
        }

        int minutes;
        int seconds;
        try {
            minutes = Integer.parseInt(minutesAndSeconds[0]);
            seconds = Integer.parseInt(minutesAndSeconds[1]);
        } catch (Exception e) {
            return -1;
        }

        if (seconds >= SECOND_IN_MINUTE || seconds < 0 || minutes < 0) {
            return -1;
        }

        return minutes * SECOND_IN_MINUTE + seconds;
    }
}
