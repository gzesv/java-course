package edu.hw9.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector {
    private ExecutorService executorService;
    private Queue<Metric> metrics = new ConcurrentLinkedQueue<>();

    public StatsCollector(int threadsCount) {
        executorService = Executors.newFixedThreadPool(threadsCount);
    }

    public void addMetric(String metricName, double[] values) {
        executorService.execute(
            putMetric(metricName, values)
        );
    }

    public List<Metric> getStats() {
        return new ArrayList<>(metrics);
    }

    private Runnable putMetric(String name, double[] values) {
        return () -> {
            double sum = 0.0;
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;

            for (double value : values) {
                if (value < min) {
                    min = value;
                }

                if (value > max) {
                    max = value;
                }

                sum += value;
            }

            metrics.add(new Metric(
                name,
                Math.round(sum),
                Math.round(sum / values.length),
                max,
                min
            ));
        };
    }
}
