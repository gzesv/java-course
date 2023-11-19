package edu.project3.reportgenerator;

import edu.project3.logreport.LogReport;
import java.time.OffsetDateTime;

public interface ReportGenerator {
    void generate(LogReport logReport, OffsetDateTime f, OffsetDateTime t);
}
