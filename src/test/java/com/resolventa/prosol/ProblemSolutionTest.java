package com.resolventa.prosol;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProblemSolutionTest {

    @Test
    public void testProblemSerialization() {
        Problem problem = new Problem("natural_sequent_type", "[(p->q)&(p->r)] |- [p->(q&r)]");
        Gson gson = new Gson();
        String json = gson.toJson(problem);

        Problem deserializedProblem = gson.fromJson(json, Problem.class);
        assertEquals(problem.problem_type, deserializedProblem.problem_type);
        assertEquals(problem.problem_content, deserializedProblem.problem_content);
    }

    @Test
    public void testSolutionSerialization() {
        Solution solution = new Solution("natural_sequent_type", "solution_content");
        Gson gson = new Gson();
        String json = gson.toJson(solution);

        Solution deserializedSolution = gson.fromJson(json, Solution.class);
        assertEquals(solution.solution_type, deserializedSolution.solution_type);
        assertEquals(solution.solution_content, deserializedSolution.solution_content);
    }

    @Test
    public void testInvalidJson() {
        Gson gson = new Gson();
        assertThrows(JsonSyntaxException.class, () -> {
            gson.fromJson("invalid json", Problem.class);
        });
    }
}
