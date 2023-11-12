package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DateParserTest {

    @ParameterizedTest
    @MethodSource("arguments")
    @DisplayName("Корректные значения")
    void parseDate_test(String dateString, LocalDate expected) {
        Optional<LocalDate> date = DateParser.parseDate(dateString);

        assertThat(date).isNotNull().isNotEmpty();
        assertThat(date.get()).isEqualTo(expected);
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
            Arguments.of("2020-10-10", LocalDate.of(2020, 10, 10)),
            Arguments.of("2020-12-2", LocalDate.of(2020, 12, 2)),
            Arguments.of("1/3/1976", LocalDate.of(1976, 3, 1)),
            Arguments.of("1/3/20", LocalDate.of(2020, 3, 1)),
            Arguments.of("tomorrow", LocalDate.now().plusDays(1)),
            Arguments.of("today", LocalDate.now()),
            Arguments.of("yesterday", LocalDate.now().minusDays(1)),
            Arguments.of("1 day ago", LocalDate.now().minusDays(1)),
            Arguments.of("2234 days ago", LocalDate.now().minusDays(2234))
        );
    }

    @Test
    @DisplayName("Не корректное значение")
    void parseDate_test() {
        String dateString = "ff34 days ago";

        Optional<LocalDate> date = DateParser.parseDate(dateString);

        assertThat(date.isPresent()).isFalse();
    }
}
