package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {
    @Test
    @DisplayName("Корректное значение, number = 111223")
    void test1() {
        int number = 111223;

        int count = Task2.countDigits(number);

        assertThat(count).isEqualTo(6);
    }

    @Test
    @DisplayName("Корректное значение, number = 0")
    void test2() {
        int number = 0;

        int count = Task2.countDigits(number);

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("Корректное значение, number = -10")
    void test3() {
        int number = -10;

        int count = Task2.countDigits(number);

        assertThat(count).isEqualTo(2);
    }
}
