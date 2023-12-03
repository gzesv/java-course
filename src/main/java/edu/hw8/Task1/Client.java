package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 11223;
    private static final int BUFFER_SIZE = 1024;
    private InputStream inputStream;
    private OutputStream outputStream;

    public String clientRun(String message) {
        String answer;
        try {
            try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                byte[] buffer = new byte[BUFFER_SIZE];

                sendMessage(message);
                answer = readAnswer(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return answer;
    }

    private void sendMessage(String message) throws IOException {
        outputStream.write(message.getBytes());
    }

    private String readAnswer(byte[] buffer) throws IOException {
        int bytesRead = inputStream.read(buffer);
        return new String(buffer, 0, bytesRead);
    }
}
