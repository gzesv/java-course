package edu.project2.solvers;

import edu.project2.Utils.Move;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import static edu.project2.maze.Cell.Type.PASSAGE;

public class BreadthFirstSearchSolver implements Solver {

    private final List<Move> moves = List.of(
        new Move(1, 0),
        new Move(0, 1),
        new Move(-1, 0),
        new Move(0, -1)
    );

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        boolean[][] visits = new boolean[maze.getGrid().length][maze.getGrid()[0].length];

        Cell startCell = maze.getCell(start.row(), start.col());
        Cell endCell = maze.getCell(end.row(), end.col());
        maze.getGrid();

        var grid = maze.getGrid();

        Map<Cell, Cell> pathMap = new HashMap<>();
        Queue<Cell> cellQueue = new LinkedList<>();
        cellQueue.add(startCell);

        while (!cellQueue.isEmpty()) {
            Cell currentCell = cellQueue.poll();

            if (currentCell == endCell) {
                break;
            }

            for (Move move : this.moves) {
                int newRow = currentCell.row() + move.x();
                int newCol = currentCell.col() + move.y();
                if (isInside(newRow, newCol, grid.length, grid[0].length)
                    && grid[newRow][newCol].type() == PASSAGE
                    && !visits[newRow][newCol]) {

                    pathMap.put(maze.getCell(newRow, newCol), currentCell);

                    visits[newRow][newCol] = true;

                    cellQueue.add(maze.getCell(newRow, newCol));
                }

            }

        }

        List<Coordinate> path = new ArrayList<>();

        Cell currentCell = endCell;
        while (currentCell != startCell) {
            path.add(new Coordinate(currentCell.row(), currentCell.col()));
            currentCell = pathMap.get(currentCell);
        }
        path.add(new Coordinate(startCell.row(), startCell.col()));

        return path;
    }

    private boolean isInside(int row, int col, int height, int width) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

}
