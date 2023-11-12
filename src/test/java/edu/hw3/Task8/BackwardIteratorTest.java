package edu.hw3.Task8;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BackwardIteratorTest {
    @Test
    void backwardIterator_next_test() {
        Iterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1, 9, 15));

        assertThat(backwardIterator).toIterable().containsExactlyElementsOf(List.of(15, 9, 1));
    }

    @Test
    void backwardIterator_hasNext_test() {
        Iterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1, 9, 15));

        assertThat(backwardIterator.hasNext()).isTrue();
    }
}
