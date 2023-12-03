package edu.project3.logreport;

import edu.project3.logrecord.LogRecord;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LogReportTest {
    private static final DateTimeFormatter DATA_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

    @Test
    void logReport_test() {
        List<String> logString = List.of(
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
            "93.180.71.3 - - [17/May/2017:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
        );
        List<LogRecord> records = logString.stream().map(LogRecord::new).toList();
        OffsetDateTime from = OffsetDateTime.parse("17/May/2014:08:05:32 +0000", DATA_FORMATTER);
        OffsetDateTime to = OffsetDateTime.parse("17/May/2016:08:05:32 +0000", DATA_FORMATTER);

        LogReport report = new LogReport(records, from, to);

        assertEquals(1, report.getTotalRequests());
        assertEquals(Map.of("/downloads/product_1 HTTP/1.1", 1), report.getResourceCount());
        assertEquals(0, report.getAverageResponseSize());
        assertEquals(Map.of(304, 1), report.getResponseCodeCount());
    }
}
