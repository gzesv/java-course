package edu.hw5.Task4;

@SuppressWarnings("HideUtilityClassConstructor")
public class PasswordValidator {
    public static boolean isValidPassword(String password) {
        return password.matches(".*[~!@#$%^&*|].*");
    }
}
