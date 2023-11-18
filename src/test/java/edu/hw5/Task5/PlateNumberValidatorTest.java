package edu.hw5.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw5.Task5.PlateNumberValidator.isValidPlateNumber;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlateNumberValidatorTest {

    @DisplayName("Валидный российский номерной знак")
    @ParameterizedTest
    @ValueSource(strings = {
        "А123ВЕ777",
        "О777ОО177",
        "А777АА77"
    })
    void validPlateNumber_test(String number) {
        boolean result = isValidPlateNumber(number);

        assertThat(result).isTrue();
    }

    @DisplayName("Не валидный российский номерной знак")
    @ParameterizedTest
    @ValueSource(strings = {
        "123АВЕ777",
        "А123ВГ77",
        "А123ВЕ7777"
    })
    void invalidPlateNumber_test(String number) {
        boolean result = isValidPlateNumber(number);

        assertThat(result).isFalse();
    }
}
