package edu.hw3.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

class RomanNumeralsTest {

    private static Arguments[] numbers() {
        return new Arguments[] {
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(202, "CCII"),
            Arguments.of(503, "DIII"),
            Arguments.of(1999, "MCMXCIX"),
            Arguments.of(3999, "MMMCMXCIX")
        };
    }

    @ParameterizedTest
    @MethodSource("numbers")
    @DisplayName("Корректные значения")
    void convertToRoman_test(int input, String expected) {
        String result = RomanNumerals.convertToRoman(input);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4000})
    @DisplayName("Исходное число за пределами вычисляемого диапазона")
    void convertToRoman_incorrectInputNumber_test(int number) {
        String romanNumber = RomanNumerals.convertToRoman(number);

        assertThat(romanNumber).isNull();
    }

    @Test
    @DisplayName("На входe ноль")
    void convertToRoman_zeroInput_test() {
        int zero = 0;

        String romanNumber = RomanNumerals.convertToRoman(zero);

        assertThat(romanNumber).isEqualTo("N");
    }
}
