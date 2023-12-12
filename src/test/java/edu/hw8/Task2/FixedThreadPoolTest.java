package edu.hw8.Task2;

import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FixedThreadPoolTest {
    @Test
    public void fixedThreadPool_test() throws Exception {
        int threadsCount = 3;
        FixedThreadPool threadPool = FixedThreadPool.create(threadsCount);
        CountDownLatch latch = new CountDownLatch(threadsCount);

        threadPool.start();

        for (int i = 0; i < 3; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(100);
                    latch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        latch.await();
        threadPool.close();

        assertEquals(0, latch.getCount());
    }
}
