package org.home.sourcereaderwriter;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContentWriter {
    String file;
    String content;

    public ContentWriter(String file, String content) {
        this.file = file;
        this.content = content;
    }

    public void write() {

        writeAll(readAndReplace());
    }

    @SneakyThrows
    private String readAndReplace() {
        StringBuilder stb = new StringBuilder();
        String line = "";
        boolean skip = false;
        boolean startEndFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                if (!skip)
                    stb.append(line).append("\n");

                if (line.trim().startsWith(Annotations.GENSTART_DIVIDER)) {
                    skip = true;
                }

                if (line.trim().startsWith(Annotations.GENEND_DIVIDER)) {
                    skip = false;
                    startEndFound = true;
                    stb.append(content).append("\n");
                    stb.append(line).append("\n");
                }
            }
        }
        if (stb.length() > 0)
            stb.deleteCharAt(stb.length() - 1);

        if (startEndFound)
            return stb.toString();

        String allText = stb.toString();
        return allText.substring(0, allText.lastIndexOf("}")) + "\n" +
                Annotations.GENSTART_DIVIDER + "\n" +
                content + "\n" +
                Annotations.GENEND_DIVIDER + "\n" +
                "}";


    }


    @SneakyThrows
    private void writeAll(String newContent) {
        Path path = Paths.get(file);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(newContent);
        }
    }


}
