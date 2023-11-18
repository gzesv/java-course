package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FileClonerTest {
    private static final String FILE_PATH =
        "/Tinkoff Bank Biggest Secret.txt";
    private static final String COPY_FILE_PATH_1 =
        "/Tinkoff Bank Biggest Secret — копия.txt";
    private static final String COPY_FILE_PATH_2 =
        "/Tinkoff Bank Biggest Secret — копия (2).txt";


    @Test
    void cloneFile_test(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        Path path = Path.of(dir + FILE_PATH);
        Files.createFile(path);
        Path expected1 = Path.of(dir + COPY_FILE_PATH_1);
        Path expected2 = Path.of(dir + COPY_FILE_PATH_2);

        FileCloner.cloneFile(path);
        FileCloner.cloneFile(path);

        assertThat(Files.exists(expected1)).isTrue();
        assertThat(Files.exists(expected2)).isTrue();
    }
}
