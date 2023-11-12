package edu.project2.solvers;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;

public class Utils {
    public static void fillMaze(Maze maze){
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
    }
}
