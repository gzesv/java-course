package edu.project3.logrecord;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LogRecordTest {
    private static final DateTimeFormatter DATA_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

    @Test
    void logRecord_test() {
        String log =
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";

        LogRecord record = new LogRecord(log);

        assertAll(
            () -> assertEquals("93.180.71.3", record.getRemoteAddress()),
            () -> assertEquals("-", record.getRemoteUser()),
            () -> assertEquals("GET", record.getRequest()),
            () -> assertEquals(
                OffsetDateTime.parse("17/May/2015:08:05:32 +0000", DATA_FORMATTER),
                record.getTimeLocal()
            ),
            () -> assertEquals("/downloads/product_1 HTTP/1.1", record.getSource()),
            () -> assertEquals(304, record.getStatus()),
            () -> assertEquals("Not Modified", record.getCodeResponse()),
            () -> assertEquals(0, record.getBodyBytesSent()),
            () -> assertEquals("-", record.getHttpReferer()),
            () -> assertEquals("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", record.getHttpUserAgent())
        );
    }

    @Test
    void logRecord_incorrectLog_test() {
        String log = "log";

        assertThrows(
            IllegalArgumentException.class,
            () -> new LogRecord(log)
        );

    }
}
