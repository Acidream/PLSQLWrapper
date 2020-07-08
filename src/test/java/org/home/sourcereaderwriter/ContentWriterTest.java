package org.home.sourcereaderwriter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentWriterTest {

    @Test
    void writeNew() {

        FileUtils.writeText("Test.java", "import ...\n" +
                "@GenProc(\"myproc\")\n" +
                " class Test {  some methods...  }");

        ContentWriter cw = new ContentWriter("Test.java", "some new content\nand very new");
        cw.write();
        String text = FileUtils.readText("Test.java");
        assertEquals("import ...\n" +
                "@GenProc(\"myproc\")\n" +
                " class Test {  some methods...  \n" +
                "//-------------------GENSTART-------------------\n" +
                "some new content\n" +
                "and very new\n" +
                "//-------------------GENEND-------------------\n" +
                "}", text);

    }

    @Test
    void writeExisting() {

        FileUtils.writeText("Test.java", "import ...\n" +
                "@GenProc(\"myproc\")\n" +
                " class Test {  some methods...  \n" +
                "//-------------------GENSTART-------------------\n" +
                "some new content\n" +
                "and very new\n" +
                "//-------------------GENEND-------------------\n" +
                "}");

        ContentWriter cw = new ContentWriter("Test.java", "epically new version\nand very new");
        cw.write();
        String text = FileUtils.readText("Test.java");
        assertEquals("import ...\n" +
                "@GenProc(\"myproc\")\n" +
                " class Test {  some methods...  \n" +
                "//-------------------GENSTART-------------------\n" +
                "epically new version\n" +
                "and very new\n" +
                "//-------------------GENEND-------------------\n" +
                "}", text);

    }


}