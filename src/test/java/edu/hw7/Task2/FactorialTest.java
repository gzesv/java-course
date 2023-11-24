package edu.hw7.Task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FactorialTest {
    @ParameterizedTest
    @CsvSource({"0,1", "1,1", "2,2", "6,720", "10,3628800"})
    void factorial_test(int number, int expected) {
        int result = Factorial.factorial(number);

        assertThat(result).isEqualTo(expected);
    }
}
