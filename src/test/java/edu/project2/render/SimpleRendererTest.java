package edu.project2.render;

import edu.project2.generators.BinaryTreeGenerator;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.solvers.DepthFirstSearchSolver;
import edu.project2.solvers.Solver;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleRendererTest {
    @Test
    @DisplayName("SimpleRenderer with Path Test")
    public void simpleRenderer_withPath_test() {
        SimpleRenderer renderer = new SimpleRenderer();
        Maze maze = getTestMaze();
        List<Coordinate> path = getTestPath(maze);

        String output = renderer.render(maze, path);

        assertThat(output)
            .isNotNull()
            .isEqualTo("█████\n" +
                "█\u001B[32m•\u001B[0m   \n" +
                "█\u001B[32m•\u001B[0m███\n" +
                "█\u001B[32m•\u001B[0m█  \n" +
                "█\u001B[32m•\u001B[0m\u001B[32m•\u001B[0m\u001B[32m•\u001B[0m\u001B[32m•\u001B[0m\n");

    }

    @Test
    @DisplayName("SimpleRenderer Test")
    public void simpleRenderer_test() {
        SimpleRenderer renderer = new SimpleRenderer();
        Maze maze = getTestMaze();

        String output = renderer.render(maze);

        assertThat(output)
            .isNotNull()
            .isEqualTo("█████\n" +
                "█    \n" +
                "█ ███\n" +
                "█ █  \n" +
                "█    \n");

    }

    @Test
    @DisplayName("Null-лабиринт.")
    void simpleRenderer_nullMaze_test() {
        SimpleRenderer simpleRenderer = new SimpleRenderer();
        Maze maze = null;
        List<Coordinate> path = List.of();

        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> simpleRenderer.render(maze, path)
        );

        assertThat(exception).isNotNull().isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Лабиринт не может быть null!");
    }

    @Test
    @DisplayName("Отрисовка лабиринта и пути в нем. Null-путь.")
    void simpleRendererNullPath() {
        SimpleRenderer simpleRenderer = new SimpleRenderer();
        Maze maze = new Maze(5, 10);
        List<Coordinate> path = null;

        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> simpleRenderer.render(maze, path)
        );

        assertThat(exception).isNotNull().isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Путь не может быть null!");
    }

    private Maze getTestMaze() {
        BinaryTreeGenerator generator = new BinaryTreeGenerator();

        Maze maze = generator.generate(5, 5);
        Cell[][] grid = maze.getGrid();
        grid[1][1] = new Cell(1, 1, Cell.Type.PASSAGE);
        grid[1][2] = new Cell(1, 2, Cell.Type.PASSAGE);
        grid[1][3] = new Cell(1, 3, Cell.Type.PASSAGE);
        grid[1][4] = new Cell(1, 4, Cell.Type.PASSAGE);
        grid[2][1] = new Cell(2, 1, Cell.Type.PASSAGE);
        grid[2][3] = new Cell(2, 3, Cell.Type.WALL);
        grid[3][1] = new Cell(3, 1, Cell.Type.PASSAGE);
        grid[3][2] = new Cell(3, 2, Cell.Type.WALL);
        grid[4][1] = new Cell(4, 1, Cell.Type.PASSAGE);
        grid[4][2] = new Cell(4, 2, Cell.Type.PASSAGE);
        grid[4][3] = new Cell(4, 3, Cell.Type.PASSAGE);
        grid[4][4] = new Cell(4, 4, Cell.Type.PASSAGE);
        grid[3][4] = new Cell(3, 4, Cell.Type.PASSAGE);
        return maze;
    }

    private List<Coordinate> getTestPath(Maze maze) {
        Solver solver = new DepthFirstSearchSolver();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(4, 4);

        return solver.solve(maze, start, end);
    }
}
