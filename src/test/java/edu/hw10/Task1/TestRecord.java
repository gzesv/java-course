package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;

public record TestRecord(@NotNull String word, @Min(value = 17) @Max(value = 357) int number) { }
