import java.util.Scanner;

public class TicTacToe {

    private static final String X_WIN = "X wins";
    private static final String O_WIN = "O wins";
    private static final String DRAW = "Draw";
    private static final String GAME_IN_PROGRESS = "Game not finished";
    private static final String INVALID_STATE = "Impossible";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char[][] grid = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };

        printGrid(grid);

        int playerTurn = 1;
        String gameStatus;

        while (true) {
            char currPlayer = playerTurn % 2 == 1 ? 'X' : 'O';
            processPlayerMove(grid, sc, currPlayer);
            gameStatus = analyzeGame(grid);
            printGrid(grid);
            if (gameStatus.equals(X_WIN) || gameStatus.equals(O_WIN) || gameStatus.equals(DRAW)) {
                break;
            }
            playerTurn++;
        }
        System.out.println(gameStatus);
    }

    private static void printGrid(char[][] grid) {

        System.out.println("---------");
        for (int row = 0; row < grid.length; row++) {
            System.out.print("| ");
            for (int col = 0; col < grid[row].length; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static void processPlayerMove(char[][] grid, Scanner sc, char currPlayer) {

        boolean validMove = false;
        while (!validMove) {

            int row;
            int col;
            try {
                row = sc.nextInt();
                col = sc.nextInt();
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                sc.nextLine(); // discard invalid input
                continue;
            }

            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (grid[row - 1][col - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                grid[row - 1][col - 1] = currPlayer;
                validMove = true;
            }
        }
    }

    private static String analyzeGame(char[][] grid) {

        int xCount = 0, oCount = 0, underscoreCount = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                char c = grid[row][col];
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
            return INVALID_STATE;
        } else if (xWins) {
            return X_WIN;
        } else if (oWins) {
            return O_WIN;
        } else if (underscoreCount > 0) {
            return GAME_IN_PROGRESS;
        } else {
            return DRAW;
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
