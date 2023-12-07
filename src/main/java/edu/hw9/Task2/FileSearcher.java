package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FileSearcher extends RecursiveTask<List<Path>> {
    private final Path currentPath;
    private final Predicate<Path> predicate;

    public FileSearcher(Path currentPath, Predicate<Path> predicate) {
        this.currentPath = currentPath;
        this.predicate = predicate;
    }

    @Override
    protected List<Path> compute() {
        List<FileSearcher> forks = new ArrayList<>();
        List<Path> result = new ArrayList<>();

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(currentPath)) {
            for (Path path : paths) {
                if (Files.isDirectory(path)) {
                    FileSearcher task = new FileSearcher(path, predicate);
                    task.fork();
                    forks.add(task);
                } else {
                    if (predicate.test(path)) {
                        result.add(path);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (var fork : forks) {
            result.addAll(fork.join());
        }

        return result;
    }
}
