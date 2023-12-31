package edu.project2.solvers;

import edu.project2.generators.BinaryTreeGenerator;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project2.solvers.Utils.fillMaze;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DepthFirstSearchSolverTest {
    @Test
    @DisplayName("DepthFirstSearchSolver Test")
    public void depthFirstSearchSolver_test() {
        BinaryTreeGenerator generator = new BinaryTreeGenerator();
        Solver solver = new DepthFirstSearchSolver();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(4, 4);
        Maze maze = generator.generate(5, 5);
        fillMaze(maze);

        List<Coordinate> solve = solver.solve(maze, start, end);

        assertThat(solve)
            .isNotNull()
            .isEqualTo(List.of(
            new Coordinate(4,4),
            new Coordinate(4,3),
            new Coordinate(4,2),
            new Coordinate(4,1),
            new Coordinate(3,1),
            new Coordinate(2,1),
            new Coordinate(1,1)
        ));
    }
}
