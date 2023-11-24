package edu.project3.logreport;

import edu.project3.logrecord.LogRecord;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogReport {
    private final OffsetDateTime from;
    private final OffsetDateTime to;
    private final List<LogRecord> logRecords;
    private final Map<String, Integer> resourceCount = new HashMap<>();
    private final Map<Integer, Integer> responseCodeCount = new HashMap<>();
    private long totalRequests = 0;
    private long totalResponseSize = 0;

    public LogReport(
        List<LogRecord> logReport,
        final OffsetDateTime from,
        final OffsetDateTime to
    ) {
        this.logRecords = logReport;
        this.from = from;
        this.to = to;
        analyzeLogs();
    }

    private void analyzeLogs() {
        for (LogRecord logRecord : logRecords) {
            if (checkBounds(logRecord)) {
                totalRequests++;

                String resource = logRecord.getSource();
                resourceCount.put(
                    resource,
                    resourceCount.getOrDefault(resource, 0) + 1
                );

                int responseCode = logRecord.getStatus();
                responseCodeCount.put(
                    responseCode,
                    responseCodeCount.getOrDefault(responseCode, 0) + 1
                );

                totalResponseSize += logRecord.getBodyBytesSent();
            }
        }
    }

    public long getTotalRequests() {
        return totalRequests;
    }

    public Map<String, Integer> getResourceCount() {
        return resourceCount;
    }

    public int getAverageResponseSize() {
        return (int) (totalResponseSize / (double) totalRequests);
    }

    public Map<Integer, Integer> getResponseCodeCount() {
        return responseCodeCount;
    }

    private boolean checkBounds(final LogRecord logRecord) {
        return logRecord.getTimeLocal().isAfter(from)
            && logRecord.getTimeLocal().isBefore(to);
    }
}
