package edu.project2.maze;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MazeTest {
    @Test
    @DisplayName("Maze getHeight test")
    public void maze_getHeight_test() {
        Maze maze = new Maze(21, 11);

        int height = maze.getHeight();

        assertThat(height).isEqualTo(21);
    }

    @Test
    @DisplayName("Maze getWidth test")
    public void maze_getWidth_test() {
        Maze maze = new Maze(21, 11);

        int width = maze.getWidth();

        assertThat(width).isEqualTo(11);
    }

    @Test
    @DisplayName("Maze getCell test")

    public void maze_getCell_test() {
        Maze maze = new Maze(21, 11);

        Cell cell = maze.getCell(5, 6);

        assertThat(cell).isNull();
    }

    @Test
    @DisplayName("Maze getGrid test")
    public void maze_getGrid_test() {
        Maze maze = new Maze(2, 2);

        Cell[][] grid = maze.getGrid();

        assertThat(grid).isEqualTo(new Cell[][] {
                new Cell[2],
                new Cell[2]
            }
        );
    }

    @Test
    @DisplayName("Создане лабиринта с некорректными данными")
    public void mazeCreate_incorrectWidth_test() {
        int height = 0;
        int width = -1;

        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Maze(height, width)
        );

        assertThat(exception.getMessage())
            .isEqualTo("Некорректные размеры лабиринта");
    }

    @Test
    @DisplayName("Получение ячейки за пределами лабиринта")
    void mazeGetCellIncorrectHeight_test() {
        int height = 2;
        int width = 2;
        Maze maze = new Maze(height, width);

        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> maze.getCell(100, 1)
        );

        assertThat(exception.getMessage())
            .isEqualTo("Такой ячейки не существует");
    }

}
