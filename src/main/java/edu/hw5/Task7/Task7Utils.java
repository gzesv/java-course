package edu.hw5.Task7;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task7Utils {
    // Строка содержит не менее 3 символов, причем третий символ равен 0.
    public static boolean task1(String input) {
        return input.matches("^[01]{2}0[01]*");
    }

    // Строка начинается и заканчивается одним и тем же символом.
    public static boolean task2(String input) {
        return input.matches("^(0(1*0)*)?$|^(1(0*1)*)?$");
    }

    // Длина строки не менее 1 и не более 3
    public static boolean task3(String input) {
        return input.matches("^[01]{1,3}$");
    }
}
