package edu.project1.session;

import edu.project1.guessresult.GuessResult;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("MultipleStringLiterals")
public class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private final Set<Character> usedSymbols;
    private int attempt;
    private boolean gameFinished = false;

    public Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.userAnswer = new char[this.answer.length()];
        for (int i = 0; i < answer.length(); i++) {
            this.userAnswer[i] = '*';
        }
        this.usedSymbols = new HashSet<>();
        this.attempt = 0;
        this.maxAttempts = maxAttempts;
    }

    @SuppressWarnings("ReturnCount")
    @NotNull public GuessResult guess(char guess) {
        if (isUsedSymbols(guess)) {
            return new GuessResult.RepeatedGuess(this.userAnswer, repeatedGuessMessage(guess));
        }

        usedSymbols.add(guess);

        int guessIndex = this.answer.indexOf(guess);
        if (guessIndex != -1) {
            openSymbols(guess);

            if (isAnswerGuessed()) {
                gameFinished = true;
                return new GuessResult.Win(this.userAnswer, winMessage());
            }
            return new GuessResult.SuccessfulGuess(this.userAnswer, successfulGuessMessage(guess));
        }

        this.attempt++;

        if (isAttemptsGone()) {
            gameFinished = true;
            return new GuessResult.Defeat(this.answer.toCharArray(), defeatMessage());
        }

        return new GuessResult.FailedGuess(this.userAnswer, failedGuessMessage());
    }

    public GuessResult giveUp() {
        gameFinished = true;
        return new GuessResult.Defeat(this.answer.toCharArray(), defeatMessage());
    }

    public boolean isGameFinished() {
        return this.gameFinished;
    }

    private void openSymbols(char symbol) {
        for (int i = 0; i < this.answer.length(); i++) {
            if (this.answer.charAt(i) == symbol) {
                this.userAnswer[i] = symbol;
            }
        }
    }

    private boolean isAttemptsGone() {
        return this.attempt == this.maxAttempts;
    }

    private boolean isAnswerGuessed() {
        return String.valueOf(this.userAnswer).matches("[а-я]+");
    }

    private boolean isUsedSymbols(char guess) {
        return this.usedSymbols.contains(guess);
    }

    private String repeatedGuessMessage(char guess) {
        return "Буква \"" + guess + "\" уже была. Введите другую букву";
    }

    private String winMessage() {
        return "Вы выиграли!!!";
    }

    private String successfulGuessMessage(char guess) {
        return "Буква \"" + guess + "\" открыта.";
    }

    private String defeatMessage() {
        return "Вы проиграли :(";
    }

    private String failedGuessMessage() {
        return "Неверно! Ошибка: " + attempt + " из " + maxAttempts + ".";
    }
}
