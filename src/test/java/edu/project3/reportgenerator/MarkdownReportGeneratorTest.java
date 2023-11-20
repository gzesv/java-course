package edu.project3.reportgenerator;

import edu.project3.logrecord.LogRecord;
import edu.project3.logreport.LogReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MarkdownReportGeneratorTest {

    @Test
    void generate_test() {
        String log =
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        LogRecord record = new LogRecord(log);
        MarkdownReportGenerator reportGenerator =
            new MarkdownReportGenerator(new LogReport(List.of(record), OffsetDateTime.MIN, OffsetDateTime.MAX),
                OffsetDateTime.MIN,
                OffsetDateTime.MAX
            );

        assertDoesNotThrow(
            reportGenerator::generate
        );
    }
}
