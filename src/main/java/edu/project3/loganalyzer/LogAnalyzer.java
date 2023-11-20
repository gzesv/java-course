package edu.project3.loganalyzer;

import edu.project3.fileformat.FileFormat;
import edu.project3.logloader.FileLogLoader;
import edu.project3.logloader.HttpLogLoader;
import edu.project3.logloader.LogLoader;
import edu.project3.logrecord.LogRecord;
import edu.project3.logreport.LogReport;
import edu.project3.reportgenerator.AdocReportGenerator;
import edu.project3.reportgenerator.MarkdownReportGenerator;
import edu.project3.reportgenerator.ReportGenerator;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LogAnalyzer {
    private String path;
    private OffsetDateTime from = OffsetDateTime.MIN;
    private OffsetDateTime to = OffsetDateTime.MAX;
    private FileFormat format;
    LogLoader logLoader;
    ReportGenerator reportGenerator;

    public void run(String[] args) {
        parseArgs(args);

        if (isValidUrl()) {
            logLoader = new HttpLogLoader(path);
        } else {
            logLoader = new FileLogLoader(Path.of(path));
        }

        logLoader.readLogs();

        List<String> logs = logLoader.getRecords();
        List<LogRecord> logRecords = logs.stream()
            .map(LogRecord::new)
            .toList();

        LogReport logReport = new LogReport(logRecords, from, to);

        if (format == FileFormat.ADOC) {
            reportGenerator = new AdocReportGenerator(logReport, from, to);
        } else {
            reportGenerator = new MarkdownReportGenerator(logReport, from, to);
        }

        reportGenerator.generate();
    }

    private void parseArgs(String[] args) {
        String command = String.join(" ", args);
        if (command.contains("--path")) {
            this.path = (findCommand(command, "--path "));
        }

        if (command.contains("--from")) {
            setFrom(findCommand(command, "--from "));
        }

        if (command.contains("--to")) {
            setTo(findCommand(command, "--to "));
        }

        if (command.contains("--format adoc")) {
            format = FileFormat.ADOC;
        } else {
            format = FileFormat.MARKDOWN;
        }
    }

    private static String findCommand(String line, String command) {
        int startIndex = line.indexOf(command) + command.length();
        String substring = line.substring(startIndex);
        int endIndex = substring.indexOf(' ');

        return line.substring(startIndex, startIndex + endIndex).trim();
    }

    private void setFrom(String stringFrom) {
        this.from = parseDateString(stringFrom).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
    }

    public void setTo(String stringTo) {
        this.to = parseDateString(stringTo).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
    }

    private LocalDate parseDateString(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private boolean isValidUrl() {
        try {
            new URL(path);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
