package edu.project3.logloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileLogLoader {
    private final Path path;
    private List<String> records = new ArrayList<>();

    public FileLogLoader(Path path) {
        this.path = path;
    }

    public void readLogs() {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            records.addAll(reader.lines().toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getRecords() {
        return records;
    }
}
