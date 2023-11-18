package edu.hw5.Task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task8UtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "1",
        "101",
        "10000",
        "0000000"
    })
    @DisplayName("Строка нечетной длины. Валидная строка.")
    void task1_valid_test(String input) {
        boolean result = Task8Utils.task1(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "10",
        "0101",
        "100000",
        "00000001"
    })
    @DisplayName("Строка нечетной длины. Не валидная строка.")
    void task1_invalid_test(String input) {
        boolean result = Task8Utils.task1(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0",
        "001",
        "100000",
        "10000001"
    })
    @DisplayName("Начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину. Валидная строка.")
    void task2_valid_test(String input) {
        boolean result = Task8Utils.task2(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1",
        "0011",
        "1000001",
        "00000001"
    })
    @DisplayName("Начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину. Валидная строка.")
    void task2_invalid_test(String input) {
        boolean result = Task8Utils.task2(input);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("количество 0 кратно 3")
    void task3_test() {
        String input = "";

        boolean result = Task8Utils.task3(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "110",
        "001",
        "111111",
        "10"
    })
    @DisplayName("Любая строка, кроме 11 или 111. Валидная строка.")
    void task4_valid_test(String input) {
        boolean result = Task8Utils.task4(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "11",
        "111"
    })
    @DisplayName("Любая строка, кроме 11 или 111. Не валидная строка.")
    void task4_invalid_test(String input) {
        boolean result = Task8Utils.task4(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "11",
        "111",
        "10101",
        "1111101"
    })
    @DisplayName("каждый нечетный символ равен 1. Валидная строка.")
    void task5_valid_test(String input) {
        boolean result = Task8Utils.task5(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "01",
        "110",
        "10100",
        "1101101"
    })
    @DisplayName("каждый нечетный символ равен 1. Не валидная строка.")
    void task5_invalid_test(String input) {
        boolean result = Task8Utils.task5(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "001",
        "100",
        "010",
        "000",
        "0100",
        "0001"
    })
    @DisplayName("Содержит не менее двух 0 и не более одной 1. Валидная строка.")
    void task6_valid_test(String input) {
        boolean result = Task8Utils.task6(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "01",
        "1100",
        "001100",
        "111",
        "0"
    })
    @DisplayName("Содержит не менее двух 0 и не более одной 1. Не валидная строка.")
    void task6_invalid_test(String input) {
        boolean result = Task8Utils.task6(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0101",
        "1",
        "0",
        "101",
        "10010010010"
    })
    @DisplayName("Нет последовательных 1. Валидная строка.")
    void task7_valid_test(String input) {
        boolean result = Task8Utils.task7(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "11",
        "110",
        "011",
        "1011",
        "100110010010"
    })
    @DisplayName("Нет последовательных 1. Не валидная строка.")
    void task7_invalid_test(String input) {
        boolean result = Task8Utils.task7(input);

        assertThat(result).isFalse();
    }
}
