package edu.hw6.Task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DiskMapTest {
    private static final Path FILE_PATH =
        Path.of("src/test/java/edu/hw6/task1/test.txt");
    private DiskMap diskMap;

    @BeforeEach
    void setUp() {
        try {
            Files.createFile(FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap = new DiskMap(FILE_PATH);
        diskMap.clear();
    }

    @AfterEach
    void tearDown() {
        if (Files.exists(FILE_PATH)) {
            try {
                Files.delete(FILE_PATH);
            } catch (IOException ignored) {
            }
        }
    }

    @Test
    @DisplayName("Сохранение")
    void save_test() {
        String key = "key";
        String value = "value";

        diskMap.put(key, value);
        diskMap.save();

        assertThat(diskMap.get(key)).isEqualTo(value);
    }

    @Test
    @DisplayName("Чтение")
    void read_test() {
        String key = "key";
        String value = "value";
        diskMap.put(key, value);
        diskMap.save();
        diskMap.clear();

        diskMap.read();

        assertThat(diskMap.get(key)).isEqualTo(value);
    }

    @Test
    void size_test() {
        String key = "key";
        String value = "value";
        diskMap.put(key, value);

        int size = diskMap.size();

        assertThat(size).isEqualTo(1);
    }

    @Test
    void isEmpty_test() {
        String key = "key";
        String value = "value";
        diskMap.put(key, value);

        boolean isEmpty = diskMap.isEmpty();

        assertThat(isEmpty).isFalse();

        diskMap.clear();
        isEmpty = diskMap.isEmpty();

        assertThat(isEmpty).isTrue();
    }

    @Test
    void containsKey_test() {
        String key = "key";
        String value = "value";
        diskMap.put(key, value);

        boolean contains = diskMap.containsKey(key);
        boolean notContains = diskMap.containsKey(value);

        assertThat(contains).isTrue();
        assertThat(notContains).isFalse();
    }

}
