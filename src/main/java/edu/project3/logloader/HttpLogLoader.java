package edu.project3.logloader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class HttpLogLoader implements LogLoader {
    private final String path;
    private List<String> records = new ArrayList<>();

    public HttpLogLoader(String path) {
        this.path = path;
    }

    public void readLogs() {
        try (HttpClient httpClient = HttpClient.newBuilder().build()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(path))
                .GET()
                .build();
            HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );
            records = response.body().lines().toList();
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getRecords() {
        return records;
    }
}
