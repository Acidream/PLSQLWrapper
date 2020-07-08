package org.home.sourcereaderwriter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileSearcherTest {

    @Test
    void search() {
        FileUtils.writeText("Test.java", "import ...\n" +
                "@GenDbProc(\"myproc\")\n" +
                " class Test {  some methods...  \n" +
                "//-------------------GENSTART-------------------\n" +
                "some new content\n" +
                "and very new\n" +
                "//-------------------GENEND-------------------\n" +
                "}");

        FileUtils.writeText("Test1.java", "import ...\n" +
                "@GenDbProc(\"myproc\")\n" +
                " class Test {  some methods...  \n" +
                "//-------------------GENSTART-------------------\n" +
                "some new content\n" +
                "and very new\n" +
                "//-------------------GENEND-------------------\n" +
                "}");
        FileSearcher fs = new FileSearcher("../");
        fs.search();
        assertEquals(2, fs.filesWithAnnotation.size());
    }
}