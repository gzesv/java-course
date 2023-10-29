package edu.hw3.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class FrequencyTest {
    @Test
    @DisplayName("Частотный словарь строк")
    void freqDict_string_() {
        List<String> input = List.of("this", "and", "that", "and");
        HashMap<String, Integer> answer = new HashMap<>() {{
            put("this", 1);
            put("and", 2);
            put("that", 1);
        }};

        Map<String, Integer> map = Frequency.freqDict(input);

        assertThat(map).isNotNull().isEqualTo(answer);
    }

    @Test
    @DisplayName("Частотный словарь цифр")
    void freqDict_numeric_test() {

        List<Integer> input = List.of(1, 1, 2, 2);
        HashMap<Integer, Integer> answer = new HashMap<>() {{
            put(1, 2);
            put(2, 2);
        }};

        Map<Integer, Integer> map = Frequency.freqDict(input);

        assertThat(map).isNotNull().isEqualTo(answer);
    }
}
