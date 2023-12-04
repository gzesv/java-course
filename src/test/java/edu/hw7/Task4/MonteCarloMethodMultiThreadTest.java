package edu.hw7.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MonteCarloMethodMultiThreadTest {
    private final static Logger LOGGER = LogManager.getLogger();

    @ParameterizedTest
    @CsvSource({
        "10_000_000,2",
        "100_000_000,2",
        "1_000_000_000,2",
        "10_000_000,4",
        "100_000_000,4",
        "1_000_000_000,4",
        "10_000_000,6",
        "100_000_000,6",
        "1_000_000_000,6",
        "10_000_000,8",
        "100_000_000,8",
        "1_000_000_000,8",
    })
    void calculatePi(int totalCount, int threadsCount) {
        long startTime = System.nanoTime();
        double pi = MonteCarloMethodMultiThread.calculatePi(totalCount, threadsCount);
        long endTime = System.nanoTime();

        LOGGER.info("Количество точек: " + totalCount);
        LOGGER.info("Количество потоков: " + threadsCount);
        LOGGER.info("Время в секундах: " + (endTime - startTime) / 1_000_000_000.0);
        LOGGER.info("Число Пи: " + pi);
    }

}
