import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String gameState = sc.nextLine();

        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.printf("| %c %c %c |%n", gameState.charAt(i), gameState.charAt(i + 1), gameState.charAt(i + 2));
        }
        System.out.println("---------");

        System.out.println(analyzeGame(gameState));
    }

    private static String analyzeGame(String gameState) {

        char[][] grid = new char[3][3];
        int xCount = 0, oCount = 0, underscoreCount = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                char c = gameState.charAt(row * 3 + col);
                grid[row][col] = c;

                switch (c) {
                    case 'X' -> xCount++;
                    case 'O' -> oCount++;
                    case '_' -> underscoreCount++;
                }
            }
        }

        boolean xWins = checkPlayerWins(grid, 'X');
        boolean oWins = checkPlayerWins(grid, 'O');

        if ((xWins && oWins)
                || (Math.abs(xCount - oCount) >= 2 && underscoreCount > 0 && !xWins && !oWins)) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (underscoreCount > 0) {
            return "Game not finished";
        } else {
            return "Draw";
        }
    }

    private static boolean checkPlayerWins(char[][] grid, char player) {

        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) {
                return true;
            }
            if (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player) {
                return true;
            }
        }

        // Check diagonal 1
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
            return true;
        }

        // Check diagonal 2
        if (grid[2][0] == player && grid[1][1] == player && grid[0][2] == player) {
            return true;
        }

        return false;
    }
}
