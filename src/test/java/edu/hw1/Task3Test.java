package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task3Test {
    @Test
    @DisplayName("Массив может быть вложен во второй")
    void test1() {
        int[] array1 = {1, 2, 3, 4};
        int[] array2 = {0, 6};

        boolean isNestable = Task3.isNestable(array1, array2);

        assertThat(isNestable).isTrue();
    }

    @Test
    @DisplayName("Массив может быть вложен во второй")
    void test2() {
        int[] array1 = {3, 1};
        int[] array2 = {4, 0};

        boolean isNestable = Task3.isNestable(array1, array2);

        assertThat(isNestable).isTrue();
    }

    @Test
    @DisplayName("Минимальне и максимальные значения массивов совпадают")
    void test3() {
        int[] array1 = {9, 9, 8};
        int[] array2 = {8, 9};

        boolean isNestable = Task3.isNestable(array1, array2);

        assertThat(isNestable).isFalse();
    }

    @Test
    @DisplayName("Массив не может быть вложен во второй")
    void test4() {
        int[] array1 = {1, 2, 3, 4};
        int[] array2 = {2, 3};

        boolean isNestable = Task3.isNestable(array1, array2);

        assertThat(isNestable).isFalse();
    }

    @Test
    @DisplayName("Два пустых массива")
    void test5() {
        int[] array1 = {};
        int[] array2 = {};

        boolean isNestable = Task3.isNestable(array1, array2);

        assertThat(isNestable).isFalse();
    }
}
