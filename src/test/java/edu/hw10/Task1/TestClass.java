package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;

public class TestClass {
    private final String word;
    private final int number;

    public TestClass(@NotNull String word, @Min(value = 1) @Max(value = 1111) int number) {
        this.word = word;
        this.number = number;
    }

    public static TestClass create(@NotNull String word, @Min(value = 7) @Max(value = 250) int number) {
        return new TestClass(word, number);
    }

    public String getWord() {
        return word;
    }

    public int getNumber() {
        return number;
    }
}
