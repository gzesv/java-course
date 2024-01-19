package edu.hw9.Task3;

import edu.project2.generators.BinaryTreeGenerator;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.solvers.Solver;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MultiThreadDepthFirstSearchSolverTest {
    @Test
    public void multiThreadDepthFirstSearchSolver_test() {
        BinaryTreeGenerator generator = new BinaryTreeGenerator();
        Solver solver = new MultiThreadDepthFirstSearchSolver();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(15, 15);
        Maze maze = generator.generate(17, 17);

        List<Coordinate> solve = solver.solve(maze, start, end);

        assertThat(solve).isNotNull();
    }
}
