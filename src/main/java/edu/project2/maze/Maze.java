package edu.project2.maze;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    private static final String NO_SUCH_CELL_MESSAGE = "Такой ячейки не существует";

    public Maze(int height, int width) {
        if (height < 1 || width < 1) {
            throw new IllegalArgumentException("Некорректные размеры лабиринта");
        }

        this.height = height;
        this.width = width;
        this.grid = new Cell[this.height][this.width];
        initGrid();
    }

    private void initGrid() {
        for (int i = 0; i < height; i++) {
            grid[i] = new Cell[width];
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell getCell(int i, int j) {
        if (i < 0 || i > height - 1 || j < 0 || j > width - 1) {
            throw new IllegalArgumentException(NO_SUCH_CELL_MESSAGE);
        }

        return grid[i][j];
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
