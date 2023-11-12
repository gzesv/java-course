package edu.project2.solvers;

import edu.project2.Utils.Move;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.ArrayList;
import java.util.List;
import static edu.project2.maze.Cell.Type.PASSAGE;

public class DepthFirstSearchSolver implements Solver {

    private final List<Move> moves = List.of(
        new Move(1, 0),
        new Move(0, 1),
        new Move(-1, 0),
        new Move(0, -1)
    );
    private boolean[][] visits;
    private boolean isSolutionFound = false;
    private final List<Coordinate> solution = new ArrayList<>();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        visits = new boolean[maze.getGrid().length][maze.getGrid()[0].length];

        dfs(start, maze.getGrid(), end);

        return solution;
    }

    @SuppressWarnings("ReturnCount")
    private void dfs(Coordinate coordinate, Cell[][] grid, Coordinate finish) {
        if (visits[coordinate.row()][coordinate.col()]) {
            return;
        }

        if (coordinate.equals(finish)) {
            isSolutionFound = true;
            solution.add(coordinate);
            return;
        }

        visits[coordinate.row()][coordinate.col()] = true;

        for (Move move : this.moves) {
            int newRow = coordinate.row() + move.x();
            int newCol = coordinate.col() + move.y();
            if (isInside(newRow, newCol, grid.length, grid[0].length)
                && grid[newRow][newCol].type() == PASSAGE) {

                dfs(new Coordinate(newRow, newCol), grid, finish);

                if (isSolutionFound) {
                    solution.add(coordinate);
                    return;
                }
            }
        }
    }

    private boolean isInside(int row, int col, int height, int width) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }
}
