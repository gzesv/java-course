package edu.hw3.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AtbashCipherTest {
    @Test
    @DisplayName("Пустая строка")
    void emptyString_test() {
        String input = "";

        String encryptedString = AtbashCipher.atbash(input);

        assertThat(encryptedString).isEqualTo("");
    }

    @Test
    @DisplayName("Тест Hello world!")
    void helloWorld_test() {
        // Arrange
        String input = "Hello world!";
        String expected = "Svool dliow!";

        // Act
        String encryptedString = AtbashCipher.atbash(input);

        // Assert
        assertThat(encryptedString).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест с символами кириллицы и цифрами")
    void cyrillicCharacters_and_numbers_test() {
        String input = "1абвSvool dliow!АБВ1";
        String expected = "1абвHello world!АБВ1";

        String encryptedString = AtbashCipher.atbash(input);

        assertThat(encryptedString).isEqualTo(expected);
    }
}
