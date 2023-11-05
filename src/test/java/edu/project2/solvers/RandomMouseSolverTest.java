package edu.project2.solvers;

import edu.project2.generators.BinaryTreeGenerator;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.render.SimpleRenderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RandomMouseSolverTest {
    @Test
    @DisplayName("RandomMouseSolver Test")
    public void randomMouseSolver_test() {
        BinaryTreeGenerator generator = new BinaryTreeGenerator();
        Solver solver = new RandomMouseSolver();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(4, 4);
        Maze maze = generator.generate(5, 5);
        Cell[][] grid = maze.getGrid();
        grid[0][1] = new Cell(0, 1, Cell.Type.WALL);
        grid[1][0] = new Cell(1, 0, Cell.Type.WALL);
        grid[2][0] = new Cell(2, 0, Cell.Type.WALL);
        grid[3][0] = new Cell(3, 0, Cell.Type.WALL);
        grid[4][0] = new Cell(4, 0, Cell.Type.WALL);
        grid[1][1] = new Cell(1, 1, Cell.Type.PASSAGE);
        grid[1][2] = new Cell(1, 2, Cell.Type.WALL);
        grid[1][3] = new Cell(1, 3, Cell.Type.PASSAGE);
        grid[1][4] = new Cell(1, 4, Cell.Type.PASSAGE);
        grid[2][1] = new Cell(2, 1, Cell.Type.PASSAGE);
        grid[3][1] = new Cell(3, 1, Cell.Type.PASSAGE);
        grid[3][2] = new Cell(3, 2, Cell.Type.WALL);
        grid[3][3] = new Cell(3, 3, Cell.Type.WALL);
        grid[3][4] = new Cell(3, 4, Cell.Type.WALL);
        grid[4][1] = new Cell(4, 1, Cell.Type.PASSAGE);
        grid[4][2] = new Cell(4, 2, Cell.Type.PASSAGE);
        grid[4][3] = new Cell(4, 3, Cell.Type.PASSAGE);
        grid[4][4] = new Cell(4, 4, Cell.Type.PASSAGE);
        SimpleRenderer sr = new SimpleRenderer();

        List<Coordinate> solve = solver.solve(maze, start, end);

        assertThat(solve).isNotNull();
        assertThat(solve.getLast()).isNotNull().isEqualTo(new Coordinate(4, 4));
    }
}
