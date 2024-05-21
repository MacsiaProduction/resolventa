package com.resolventa.solvers;

import com.resolventa.prosol.Problem;
import com.resolventa.prosol.Solution;

public interface Solver {
    Solution solve(Problem problem);
}
