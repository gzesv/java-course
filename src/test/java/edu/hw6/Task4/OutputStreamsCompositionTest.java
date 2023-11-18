package edu.hw6.Task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OutputStreamsCompositionTest {
    private static final String TEST_FILE = "src/test/java/edu/hw6/Task4/test_task4.txt";

    @Test
    void write_test() throws IOException {
        String message = "Programming is learned by writing programs. ― Brian Kernighan";

        OutputStreamsComposition.write(TEST_FILE, message);
        String result = Files.readAllLines(Path.of(TEST_FILE)).get(0);

        assertThat(result).isEqualTo(message);
    }
}
