package edu.project2.generators;

import edu.project2.Utils.Move;
import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import java.util.List;
import java.util.Random;
import static edu.project2.maze.Cell.Type.PASSAGE;
import static edu.project2.maze.Cell.Type.WALL;

public class BinaryTreeGenerator implements Generator {
    private final List<Move> moves = List.of(
        new Move(1, 0),
        new Move(0, 1)
    );

    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        Cell[][] grid = maze.getGrid();
        int cellRow;
        int cellCol;
        Move move;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j, WALL);
            }
        }

        for (int i = 1; i < height; i += 2) {
            for (int j = 1; j < width; j += 2) {
                if (i == height - 2 && j == width - 2) {
                    grid[i][j] = new Cell(i, j, PASSAGE);
                    break;
                } else if (i == height - 2) {
                    move = this.moves.getLast();
                } else if (j == width - 2) {
                    move = this.moves.getFirst();
                } else {
                    move = this.moves.get(random.nextInt(this.moves.size()));
                }

                cellRow = i + move.x();
                cellCol = j + move.y();
                grid[i][j] = new Cell(i, j, PASSAGE);
                grid[cellRow][cellCol] = new Cell(cellRow, cellCol, PASSAGE);
            }
        }

        return maze;
    }
}
