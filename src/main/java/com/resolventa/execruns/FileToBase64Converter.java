package com.resolventa.execruns;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FileToBase64Converter {
    public static String convert(String pdfFilePath) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Paths.get(pdfFilePath));
        return Base64.getEncoder().encodeToString(fileBytes);
    }
}

