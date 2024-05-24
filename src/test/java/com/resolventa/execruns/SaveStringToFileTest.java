package com.resolventa.execruns;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveStringToFileTest {

    @Test
    public void testSave() throws IOException {
        String filename = "./test.txt";
        String content = "This is a test";

        SaveStringToFile.save(filename, content);
        String savedContent = new String(Files.readAllBytes(Paths.get(filename)));

        assertEquals(content, savedContent);

        Files.delete(Paths.get(filename));
    }
}
