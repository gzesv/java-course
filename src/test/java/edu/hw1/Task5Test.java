package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task5Test {
    @Test
    @DisplayName("Исходное число является палиндромом")
    void test1() {
        int number  = 1221;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant).isTrue();
    }

    @Test
    @DisplayName("Потомок числа является палиндромом")
    void test2() {
        int number  = 11211230;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant).isTrue();
    }

    @Test
    @DisplayName("Длинна числа нечетная")
    void test3() {
        int number  = 567;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant).isFalse();
    }
}
