package org.home.sourcereaderwriter;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    @SneakyThrows
    static void writeText(String file, String newContent) {
        Path path = Paths.get(file);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(newContent);
        }
    }

    @SneakyThrows
    static String readText(String file) {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    @SneakyThrows
    static void deleteFile(String file) {
        Files.delete(Paths.get(file));
    }


}
