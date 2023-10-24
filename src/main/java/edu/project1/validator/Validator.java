package edu.project1.validator;

@SuppressWarnings("HideUtilityClassConstructor")
public class Validator {
    private static final String END_GAME_COMMAND = "exit";

    public static boolean isGiveUpCommand(String userInput) {
        return END_GAME_COMMAND.equals(userInput);
    }

    public static boolean isIncorrectInput(String userInput) {
        return userInput == null || userInput.length() != 1
            || !Character.isLetter(userInput.charAt(0));
    }
}
