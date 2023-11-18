package edu.hw5.Task8;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task8Utils {

    // Строка нечетной длины
    public static boolean task1(String input) {
        return input.matches("^[01]([01]{2})*$");
    }

    // Строка начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    public static boolean task2(String input) {
        return input.matches("^1[01]([01]{2})*$|^0([01]{2})*$");
    }

    public static boolean task3(String input) {
        return false;
    }

    // Любая строка, кроме 11 или 111
    public static boolean task4(String input) {
        return !input.matches("^11$|^111$");
    }

    // Строка каждый нечетный символ которой равен 1
    public static boolean task5(String input) {
        return input.matches("^1([01]1)*[01]?$");
    }

    // Строка содержит не менее двух 0 и не более одной 1
    public static boolean task6(String input) {
        return input.matches("^(100+|0+10+|0+01|00+)$");
    }

    // Строка в кторой нет последовательных 1
    public static boolean task7(String input) {
        return !input.matches("[01]*11[01]*");
    }
}
