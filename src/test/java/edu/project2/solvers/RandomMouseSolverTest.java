package edu.project2.solvers;

import edu.project2.generators.BinaryTreeGenerator;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.render.SimpleRenderer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project2.solvers.Utils.fillMaze;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RandomMouseSolverTest {
    @Test
    @DisplayName("RandomMouseSolver Test")
    public void randomMouseSolver_test() {
        BinaryTreeGenerator generator = new BinaryTreeGenerator();
        Solver solver = new RandomMouseSolver();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(4, 4);
        Maze maze = generator.generate(5, 5);
        fillMaze(maze);

        List<Coordinate> solve = solver.solve(maze, start, end);

        assertThat(solve.getLast())
            .isNotNull()
            .isEqualTo(new Coordinate(4, 4));
    }
}
