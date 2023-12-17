package edu.hw10.Task2;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CacheProxyTest {
    @Test
    void memoryCache_test(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) {
        CacheTest cacheTest = num -> {
            int first = 0;
            int second = 1;
            if (num == 1) {
                return first;
            }

            for (int i = 2; i < num; i++) {
                int next = first + second;
                first = second;
                second = next;
            }
            return second;
        };

        CacheTest test = CacheProxy.create(cacheTest, CacheTest.class, dir);

        assertThat(test.test(5)).isEqualTo(3);
    }

    @Test
    void fileCache_test(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) {
        CacheTestFile cacheTest = num -> {
            int first = 0;
            int second = 1;
            if (num == 1) {
                return first;
            }

            for (int i = 2; i < num; i++) {
                int next = first + second;
                first = second;
                second = next;
            }
            return second;
        };

        CacheTestFile test = CacheProxy.create(cacheTest, CacheTestFile.class, dir);
        assertThat(test.test(5)).isEqualTo(3);
    }
}
