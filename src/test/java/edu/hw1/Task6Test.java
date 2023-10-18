package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task6Test {
    @DisplayName("Постоянная Капрекара, ")
    @ParameterizedTest
    @CsvSource({
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "3524, 3",
    })
    void test1(int number, int expected) {
        int count = Task6.countK(number);

        assertThat(count).isEqualTo(expected);
    }

    @DisplayName("Исходное число не четырехзначное")
    @ParameterizedTest
    @CsvSource({
        "999, -1",
        "10000, -1"
    })
    void test2(int number, int expected) {
        int count = Task6.countK(number);

        assertThat(count).isEqualTo(expected);
    }

    @Test
    @DisplayName("Исходное число 6174")
    void test2() {
        int number  = 6174;

        int count = Task6.countK(number);

        assertThat(count).isEqualTo(0);
    }
}
