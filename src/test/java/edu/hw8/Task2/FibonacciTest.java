package edu.hw8.Task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {

    @Test
    void getFibonacci_test() {
        int answer = Fibonacci.getFibonacci(6);

        assertEquals(8, answer);
    }
}
