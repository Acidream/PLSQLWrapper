package org.home.sourcereaderwriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.home.sourcereaderwriter.FileUtils.deleteFile;
import static org.home.sourcereaderwriter.FileUtils.writeText;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnotationsReaderTest {

    @BeforeEach
    void setUp() {
        writeText("Test1.java", "import ...\n" +
                "@GenDbProc(\"myproc\")\n" +
                "@GenDbFuncResp(\"func1:res1;func2:res2\")\n" +
                " class Test {  some methods...  \n" +
                "//-------------------GENSTART-------------------\n" +
                "some new content\n" +
                "and very new\n" +
                "//-------------------GENEND-------------------\n" +
                "}");
    }

    @AfterEach
    void tearDown() {
        deleteFile("Test1.java");
    }

    @Test
    void readPackName() {
        AnnotationsReader ar = new AnnotationsReader("Test1.java");
        ar.read();
        assertEquals("myproc", ar.annotations.packageName);
    }

    @Test
    void readFuncType() {
        AnnotationsReader ar = new AnnotationsReader("Test1.java");
        ar.read();
        assertEquals(2, ar.annotations.functionType.size());
    }
}