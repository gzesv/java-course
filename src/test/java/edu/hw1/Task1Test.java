package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {

    @DisplayName("Корректные входные данные")
    @ParameterizedTest
    @CsvSource({
        "01:00, 60",
        "13:56, 836",
        "00:01, 1"
    })
    void test1(String time, int expected) {
        int seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(expected);
    }

    @Test
    @DisplayName("Количество секунд = 60")
    void test3() {
        String time = "10:60";

        int seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Два двоеточия")
    void test4() {
        String time = "10:60:10";

        int seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Некорректное время, time = aa:bb")
    void test5() {
        String time = "aa:bb";

        int seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(-1);
    }
}
