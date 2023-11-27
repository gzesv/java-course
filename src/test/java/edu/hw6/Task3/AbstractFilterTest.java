package edu.hw6.Task3;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import static edu.hw6.Task3.AbstractFilter.READABLE;
import static edu.hw6.Task3.AbstractFilter.REGULAR_FILE;
import static edu.hw6.Task3.AbstractFilter.WRITABLE;
import static edu.hw6.Task3.AbstractFilter.globMatches;
import static edu.hw6.Task3.AbstractFilter.largerThan;
import static edu.hw6.Task3.AbstractFilter.regexContains;
import static edu.hw6.Task3.AbstractFilter.smallThan;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class AbstractFilterTest {
    private static final Path FILE_PATH = Path.of(
        "src/test/java/edu/hw6/Task3");

    @Test
    void abstractFilter_test() throws IOException {
        DirectoryStream.Filter<Path> filter = REGULAR_FILE
            .and(READABLE)
            .and(WRITABLE)
            .and(largerThan(1))
            .and(smallThan(1000))
            .and(AbstractFilter.magicNumber("12345test".getBytes(StandardCharsets.UTF_8))
            .and(globMatches("*.txt"))
            .and(regexContains(".*")));

        var result = new ArrayList<String>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(FILE_PATH, filter)) {
            entries.forEach(filePath ->
                result.add(filePath.getFileName().toString())
            );
        }

        assertThat(result).containsExactly("test.txt");
    }

    @Test
    void abstractFilter_notFound_test() throws IOException {
        DirectoryStream.Filter<Path> filter =
            AbstractFilter.magicNumber("12345test12124".getBytes(StandardCharsets.UTF_8));

        var result = new ArrayList<String>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(FILE_PATH, filter)) {
            entries.forEach(filePath ->
                result.add(filePath.getFileName().toString())
            );
        }

        assertThat(result).isEmpty();
    }

    @Test
    void abstractFilter_notEquals_test() throws IOException {
        DirectoryStream.Filter<Path> filter =
            AbstractFilter.magicNumber("222".getBytes(StandardCharsets.UTF_8));

        var result = new ArrayList<String>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(FILE_PATH, filter)) {
            entries.forEach(filePath ->
                result.add(filePath.getFileName().toString())
            );
        }

        assertThat(result).isEmpty();
    }
}
