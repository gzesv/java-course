package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.project1.validator.Validator.isGiveUpCommand;
import static edu.project1.validator.Validator.isIncorrectInput;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ValidatorTest {
    @Test
    @DisplayName("Ввод команды для завершения игры.")
    void giveUp_command_entered_test() {
        String input = "exit";

        boolean result = isGiveUpCommand(input);

        assertThat(result).isTrue();
    }

    @DisplayName("Введена некорректная строка")
    @ParameterizedTest
    @CsvSource({
        "1, true",
        "qw, true",
        "!, true",
        ", true",
    })
    void incorrectInput_test(String input, boolean expected) {
        boolean result = isIncorrectInput(input);

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("Введена корректная строка")
    @ParameterizedTest
    @CsvSource({
        "q, false",
        "w, false",
        "й, false",
        "ц, false",
    })
    void correctInput_test(String input, boolean expected) {
        boolean result = isIncorrectInput(input);

        assertThat(result).isEqualTo(expected);
    }
}
