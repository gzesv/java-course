package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private static final URI TOP_URL =
        URI.create("https://hacker-news.firebaseio.com/v0/topstories.json");

    private static final URI NEWS_URL =
        URI.create("https://hacker-news.firebaseio.com/v0/item/");

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    private final HttpClient client;

    public HackerNews() {
        this.client = HttpClient.newHttpClient();
    }

    public long[] hackerNewsTopStories() {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(TOP_URL)
            .timeout(DEFAULT_TIMEOUT)
            .GET()
            .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return parseStoriesIds(response.body());
    }

    public String news(long id) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(NEWS_URL.resolve(id + ".json"))
            .timeout(DEFAULT_TIMEOUT)
            .GET()
            .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return parseNewsTitle(response.body());
    }

    private static long[] parseStoriesIds(String input) {
        String[] stringNumbers = input.substring(1, input.length() - 1).split(",");

        long[] longArray = new long[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            longArray[i] = Long.parseLong(stringNumbers[i].trim());
        }

        return longArray;
    }

    private static String parseNewsTitle(String input) {
        Pattern pattern = Pattern.compile("\"title\":\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? matcher.group(1) : "Title not found";
    }
}
