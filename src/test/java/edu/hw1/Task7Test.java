package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task7Test {
    @Test
    @DisplayName("Сдвиг влево")
    void test1() {

        int n = 387;
        int shift = 5;

        int newNumber = Task7.rotateLeft(n, shift);

        assertThat(newNumber).isEqualTo(120);
    }

    @Test
    @DisplayName("Сдвиг вправо")
    void test2() {

        int n = 720;
        int shift = 2;

        int newNumber = Task7.rotateRight(n, shift);

        assertThat(newNumber).isEqualTo(180);
    }

    @Test
    @DisplayName("Исходное число меньше 0")
    void test3() {

        int n = -1;
        int shift = 2;

        int newNumber = Task7.rotateLeft(n, shift);

        assertThat(newNumber).isEqualTo(-1);
    }

    @Test
    @DisplayName("Размер циклического сдвига < 0")
    void test4() {

        int n = 111;
        int shift = -2;

        int newNumber = Task7.rotateRight(n, shift);

        assertThat(newNumber).isEqualTo(-1);
    }

    @Test
    @DisplayName("Число не изменяется после сдвига вправо")
    void test5() {

        int n = 1;
        int shift = 1;

        int newNumber = Task7.rotateRight(n, shift);

        assertThat(newNumber).isEqualTo(1);
    }

    @Test
    @DisplayName("Число не изменяется после сдвига влево")
    void test6() {

        int n = 17;
        int shift = 5;

        int newNumber = Task7.rotateLeft(n, shift);

        assertThat(newNumber).isEqualTo(17);
    }
}
