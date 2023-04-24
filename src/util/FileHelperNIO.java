package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileHelperNIO {
    public static List<String> getFileLines(String filename) {
        try {
            return Files.readAllLines(Path.of(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeLinesToFile(List<String> linesToWrite, String filename) {
        Path path = Path.of(filename);
        try {
            Files.write(path, linesToWrite, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
