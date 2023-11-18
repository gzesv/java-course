package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressWarnings("HideUtilityClassConstructor")
public class FileCloner {

    private static final String FILE_NAME_SEPARATOR = "\\.";
    private static final String COPY_SUFFIX = " — копия";

    public static void cloneFile(Path filePath) {
        String[] fullFileName = filePath.getFileName().toString().split(FILE_NAME_SEPARATOR);
        String name = fullFileName[0];
        String extension = fullFileName[1];

        String copyFileName = name + COPY_SUFFIX + "." + extension;
        Path directory = filePath.getParent();
        Path copyFilePath = directory.resolve(copyFileName);

        int copyNumber = 1;
        while (Files.exists(copyFilePath)) {
            copyNumber++;
            copyFileName = name + COPY_SUFFIX + " (" + copyNumber + ")." + extension;
            copyFilePath = directory.resolve(copyFileName);
        }

        try {
            Files.copy(filePath, copyFilePath);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
