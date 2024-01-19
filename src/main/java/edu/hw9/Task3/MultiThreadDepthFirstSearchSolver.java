package edu.hw9.Task3;

import edu.project2.Utils.Move;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.solvers.Solver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MultiThreadDepthFirstSearchSolver implements Solver {
    private static final List<Move> MOVES = List.of(
        new Move(1, 0),
        new Move(0, 1),
        new Move(-1, 0),
        new Move(0, -1)
    );

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        RecursiveSolver recursiveSolver =
            new RecursiveSolver(maze.getCell(start.row(), start.col()),
                maze, start, end, new ArrayList<>(List.of())
            );
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(recursiveSolver);
        }
    }

    private static class RecursiveSolver extends RecursiveTask<List<Coordinate>> {
        private final Cell currCell;
        private final Maze maze;
        private final Coordinate start;
        private final Coordinate end;
        private final List<Coordinate> visitedCells;

        private RecursiveSolver(
            Cell currCell, Maze maze, Coordinate start,
            Coordinate end, List<Coordinate> visitedCells
        ) {
            this.currCell = currCell;
            this.maze = maze;
            this.start = start;
            this.end = end;
            this.visitedCells = visitedCells;
        }

        @Override
        protected List<Coordinate> compute() {
            if (currCell.col() == end.col() && currCell.row() == end.row()) {
                return List.of(new Coordinate(currCell.row(), currCell.col()));
            }

            boolean canDoNextStep = false;
            List<RecursiveSolver> recursiveSolvers = new ArrayList<>();
            visitedCells.add(new Coordinate(currCell.row(), currCell.col()));

            for (Move move : MOVES) {
                int newRow = currCell.row() + move.x();
                int newCol = currCell.col() + move.y();

                if (isInside(newRow, newCol, maze.getHeight(), maze.getWidth())) {
                    if (!visitedCells.contains(new Coordinate(newRow, newCol))) {
                        canDoNextStep = true;
                        RecursiveSolver recursiveSolver =
                            new RecursiveSolver(maze.getCell(newRow, newCol), maze, start, end, visitedCells);
                        recursiveSolver.fork();
                        recursiveSolvers.add(recursiveSolver);
                    }
                }
            }

            if (!canDoNextStep) {
                return List.of();
            }

            List<Coordinate> currPath = new ArrayList<>(recursiveSolvers.stream()
                .map(ForkJoinTask::join)
                .flatMap(Collection::stream)
                .toList());

            if (!currPath.isEmpty()) {
                currPath.add(new Coordinate(currCell.row(), currCell.col()));
            }

            return currPath;
        }

        private boolean isInside(int row, int col, int height, int width) {
            return row >= 0 && row < height && col >= 0 && col < width;
        }
    }
}
