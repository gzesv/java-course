package edu.hw5.Task2;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task2.Friday13.findAllFriday13InYear;
import static edu.hw5.Task2.Friday13.findNextFriday13;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Friday13Test {

    @Test
    void findAllFriday13InYear_test() {
        int year = 1925;
        var expected = List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );

        List<LocalDate> fridays = findAllFriday13InYear(year);

        assertThat(fridays).isEqualTo(expected);
    }

    @Test
    void findNextFriday13_test() {
        LocalDate current = LocalDate.of(2023, 1, 13);
        LocalDate expected = LocalDate.of(2023, 10, 13);

        LocalDate result = findNextFriday13(current);

        assertThat(result).isEqualTo(expected);
    }
}
