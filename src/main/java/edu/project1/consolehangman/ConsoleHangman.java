package edu.project1.consolehangman;

import edu.project1.dictionary.IDictionary;
import edu.project1.guessresult.GuessResult;
import edu.project1.session.Session;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MAX_ATTEMPTS = 5;
    private static final String END_GAME_COMMAND = "exit";
    private final IDictionary dictionary;

    public ConsoleHangman(IDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void run() {
        String answer = this.dictionary.getRandomWord();
        Session session = new Session(answer, MAX_ATTEMPTS);
        Scanner scanner = new Scanner(System.in);

        LOGGER.info("Добро пожаловать.");
        LOGGER.info("Отгадайте слово.");
        LOGGER.info("Если вы хотите завершить игру напишите слово \"exit\".");

        while (!session.isGameFinished()) {
            LOGGER.info("Введите букву: ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (isGiveUpCommand(userInput)) {
                GuessResult guessResult = session.giveUp();
                printGameState(guessResult);
                break;
            }

            Character userGuess = readUserGuess(userInput);

            if (userGuess == null) {
                LOGGER.info("Неверный формат ввода. Введите букву.");
                continue;
            }

            GuessResult guessResult = session.guess(userGuess);

            printGameState(guessResult);
        }
        LOGGER.info("До скорых встреч!");
    }

    private Character readUserGuess(String userInput) {
        if (userInput.length() != 1 || !Character.isLetter(userInput.charAt(0))) {
            return null;
        }
        return userInput.charAt(0);
    }

    private void printGameState(GuessResult guess) {
        switch (guess) {
            case GuessResult.Win win -> printMessageAndAnswerState(win);
            case GuessResult.Defeat defeat -> printMessageAndAnswerState(defeat);
            case GuessResult.SuccessfulGuess successfulGuess -> printMessageAndAnswerState(successfulGuess);
            case GuessResult.FailedGuess failedGuess -> printMessageAndAnswerState(failedGuess);
            case GuessResult.RepeatedGuess repeatedGuess -> printMessageAndAnswerState(repeatedGuess);
        }
    }

    private boolean isGiveUpCommand(String input) {
        return input.equals(END_GAME_COMMAND);
    }

    private void printMessageAndAnswerState(GuessResult guessResult) {
        LOGGER.info(guessResult.message());
        LOGGER.info("Слово: " + String.valueOf(guessResult.state()) + ".");
    }
}
