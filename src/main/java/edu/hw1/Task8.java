package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task8 {
    private static final int BOARD_SIZE = 8;

    @SuppressWarnings("MagicNumber")
    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    continue;
                }

                if (horseCanCapture(i + 1, j - 2, board)
                    || horseCanCapture(i + 1, j + 2, board)
                    || horseCanCapture(i - 1, j - 2, board)
                    || horseCanCapture(i - 1, j + 2, board)
                    || horseCanCapture(i + 2, j - 1, board)
                    || horseCanCapture(i + 2, j + 1, board)
                    || horseCanCapture(i - 2, j - 1, board)
                    || horseCanCapture(i - 2, j + 1, board)) {
                    return false;
                }
            }
        }

        return true;
    }

    @SuppressWarnings("MagicNumber")
    private static boolean horseCanCapture(int x, int y, int[][] board) {
        return (positionOnBoard(x, y) && board[x][y] == 1);
    }

    @SuppressWarnings("MagicNumber")
    private static boolean positionOnBoard(int x, int y) {
        return (x >= 0 && x < BOARD_SIZE && y >= 0 & y < BOARD_SIZE);
    }
}
