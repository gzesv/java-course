package edu.project2.generators;

import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.solvers.DepthFirstSearchSolver;
import edu.project2.solvers.Solver;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BinaryTreeGeneratorTest {
    @Test
    @DisplayName("BinaryTreeGenerator Test")
    public void binaryTreeGenerator_test() {
        BinaryTreeGenerator generator = new BinaryTreeGenerator();
        Solver solver = new DepthFirstSearchSolver();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(9, 9);
        Maze maze = generator.generate(11, 11);

        List<Coordinate> solve = solver.solve(maze, start, end);

        assertThat(solve).isNotNull();
    }
}
