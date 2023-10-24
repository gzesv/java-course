package edu.project1.consolehangman;

import edu.project1.dictionary.IDictionary;
import edu.project1.guessresult.GuessResult;
import edu.project1.session.Session;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.project1.validator.Validator.isGiveUpCommand;
import static edu.project1.validator.Validator.isIncorrectInput;

public class ConsoleHangman {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MAX_ATTEMPTS = 5;
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
                printMessageAndAnswerState(guessResult);
                break;
            }

            if (isIncorrectInput(userInput)) {
                LOGGER.info("Неверный формат ввода. Введите букву.");
                continue;
            }

            GuessResult guessResult = session.guess(userInput.charAt(0));

            printMessageAndAnswerState(guessResult);
        }
        LOGGER.info("До скорых встреч!");
    }

    private void printMessageAndAnswerState(GuessResult guessResult) {
        LOGGER.info(guessResult.message());
        LOGGER.info("Слово: " + String.valueOf(guessResult.state()) + ".");
    }
}
