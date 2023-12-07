package edu.hw9.Task1;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

class StatsCollectorTest {
    private static final int THREADS_COUNT = 2;
    private StatsCollector statsCollector = new StatsCollector(THREADS_COUNT);

    @Test
    void statsCollector_test() throws InterruptedException {
        String metricName = "metric_name";
        double[] values = new double[] {0.1, 0.05, 1.4, 5.1, 0.3};
        Metric expected = new Metric(
            "metric_name", 7.0, 1.0, 5.1, 0.05
        );

        statsCollector.addMetric(metricName, values);
        sleep(10);

        List<Metric> result = statsCollector.getStats();

        assertThat(result).hasSize(1);
        Assertions.assertAll(
            () -> assertThat(result.getLast().name()).isEqualTo(expected.name()),
            () -> assertThat(result.getLast().sum()).isEqualTo(expected.sum()),
            () -> assertThat(result.getLast().average()).isEqualTo(expected.average()),
            () -> assertThat(result.getLast().max()).isEqualTo(expected.max()),
            () -> assertThat(result.getLast().min()).isEqualTo(expected.min())
        );
    }
}
