package edu.hw8.Task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private int threadsCount;
    private Thread[] threads;
    private BlockingQueue<Runnable> tasks;

    public static FixedThreadPool create(int threadsCount) {
        return new FixedThreadPool(threadsCount);
    }

    private FixedThreadPool(int threadsCount) {
        this.threadsCount = threadsCount;
        this.threads = new Thread[threadsCount];
        this.tasks = new ArrayBlockingQueue<>(threadsCount);
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsCount; ++i) {
            threads[i] = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Runnable task = tasks.take();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            tasks.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
