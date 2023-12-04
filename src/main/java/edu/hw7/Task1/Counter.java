package edu.hw7.Task1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Counter {
    public static int increment(int initialValue, int threadsCount) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadsCount);

        AtomicInteger currentValue = new AtomicInteger(initialValue);

        Thread[] threads = new Thread[threadsCount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                currentValue.incrementAndGet();
                countDownLatch.countDown();
            });
            threads[i].start();
        }

        countDownLatch.await();

        return currentValue.get();
    }
}
