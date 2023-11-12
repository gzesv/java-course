package edu.hw3.Task2;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class BracketsClusterizeTest {

    private static Arguments[] brackets() {
        return new Arguments[] {
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))
        };
    }

    @ParameterizedTest
    @MethodSource("brackets")
    @DisplayName("Кластеризации строк")
    public void clusterize_test(String input, List<String> expected) {

        List<String> clusterizeString = BracketsClusterize.clusterize(input);

        assertThat(clusterizeString).isEqualTo(expected);
    }
}
