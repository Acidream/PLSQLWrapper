package org.home.sourcereaderwriter;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher {

    String sourcesDir;
    List<String> filesWithAnnotation = new ArrayList<>();

    public FileSearcher(String sourcesDir) {
        this.sourcesDir = sourcesDir;
    }

    @SneakyThrows
    public void search() {
        Files.walk(Paths.get(sourcesDir))
                .filter(Files::isRegularFile)
                .forEach((f) -> {
                    String file = f.toString();
                    if (file.endsWith(".java") && hasAnnotation(file))
                        filesWithAnnotation.add(file);
                });

    }

    @SneakyThrows
    private boolean hasAnnotation(String filePath) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith(Annotations.PROC_ANNOTATION))
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}


