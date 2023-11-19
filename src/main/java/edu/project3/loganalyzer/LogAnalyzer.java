package edu.project3.loganalyzer;

import edu.project3.fileformat.FileFormat;
import edu.project3.logloader.FileLogLoader;
import edu.project3.logrecord.LogRecord;
import edu.project3.logreport.LogReport;
import edu.project3.reportgenerator.MarkdownReportGenerator;
import edu.project3.reportgenerator.ReportGenerator;
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
    FileLogLoader logLoader;
    ReportGenerator reportGenerator;

    public void run(String[] args) {
        parseArgs(args);
        logLoader = new FileLogLoader(Path.of(path));
        logLoader.readLogs();

        List<String> logs = logLoader.getRecords();
        List<LogRecord> logRecords = logs.stream()
            .map(LogRecord::new)
            .toList();

        LogReport logReport = new LogReport(logRecords, from, to);

        reportGenerator = new MarkdownReportGenerator();

        reportGenerator.generate(logReport, from, to);
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
}
