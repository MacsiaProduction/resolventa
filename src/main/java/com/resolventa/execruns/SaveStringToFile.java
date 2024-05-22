package com.resolventa.execruns;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveStringToFile {
    public static void save(String filename, String content) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filename);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
        }
    }
}

