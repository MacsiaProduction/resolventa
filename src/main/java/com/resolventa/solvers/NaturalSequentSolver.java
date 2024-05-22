package com.resolventa.solvers;

import com.resolventa.execruns.ExecFileRunner;
import com.resolventa.execruns.FileToBase64Converter;
import com.resolventa.execruns.NotZeroExitCodeException;
import com.resolventa.execruns.SaveStringToFile;
import com.resolventa.prosol.Problem;
import com.resolventa.prosol.Solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NaturalSequentSolver implements Solver{
    private static final String ERROR_TYPE = "error_type";
    @Override
    public Solution solve(Problem problem) {
        Solution res;

        final List<String> command = new ArrayList<>();
        command.add("./raw/wang");
        command.add("-s");
        command.add(problem.problem_content);
        command.add("-b");
        command.add("LaTeX");

        ExecFileRunner runner = new ExecFileRunner(command);
        String latex_code, pdf_base64;

        try {
            latex_code = wrap_latex(runner.Run());
            SaveStringToFile.save("./raw/res.tex", latex_code);

            command.clear();
            command.add("bash");
            command.add("-c");
            command.add("cd raw/ && pdflatex res.tex");
            runner.Run();

            pdf_base64 = FileToBase64Converter.convert("./raw/res.pdf");
        } catch (IOException | InterruptedException | NotZeroExitCodeException e) {
            res = new Solution(ERROR_TYPE, e.getMessage());
            return res;
        }

        res = new Solution(problem.problem_type, pdf_base64);
        return res;
    }

    private String wrap_latex(String latex){
        return "\\documentclass{article}\n" +
                "\\usepackage{bussproofs}\n" +
                "\\usepackage{amsmath}\n" +
                "\\begin{document}\n" +
                latex +
                "\\end{document}";
    }
}
