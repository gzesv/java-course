package edu.hw8.Task1;

import java.util.Map;

@SuppressWarnings("HideUtilityClassConstructor")
public class Quotes {
    private static final String DEFAULT_VALUE = "Ничего не найдено(";

    private static final Map<String, String> QUOTES = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления."
    );

    public static String getQuotes(String request) {
        return QUOTES.getOrDefault(request.toLowerCase(), DEFAULT_VALUE);
    }
}
