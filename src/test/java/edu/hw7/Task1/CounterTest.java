package edu.hw7.Task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CounterTest {
    @ParameterizedTest
    @CsvSource({"111,1", "2,4", "10,100", "12345,200"})
    void increment_test(int initialValue, int threadsCount) {
        int result = Counter.increment(initialValue, threadsCount);

        assertThat(result).isEqualTo(initialValue + threadsCount);
    }
}
