package edu.project3.logloader;

import java.util.List;

public interface LogLoader {
    void readLogs();

    List<String> getRecords();
}
