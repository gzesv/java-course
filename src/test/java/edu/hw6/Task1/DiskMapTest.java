package edu.hw6.Task1;

import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DiskMapTest {
    private static final String FILE_PATH = "src/test/java/edu/hw6/task1/test.txt";
    private DiskMap diskMap;

    @BeforeEach
    void setUp() {
        diskMap = new DiskMap(Path.of(FILE_PATH));
        diskMap.clear();
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
