package com.resolventa.execruns;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileToBase64ConverterTest {

    @Test
    public void testConvert() throws IOException {
        String testFilePath = "./test.pdf";
        byte[] testBytes = "This is a test".getBytes();
        Files.write(Paths.get(testFilePath), testBytes);

        String encoded = FileToBase64Converter.convert(testFilePath);
        String expected = java.util.Base64.getEncoder().encodeToString(testBytes);

        assertEquals(expected, encoded);

        Files.delete(Paths.get(testFilePath));
    }
}
