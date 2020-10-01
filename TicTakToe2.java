// package tictactoe;

import java.util.*;

public class TicTakToe2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // System.out.println("Enter cells: ");
        // String s = scanner.nextLine();
        int[][] board = new int[4][4];
        char[][] cboard = new char[4][4];
        conToMat(cboard, board);
        disboard_mat(cboard);
        getEntry(cboard, scanner);
    }

    public static void disboard_mat(char[][] cboard) {
        System.out.println("---------");
        for (int i = 1; i <= 3; i++) {

            System.out.print("| ");
            for (int j = 1; j <= 3; j++) {
                System.out.print(cboard[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void conToMat(char[][] cboard, int[][] board) {
        int i, j;
        int k = 0;
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {

                cboard[i][j] = ' ';
                board[i][j] = -1;
                k++;

            }
        }
    }

    public static void getEntry(char[][] cboard, Scanner scanner) {
        int i = 0;
        int j = 0;
        int player = -1;
        boolean error = true;
        while (error && !checkRows(cboard) && !checkCols(cboard) && !checkMajorDiagonal(cboard)
                && !checkMinorDiagonal(cboard) && !checkDraw(cboard, player)) {

            System.out.println("Enter coordinates:");
            String input = scanner.nextLine();

            String[] pieces = input.split(" ");
            if ((pieces[0].matches("\\d")) && (pieces[1].matches("\\d"))) {

                i = Integer.parseInt(pieces[0]);
                j = Integer.parseInt(pieces[1]);

                if (i <= 3 && j <= 3) {
                    if (isValid(cboard, (4 - j), i) && player % 2 == 0) {
                        cboard[4 - j][i] = 'X';
                        player++;
                        disboard_mat(cboard);
                        System.out.println("Making move level \"easy\"");
                        // error = false;
                    } else if (isValid(cboard, (4 - j), i) && player % 2 != 0) {
                        cboard[4 - j][i] = 'O';
                        player++;
                        disboard_mat(cboard);
                        System.out.println("Making move level \"easy\"");
                        // error = false;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        error = true;
                    }

                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                    error = true;
                }

            } else {
                System.out.println("You should enter numbers!");
                error = true;
            }

        }

    }

    public static boolean checkDraw(char[][] cboard, int player) {
        int player_X = 0;
        int player_Y = 0;
        int k = 0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (cboard[i][j] == 'X') {
                    player_X++;
                    k++;
                } else if (cboard[i][j] == 'O') {
                    player_Y++;
                    k++;
                }
            }
        }
        // System.out.println(player_X+"|"+player_Y+"|"+player);
        if (k == 9) {
            System.out.println("Draw");
            return true;
        }

        return false;
    }

    public static boolean isValid(char[][] cboard, int i, int j) {
        if (cboard[i][j] == ' ')
            return true;
        else
            return false;
    }

    public static boolean checkRows(char[][] cboard) {
        int i, j = 0;
        int count_1 = 0;
        int ans = 0;
        int winner = 0;
        int count_0 = 0;
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                if (cboard[i][j] == 'X') {
                    count_1++;

                } else if (cboard[i][j] == 'O') {
                    count_0++;
                }
            }
            if (count_1 == 3) {
                ans++;
                winner = 1;

            } else if (count_0 == 3) {
                winner = 0;
                ans++;

            }
            if (j == 4) {
                count_1 = 0;
                count_0 = 0;

            }

        }
        if (ans == 1) {
            if (winner == 1) {
                System.out.println("X wins");
            } else if (winner == 0) {
                System.out.println("O wins");
            }
            return true;
        } else if (ans > 1) {
            System.out.println("Impossible");
            return true;
        }
        return false;

    }

    public static boolean checkCols(char[][] cboard) {
        int i, j = 0;
        int ans = 0;
        int winner = 0;
        int count_1 = 0;
        int count_0 = 0;
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                if (cboard[j][i] == 'X') {
                    count_1++;
                } else if (cboard[j][i] == 'O') {
                    count_0++;

                }
            }
            if (count_1 == 3) {
                ans++;
                winner = 1;

            } else if (count_0 == 3) {
                ans++;

                winner = 0;

            }
            if (j == 4) {
                count_1 = 0;
                count_0 = 0;
            }

        }
        if (ans == 1) {
            if (winner == 1) {
                System.out.println("X wins");
            } else if (winner == 0) {
                System.out.println("O wins");
            }
            return true;

        } else if (ans > 1) {
            System.out.println("Impossible");
            return true;
        }
        return false;

    }

    public static boolean checkMajorDiagonal(char[][] cboard) {
        int i = 0;
        int j = 0;
        int count_1 = 0;
        int count_0 = 0;

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {

                // major digonal
                if (i == j) {
                    if (cboard[i][j] == 'X') {
                        count_1++;
                    }
                    if (cboard[i][j] == 'O') {
                        count_0++;

                    }
                }

            }

            if (count_0 == 3) {
                System.out.println("O wins");
                return true;
            } else if (count_1 == 3) {
                System.out.println("X wins");
                return true;
            }
        }
        return false;
    }

    public static boolean checkMinorDiagonal(char[][] cboard) {
        int i = 0;
        int j = 2;
        int count_1 = 0;
        int count_0 = 0;

        for (i = 1; i <= 3; i++) {
            for (j = 3; j >= 1; j--) {

                // minor digonal
                if (i + j == 4) {
                    if (cboard[i][j] == 'X') {
                        count_1++;
                    }
                    if (cboard[i][j] == 'O') {
                        count_0++;

                    }
                }

            }

            if (count_0 == 3) {
                System.out.println("O wins");
                return true;
            } else if (count_1 == 3) {
                System.out.println("X wins");
                return true;
            }
        }
        return false;
    }

}
