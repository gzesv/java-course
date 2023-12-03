package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private static final int PORT = 11223;
    private static final int MAX_CONNECTIONS = 5;
    private static final int BUFFER_SIZE = 1024;
    private static final String CLIENT_IS_CONNECTED = "Подключен клиент: %s\n";
    private static final String SERVER_START = "Сервер запущен. Ожидание подключений...\n";
    private static final Logger LOGGER = LogManager.getLogger();
    private final ExecutorService executorService;

    public Server() {
        this.executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    }

    public void serverRun() {
        LOGGER.info(SERVER_START);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            int clientCount = 0;

            while (clientCount < MAX_CONNECTIONS) {
                Socket clientSocket = serverSocket.accept();
                LOGGER.info(CLIENT_IS_CONNECTED);
                executorService.execute(() -> handleClient(clientSocket));
                clientCount++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleClient(final Socket clientSocket) {
        try (
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream()
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                int bytesRead = inputStream.read(buffer);

                if (bytesRead != -1) {
                    String request = new String(buffer, 0, bytesRead);

                    String response = Quotes.getQuotes(request);

                    outputStream.write(response.getBytes());
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
