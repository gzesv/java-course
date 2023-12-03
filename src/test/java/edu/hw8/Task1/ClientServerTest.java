package edu.hw8.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ClientServerTest {
    private static Server server;
    private static final String DEFAULT_VALUE = "Ничего не найдено(";

    @Test
    void getQuotes_test() {
        String keyword = "личности";

        String response = Quotes.getQuotes(keyword);

        String expected = "Не переходи на личности там, где их нет";
        assertEquals(expected, response);
    }

    @Test
    void findUnknownResponse() {
        String keyword = "1";

        String response = Quotes.getQuotes(keyword);

        assertEquals(DEFAULT_VALUE, response);
    }

    @Test
    @DisplayName("Отправка запросов на сервер в несколько потоков.")
    void sendWordsToServer() throws InterruptedException {

        Thread thread = new Thread(() -> {
            server = new Server();
            server.serverRun();
        });
        thread.start();
        Thread.sleep(1000);

        List<String> words = List.of(
            "личности",
            "оскорбления"
        );

        SecureRandom random = new SecureRandom();

        ExecutorService executorService = Executors.newCachedThreadPool();

        Callable<Void> sendWord = () -> {
            String word = words.get(random.nextInt(0, words.size()));
            Client client = new Client();
            String answer = client.clientRun(word);
            assertThat(Quotes.getQuotes(word)).contains(answer);
            return null;
        };

        var tasks = Stream.generate(() -> sendWord).limit(3).toList();
        try {
            List<Future<Void>> futures = executorService.invokeAll(tasks);
            for (var future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
