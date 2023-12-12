package edu.hw8.Task1;

import java.security.SecureRandom;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientServerTest {
    private static Server server;
    private static final String DEFAULT_VALUE = "Ничего не найдено(";
    private static final List<String> words = List.of(
        "личности",
        "оскорбления"
    );
    private SecureRandom random = new SecureRandom();

    @Test
    void getQuotes_test() {
        String keyword = "личности";

        String response = Quotes.getQuotes(keyword);

        String expected = "Не переходи на личности там, где их нет";
        assertEquals(expected, response);
    }

    @Test
    void findUnknownResponse_test() {
        String keyword = "1";

        String response = Quotes.getQuotes(keyword);

        assertEquals(DEFAULT_VALUE, response);
    }

    @Test
    @DisplayName("Отправка запросов на сервер")
    void sendWordsToServer_tset() throws InterruptedException {
        Thread thread = new Thread(() -> {
            server = new Server();
            server.serverRun();
        });
        thread.start();
        Thread.sleep(1000);

        String word = words.get(random.nextInt(0, words.size()));
        Client client = new Client();
        String answer = client.clientRun(word);

        assertThat(Quotes.getQuotes(word)).contains(answer);
    }
}
