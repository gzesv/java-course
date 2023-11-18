package edu.hw5.Task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task7UtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "010",
        "1100000",
        "11011111",
        "000",
        "110"
    })
    @DisplayName("Строка содержит не менее 3 символов, третий символ равен 0. Валидная строка.")
    void task1_valid_test(String input) {
        boolean valid = Task7Utils.task1(input);

        assertThat(valid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "011",
        "111",
        "0010000",
        "0",
        "11",
        "00",
        "1"
    })
    @DisplayName("Строка содержит не менее 3 символов, третий символ равен 0. Не валидная строка.")
    void task1_invalid_test(String input) {
        boolean valid = Task7Utils.task1(input);

        assertThat(valid).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "0",
        "1",
        "11",
        "010",
        "10101",
        "101010101"
    })
    @DisplayName("Строка начинается и заканчивается одним и тем же символом. Валидная строка.")
    void task2_valid_test(String input) {
        boolean valid = Task7Utils.task2(input);

        assertThat(valid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "10",
        "01",
        "100000000000",
        "011111111111"
    })
    @DisplayName("Строка начинается и заканчивается одним и тем же символом. Не валидная строка.")
    void task2_invalid_test(String input) {
        boolean valid = Task7Utils.task2(input);

        assertThat(valid).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0",
        "01",
        "10",
        "101",
        "000",
        "111"
    })
    @DisplayName("Длина не менее 1 и не более 3. Валидная строка.")
    void task3_valid_test(String input) {
        boolean valid = Task7Utils.task3(input);

        assertThat(valid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0111",
        "",
        "11111"
    })
    @DisplayName("Длина не менее 1 и не более 3. Валидная строка.")
    void task3_invalid_test(String input) {
        boolean valid = Task7Utils.task3(input);

        assertThat(valid).isFalse();
    }
}
