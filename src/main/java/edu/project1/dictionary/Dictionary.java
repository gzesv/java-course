package edu.project1.dictionary;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Dictionary implements IDictionary {
    private final Random random = new Random();
    private final String[] words = {"буква", "слово"};

    @Override
    public @NotNull String getRandomWord() {
        return words[random.nextInt(words.length)];
    }
}
