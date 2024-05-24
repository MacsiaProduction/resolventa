package com.resolventa.execruns;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExecFileRunnerTest {

    @Test
    public void testRunSuccessful() throws IOException, InterruptedException, NotZeroExitCodeException {
        ExecFileRunner runner = new ExecFileRunner(Arrays.asList("ping", "localhost"));
        String result = runner.Run();
        assertTrue(result.length() > 0); // Проверяем, что результат не пустой
    }

    @Test
    public void testRunNonZeroExitCode() {
        ExecFileRunner runner = new ExecFileRunner(Arrays.asList("nonexistentcommand"));
        assertThrows(IOException.class, runner::Run); // Проверяем, что выбрасывается исключение
    }
}
