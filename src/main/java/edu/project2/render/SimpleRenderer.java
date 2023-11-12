package edu.project2.render;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;

public class SimpleRenderer implements Renderer {

    private static final String PATH_POINT = "\u001B[32m•\033[0m";

    @Override
    public String render(Maze maze) {
        return render(maze, List.of());
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        if (maze == null) {
            throw new IllegalArgumentException("Лабиринт не может быть null");
        }

        if (path == null) {
            throw new IllegalArgumentException("Путь не может быть null");
        }

        StringBuilder stringBuilder = new StringBuilder();
        Cell[][] grid = maze.getGrid();
        int height = maze.getHeight();
        int width = maze.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                stringBuilder.append(getSymbolByCoord(grid, i, j));
            }
            stringBuilder.append("\n");
        }

        for (Coordinate coord : path) {
            int position = coord.col() + coord.row() * (width + 1);
            stringBuilder.replace(position, position + 1, "•");
        }

        return stringBuilder.toString().replaceAll("•", PATH_POINT);
    }

    private String getSymbolByCoord(Cell[][] grid, int i, int j) {
        if (isPassage(grid[i][j])) {
            return " ";
        }

        return "█";
    }

    private boolean isPassage(Cell cell) {
        return cell.type().equals(Cell.Type.PASSAGE);
    }
}
