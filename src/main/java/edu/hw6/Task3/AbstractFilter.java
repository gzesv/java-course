package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    AbstractFilter REGULAR_FILE = Files::isRegularFile;
    AbstractFilter READABLE = Files::isReadable;
    AbstractFilter WRITABLE = Files::isWritable;

    boolean accept(Path entry) throws IOException;

    default AbstractFilter and(AbstractFilter other) {
        return path -> accept(path) && other.accept(path);
    }

    static AbstractFilter largerThan(long size) {
        return path -> Files.size(path) > size;
    }

    static AbstractFilter smallThan(long size) {
        return path -> Files.size(path) < size;
    }

    static AbstractFilter globMatches(String glob) {
        return path -> FileSystems.getDefault()
            .getPathMatcher("glob:" + glob)
            .matches(path.getFileName());
    }

    static AbstractFilter magicNumber(byte... bytes) {
        return path -> {
            var fileBytes = Files.readAllBytes(path);
            if (bytes.length > fileBytes.length) {
                return false;
            }

            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] != fileBytes[i]) {
                    return false;
                }
            }
            return true;
        };
    }

    static AbstractFilter regexContains(String regex) {
        return path -> path.getFileName().toString().matches(regex);
    }
}
