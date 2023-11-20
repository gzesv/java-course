package edu.project3.logloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileLogLoaderTest {
    private static final String FILE_PATH =
        "/test.txt";

    @Test
    void fileLogLoader_test(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        Path path = Path.of(dir + FILE_PATH);
        Files.createFile(path);
        List<String> expected = List.of(
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)",
            "93.180.71.3 - - [17/May/2015:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
        );
        Files.write(path, expected);

        FileLogLoader loader = new FileLogLoader(path);
        loader.readLogs();
        List<String> records = loader.getRecords();

        assertThat(records).isNotNull().isEqualTo(expected);

    }

    @Test
    void fileLogLoader_incorrectPath_test() {
        FileLogLoader loader = new FileLogLoader(Path.of(""));

        assertThrows(
            RuntimeException.class,
            loader::readLogs
        );
    }
}
