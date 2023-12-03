package edu.project3.reportgenerator;

import edu.project3.coderesponse.CodeResponse;
import edu.project3.logreport.LogReport;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class MarkdownReportGenerator implements ReportGenerator {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private OffsetDateTime from;
    private OffsetDateTime to;
    private LogReport logReport;
    private static final String FILE_FORMAT = "####";

    public MarkdownReportGenerator(LogReport logReport, OffsetDateTime from, OffsetDateTime to) {
        this.logReport = logReport;
        this.from = from;
        this.to = to;
    }

    @Override
    public void generate() {
        String fileName = "file";
        Path currentDirectory = Paths.get("src/main/java/edu/project3/").toAbsolutePath();
        Path filePath = currentDirectory.resolve(fileName + ".md");

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath.toFile()))) {
            writeGeneralInfoSection(writer, fileName);
            writeResourceSection(writer);
            writeResponseCodeSection(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeGeneralInfoSection(PrintWriter writer, String fileName) {
        writer.println(FILE_FORMAT + " Общая информация\n");
        writer.println("|        Метрика        |     Значение |");
        writer.println("|:---------------------:|-------------:|");
        writer.println("|         Файл          | " + fileName + " |");
        writer.println("|    Начальная дата     | " + (this.from.isEqual(OffsetDateTime.MIN)
            ? "-" : this.from.format(DATE_TIME_FORMATTER) + " |"));
        writer.println("|     Конечная дата     | " + (this.to.isEqual(OffsetDateTime.MAX)
            ? "- |" : this.to.format(DATE_TIME_FORMATTER) + " |"));
        writer.println("|  Количество запросов  | " + this.logReport.getTotalRequests() + " |");
        writer.println("| Средний размер ответа | " + this.logReport.getAverageResponseSize() + "b |");
        writer.println();
    }

    private void writeResourceSection(PrintWriter writer) {
        writer.println(FILE_FORMAT + " Запрашиваемые ресурсы\n");
        writer.println("|     Ресурс      |         Количество       |");
        writer.println("|:---------------:|-------------------------:|");

        Map<String, Integer> resourceCount = this.logReport.getResourceCount();
        for (Map.Entry<String, Integer> entry : resourceCount.entrySet()) {
            writer.println("|  `" + entry.getKey() + "`  |      " + entry.getValue() + " |");
        }
        writer.println();
    }

    private void writeResponseCodeSection(PrintWriter writer) {
        writer.println(FILE_FORMAT + " Коды ответа\n");
        writer.println("| Код |          Имя          | Количество |");
        writer.println("|:---:|:---------------------:|-----------:|");

        Map<Integer, Integer> responseCodeCount = logReport.getResponseCodeCount();
        for (Map.Entry<Integer, Integer> entry : responseCodeCount.entrySet()) {
            int codeResponse = entry.getKey();
            writer.println("| " + codeResponse + " | " + CodeResponse.getDescriptionByCode(codeResponse)
                + " |       " + entry.getValue() + " |");
        }
    }
}
