package edu.hw3.Task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NullComparatorTest {
    @Test
    @DisplayName("null в качестве ключа")
    public void null_test() {
        TreeMap<String, String> tree = new TreeMap<>(
            new NullComparator<>()
        );

        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
