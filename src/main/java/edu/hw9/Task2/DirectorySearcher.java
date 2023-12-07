package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectorySearcher extends RecursiveTask<List<Path>> {
    private final Path currentPath;
    private final int minFilesCount;

    public DirectorySearcher(Path currentPath, int minFilesCount) {
        this.currentPath = currentPath;
        this.minFilesCount = minFilesCount;
    }

    @Override
    protected List<Path> compute() {
        List<DirectorySearcher> forks = new ArrayList<>();
        List<Path> result = new ArrayList<>();

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(currentPath)) {

            int filesCounter = 0;

            for (Path path : paths) {
                if (Files.isDirectory(path)) {
                    DirectorySearcher task = new DirectorySearcher(path, minFilesCount);
                    task.fork();
                    forks.add(task);
                } else {
                    filesCounter++;
                }
            }

            if (filesCounter > minFilesCount) {
                result.add(currentPath);
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
