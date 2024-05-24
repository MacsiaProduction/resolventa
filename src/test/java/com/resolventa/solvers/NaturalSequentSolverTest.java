package com.resolventa.solvers;

import com.resolventa.prosol.Problem;
import com.resolventa.prosol.Solution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NaturalSequentSolverTest {

    @Test
    public void testSolve() {
        Problem problem = new Problem("natural_sequent_type", "A -> B");
        NaturalSequentSolver solver = new NaturalSequentSolver();
        Solution solution = solver.solve(problem);

        assertNotNull(solution);
        assertTrue(solution.solution_content.length() > 0);
    }
}
