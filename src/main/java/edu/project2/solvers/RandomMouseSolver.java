package edu.project2.solvers;

import edu.project2.Utils.Move;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static edu.project2.maze.Cell.Type.PASSAGE;

public class RandomMouseSolver implements Solver {

    private final ArrayList<Move> moves = new ArrayList<>(List.of(
        new Move(1, 0),
        new Move(0, 1),
        new Move(-1, 0),
        new Move(0, -1)
    ));

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Random random = new Random();
        Cell[][] grid = maze.getGrid();

        Cell startCell = maze.getCell(start.row(), start.col());
        Cell endCell = maze.getCell(end.row(), end.col());

        Cell currentCell = startCell;
        path.add(new Coordinate(startCell.row(), startCell.col()));
        while (!currentCell.equals(endCell)) {
            Move move = moves.get(random.nextInt(moves.size()));
            int newRow = currentCell.row() + move.x();
            int newCol = currentCell.col() + move.y();

            if (isInside(newRow, newCol, grid.length, grid[0].length)
                && grid[newRow][newCol].type() == PASSAGE) {

                currentCell = maze.getCell(newRow, newCol);
                path.add(new Coordinate(currentCell.row(), currentCell.col()));
            }
        }
        return path;
    }

    private boolean isInside(int row, int col, int height, int width) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }
}
