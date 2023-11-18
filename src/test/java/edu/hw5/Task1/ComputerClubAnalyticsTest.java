package edu.hw5.Task1;

import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task1.ComputerClubAnalytics.calculateAverageDuration;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ComputerClubAnalyticsTest {

    @Test
    @DisplayName("Среднее время за 1 сеанс")
    void calculateAverageDuration_test() {
        String[] sessions = {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        };
        Duration expected = Duration.ofHours(3).plusMinutes(40);

        Duration result = calculateAverageDuration(sessions);

        assertThat(result).isEqualTo(expected);
    }
}
