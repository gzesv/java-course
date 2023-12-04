package edu.hw7.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MonteCarloMethodSingleThreadTest {
    private final static Logger LOGGER = LogManager.getLogger();

    @ParameterizedTest
    @CsvSource({
        "10_000_000",
        "100_000_000",
        "1_000_000_000"
    })
    void calculatePi(int totalCount) {
        long startTime = System.nanoTime();
        double pi = MonteCarloMethodSingleThread.calculatePi(totalCount);
        long endTime = System.nanoTime();

        LOGGER.info("Количество точек: " + totalCount);
        LOGGER.info("Количество потоков: " + 1);
        LOGGER.info("Время в секундах: " + (endTime - startTime) / 1_000_000_000.0);
        LOGGER.info("Число Пи: " + pi);
    }
}
