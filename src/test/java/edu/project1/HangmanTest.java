package edu.project1;

import edu.project1.guessresult.GuessResult;
import edu.project1.session.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanTest {

    @Test
    @DisplayName("Игрок угадал слово")
    void sessionWin_test() {

        String answer = "оооооо";
        int maxAttempts = 2;
        Session session = new Session(answer, maxAttempts);

        GuessResult guessResult = session.guess('о');

        assertThat(guessResult.getClass()).isSameAs(GuessResult.Win.class);
        assertThat(guessResult.state()).isEqualTo(new char[] {'о', 'о', 'о', 'о', 'о', 'о'});
        assertThat(session.isGameFinished()).isTrue();
    }

    @Test
    @DisplayName("Игрок решил сдаться")
    void sessionGiveUp_test() {

        String answer = "буква";
        Session session = new Session(answer, 7);

        GuessResult guessResult = session.giveUp();

        assertThat(guessResult.getClass()).isSameAs(GuessResult.Defeat.class);
        assertThat(String.valueOf(guessResult.state())).isEqualTo(answer);
        assertThat(session.isGameFinished()).isTrue();
    }

    @Test
    @DisplayName("Угадана буква")
    void sessionSuccessfulGuess_test() {

        String answer = "молоко";
        Session session = new Session(answer, 5);

        GuessResult guessResult = session.guess('о');

        assertThat(guessResult.getClass()).isSameAs(GuessResult.SuccessfulGuess.class);
        assertThat(guessResult.state()).isEqualTo(new char[] {'*', 'о', '*', 'о', '*', 'о'});
    }

    @Test
    @DisplayName("Ввод символа который уже был")
    void repeatedInput_() {

        String answer = "молоко";
        Session session = new Session(answer, 7);

        GuessResult guessResult = session.guess('о');

        assertThat(guessResult.getClass()).isSameAs(GuessResult.SuccessfulGuess.class);
        assertThat(guessResult.state()).isEqualTo(new char[] {'*', 'о', '*', 'о', '*', 'о'});

        guessResult = session.guess('о');

        assertThat(guessResult.getClass()).isSameAs(GuessResult.RepeatedGuess.class);
        assertThat(guessResult.state()).isEqualTo(new char[] {'*', 'о', '*', 'о', '*', 'о'});
    }

    @Test
    @DisplayName("Закончились попытки. Игрок проиграл.")
    void attemptsAreOver_test() {

        String answer = "молоко";
        int maxAttempts = 2;
        Session session = new Session(answer, maxAttempts);

        GuessResult guessResult = session.guess('й');

        assertThat(guessResult.getClass()).isSameAs(GuessResult.FailedGuess.class);
        assertThat(guessResult.state()).isEqualTo(new char[] {'*', '*', '*', '*', '*', '*'});

        guessResult = session.guess('о');

        assertThat(guessResult.getClass()).isSameAs(GuessResult.SuccessfulGuess.class);
        assertThat(guessResult.state()).isEqualTo(new char[] {'*', 'о', '*', 'о', '*', 'о'});

        guessResult = session.guess('ю');

        assertThat(guessResult.getClass()).isSameAs(GuessResult.Defeat.class);
        assertThat(String.valueOf(guessResult.state())).isEqualTo(answer);
        assertThat(session.isGameFinished()).isTrue();
    }
}
