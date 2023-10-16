package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {

    @Test
    @DisplayName("Корректные входные данные, time = 01:00")
    void test1() {
        String time = "01:00";

        int seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(60);
    }

    @Test
    @DisplayName("Корректные входные данные, time = 13:56")
    void test2() {
        String time = "13:56";

        int seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(836);
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
