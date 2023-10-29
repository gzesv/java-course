package edu.project1.guessresult;

sealed public interface GuessResult {

    char[] state();

    String message();

    record Defeat(char[] state, String message) implements GuessResult { }

    record Win(char[] state, String message) implements GuessResult { }

    record SuccessfulGuess(char[] state, String message) implements GuessResult { }

    record FailedGuess(char[] state, String message) implements GuessResult { }

    record RepeatedGuess(char[] state, String message) implements GuessResult { }
}
