package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings({"HideUtilityClassConstructor", "MagicNumber"})
public class MonteCarloMethodMultiThread {
    public static double calculatePi(int totalCount, int threadsCount) {
        AtomicInteger circleCount = new AtomicInteger(0);
        int pointCount = totalCount / threadsCount;
        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < pointCount; j++) {
                    double x = ThreadLocalRandom.current().nextDouble(0, 1);
                    double y = ThreadLocalRandom.current().nextDouble(0, 1);
                    if (isInCircle(new Point(x, y))) {
                        circleCount.getAndIncrement();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return 4 * ((double) circleCount.get() / totalCount);
    }

    private static boolean isInCircle(Point point) {
        return Math.pow(point.x(), 2) + Math.pow(point.y(), 2) < 1;
    }
}
