package org.home.sourcereaderwriter;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;

public class AnnotationsReader {
    String file;
    @Getter
    Annotations annotations;

    public AnnotationsReader(String file) {
        this.file = file;
    }

    @SneakyThrows
    public void read() {
        annotations = new Annotations();

        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {

                if (line.trim().startsWith(Annotations.PROC_ANNOTATION + " ") || line.trim().startsWith(Annotations.PROC_ANNOTATION + "(")) {
                    String procLine = line.trim();
                    procLine = procLine.replace(Annotations.PROC_ANNOTATION, "");
                    procLine = procLine.replace("\"", "");
                    procLine = procLine.replace("(", "");
                    procLine = procLine.replace(")", "").trim();
                    if (procLine.length() == 0)
                        throw new RuntimeException("File " + file + " has proc annotation but no proc defined");
                    annotations.packageName = procLine;
                }

                if (line.trim().startsWith(Annotations.FUNCRESP_ANNOTATION)) {
                    String funcLine = line.trim();
                    funcLine = funcLine.replace(Annotations.FUNCRESP_ANNOTATION, "");
                    funcLine = funcLine.replace("\"", "");
                    funcLine = funcLine.replace("(", "");
                    funcLine = funcLine.replace(")", "").trim();
                    if (funcLine.length() == 0)
                        continue;
                    String[] funcResArr = funcLine.split(";");
                    if (funcResArr.length == 0)
                        continue;
                    for (String funcRes : funcResArr) {
                        String[] split = funcRes.split(":");
                        if (split.length < 2)
                            continue;
                        annotations.functionType.put(split[0], split[1]);
                    }
                }
            }
        }
    }


}
