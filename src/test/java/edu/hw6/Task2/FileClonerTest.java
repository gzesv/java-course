package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FileClonerTest {
    private static final Path FILE_PATH = Path.of(
        "src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret.txt");
    private static final Path COPY_FILE_PATH_1 = Path.of(
        "src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret — копия.txt");
    private static final Path COPY_FILE_PATH_2 = Path.of(
        "src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret — копия (2).txt");

    @BeforeEach
    void setUp() {
        try {
            Files.createFile(FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        if (Files.exists(FILE_PATH)) {
            try {
                Files.delete(FILE_PATH);
            } catch (IOException ignored) { }
        }
        if (Files.exists(COPY_FILE_PATH_1)) {
            try {
                Files.delete(COPY_FILE_PATH_1);
            } catch (IOException ignored) { }
        }
        if (Files.exists(COPY_FILE_PATH_2)) {
            try {
                Files.delete(COPY_FILE_PATH_2);
            } catch (IOException ignored) { }
        }
    }

    @Test
    void cloneFile_test() {
        Path expected1 = COPY_FILE_PATH_1;
        Path expected2 = COPY_FILE_PATH_2;

        FileCloner.cloneFile(FILE_PATH);
        FileCloner.cloneFile(FILE_PATH);

        assertThat(Files.exists(expected1)).isTrue();
        assertThat(Files.exists(expected2)).isTrue();
    }

}
