import java.util.Scanner;

public class SudokuSolver {

    // Size of the grid
    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[GRID_SIZE][GRID_SIZE];

        // Input the board (0 for empty cells)
        System.out.println("Enter the Sudoku puzzle (enter 0 for empty spaces):");
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        if (solveBoard(board)) {
            System.out.println("Sudoku Solved Successfully:");
            printBoard(board);
        } else {
            System.out.println("This Sudoku puzzle cannot be solved.");
        }
        scanner.close();
    }

    // Function to solve the Sudoku using backtracking
    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                // Check for an empty cell (0 represents an empty cell)
                if (board[row][col] == 0) {
                    // Try placing numbers 1 through 9 in the empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isValidPlacement(board, num, row, col)) {
                            board[row][col] = num;

                            // Recursively try to solve the rest of the board
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                // If the current placement doesn't work, backtrack by resetting the cell
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false; // No valid number can be placed in this cell
                }
            }
        }
        return true; // Solved if there are no more empty cells
    }

    // Function to check if the placement of a number is valid
    private static boolean isValidPlacement(int[][] board, int number, int row, int col) {
        // Check if the number is already in the row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return false;
            }
        }

        // Check if the number is already in the column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }

        // Check if the number is already in the 3x3 grid
        int localRow = row - row % 3;
        int localCol = col - col % 3;
        for (int i = localRow; i < localRow + 3; i++) {
            for (int j = localCol; j < localCol + 3; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }

        return true; // If the number is not present in the row, column, or grid, it's a valid placement
    }

    // Function to print the Sudoku board
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }

            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
