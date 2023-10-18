package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {
    @Test
    @DisplayName("Длинна строки четная")
    void test1() {
        String brokenLine = "123456";

        String fixedLine = Task4.fixString(brokenLine);

        assertThat(fixedLine).isEqualTo("214365");
    }

    @Test
    @DisplayName("Длинна строки нечетная")
    void test2() {
        String brokenLine = "badce";

        String fixedLine = Task4.fixString(brokenLine);

        assertThat(fixedLine).isEqualTo("abcde");
    }

    @Test
    @DisplayName("Длинна строки равна 1")
    void test3() {
        String brokenLine = "a";

        String fixedLine = Task4.fixString(brokenLine);

        assertThat(fixedLine).isEqualTo("a");
    }

    @Test
    @DisplayName("Пустая строка")
    void test4() {
        String brokenLine = "";

        String fixedLine = Task4.fixString(brokenLine);

        assertThat(fixedLine).isEqualTo("");
    }
}
