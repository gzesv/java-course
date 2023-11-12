package edu.hw5.Task5;

@SuppressWarnings("HideUtilityClassConstructor")
public class PlateNumberValidator {
    public static boolean isValidPlateNumber(String number) {
        return number.matches("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");
    }
}
