package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectorySearcherTest {

    @Test
    void directorySearcher_test(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) {
        int filesCount = 1001;
        createFiles(dir, filesCount);

        DirectorySearcher fileSearcher = new DirectorySearcher(dir, 1000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<Path> result = forkJoinPool.invoke(fileSearcher);

        assertEquals(1, result.size());
    }

    @Test
    void directorySearcher_fewFiles_test(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) {
        int filesCount = 1001;
        createFiles(dir, filesCount);

        DirectorySearcher fileSearcher = new DirectorySearcher(dir, 2000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<Path> result = forkJoinPool.invoke(fileSearcher);

        assertEquals(0, result.size());
    }

    private static void createFiles(Path dir, int count) {
        try {
            Files.createDirectories(dir);
            for (int i = 0; i < count; i++) {
                String fileName = "file" + i + ".txt";
                Path filePath = dir.resolve(fileName);
                Files.createFile(filePath);
            }
            Files.createDirectories(dir.resolve("A"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
