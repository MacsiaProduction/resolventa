package com.resolventa.execruns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ExecFileRunner {
    private static final String EMPTY_STRING = "";
    private final List<String> command;

    public ExecFileRunner(List<String> command) {
        this.command = command;
    }

    public String Run() throws IOException, InterruptedException, NotZeroExitCodeException {
        StringBuilder res = new StringBuilder(EMPTY_STRING);
        ProcessBuilder pb = new ProcessBuilder(command);

        Process process = pb.start();

        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            res.append(line).append("\n");
        }

        int exitCode = process.waitFor();
        if(exitCode != 0)
            throw new NotZeroExitCodeException("Something is wrong with your exec file...");

        return res.toString();
    }
}

