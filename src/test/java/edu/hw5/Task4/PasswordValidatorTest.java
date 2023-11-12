package edu.hw5.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw5.Task4.PasswordValidator.isValidPassword;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PasswordValidatorTest {
    @DisplayName("Пароль содерит один из требуемых символов.")
    @ParameterizedTest
    @ValueSource(strings = {
        "123~",
        "!ew",
        "@qqq",
        "11#11",
        "ww$ww",
        "%",
        "^",
        "&",
        "*",
        "|"
    })
    void validPassword_test(String password) {
        boolean result = isValidPassword(password);

        assertThat(result).isTrue();
    }

    @DisplayName("Пароль не содерит один из требуемых символов.")
    @ParameterizedTest
    @ValueSource(strings = {
        "qwe123",
        "111",
        " ",
        ""
    })
    void invalidPassword_test(String password) {
        boolean result = isValidPassword(password);

        assertThat(result).isFalse();
    }
}
