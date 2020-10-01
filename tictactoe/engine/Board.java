package tictactoe.engine;


import java.util.Random;

public final class Board {
    private static final Random random = new Random();
    // private static final int[][] TRIPS = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
    //         {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};

    private final char[][] board = new char[4][4];
    private static final int index[] = new int[2];


    public static int[] getIndex(final int x, final int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IndexOutOfBoundsException("Coordinates should be from 1 to 3");
        }
        index[0] = x;
        index[1] = y;
        return index;
//        return x - 3 * y + 8;
    }

    public Board() {
        for(int i=1;i<=3;i++){
            for (int j = 1; j <= 3; j++) {
                board[i][j]=' ';
            }
        }

    }

    public void set(int[] index, char state) {
        board[index[0]][index[1]] = state;
    }



    public boolean isEmpty() {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {

                if (board[i][j] != ' ') {
                    return false;
                }

            }
        }
        return true;

    }



    public boolean isEmpty(int[] index) {
        if (board[index[0]][index[1]] == ' ')
            return true;
        return false;
    }


    public  int[] checkRows() {
        int[] count ={-1,-1};
        int count_O = 0;
        int count_X = 0;
        for (int j = 1; j <= 3; j++) {
            for (int i = 1; i <= 3; i++) {
                // System.out.print(cboard[j][i]+" ");
                if (board[j][i] == 'X') {

                    count_X++;
                }
                if (board[j][i] == 'O') {
                    count_O++;
                }

                if (i == 3) {

                    if (count_O == 3) {
                        count[0]=0;
//                        System.out.println("O wins");
//                        return true;
                    }
                    if (count_X == 3) {
                        count[1]=1;
//                        System.out.println("X wins");
//                        return true;
                    }

                }


                if (i == 3) {

                    count_O = 0;
                    count_X = 0;

                }
            }
        }
        return count;
    }


    public  int[] checkCols() {
        int count_O = 0;
        int count_X = 0;
        int[] count ={-1,-1};
        for (int j = 1; j <= 3; j++) {
            for (int i = 1; i <= 3; i++) {
                if (board[i][j] == 'X') {

                    count_X++;
                }
                if (board[i][j] == 'O') {
                    count_O++;
                }

                if (i == 3) {
                    if (count_O == 3) {
                        count[0]=0;

                    }
                    if (count_X == 3) {
                        count[1]=1;

                    }

                }
                if (i == 3) {

                    count_O = 0;
                    count_X = 0;

                }
            }
        }
        return count;

    }

    public  int[] checkMajorDiagonal() {
        int i = 0;
        int j = 0;
        int[] count ={-1,-1};
        int count_X = 0;
        int count_O = 0;

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {

                // major digonal
                if (i == j) {
                    if (board[i][j] == 'X') {
                        count_X++;
                    }
                    if (board[i][j] == 'O') {
                        count_O++;

                    }
                }

            }


            if (count_O == 3) {
                count[0]=0;
            } else if (count_X == 3) {
                count[1]=1;

            }
        }
        return count;
    }

    public  int[] checkMinorDiagonal() {
        int i = 0;
        int j;
        int[] count ={-1,-1};
        int count_X = 0;
        int count_O = 0;

        for (i = 1; i <= 3; i++) {
            for (j = 3; j >= 1; j--) {

                // minor digonal
                if (i + j == 4) {
                    if (board[i][j] == 'X') {
                        count_X++;
                    }
                    if (board[i][j] == 'O') {
                        count_O++;

                    }
                }

            }



            if (count_O == 3) {
                count[0]=0;

            } else if (count_X == 3) {
                count[1]=1;

            }
        }
        return count;

    }


    public  int checkDraw() {
        int k = 0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (board[i][j] == 'X' || board[i][j] == 'O') {
                    k++;
                }
            }
        }
        // System.out.println(k);
        if (k == 9) {

            return 1;
        }

        return 0;
    }
    public int[] getRandomFree(){
        int[] index= new int[2];
        while (true){
            int x=random.nextInt(3)+1;
            int y=random.nextInt(3)+1;
            if(board[x][y] ==' '){
                index[0]=x;
                index[1]=y;
                return index;

            }
        }
    }

    public State getGameState() {

        int[] rows=checkRows();
        int[] cols=checkCols();
        int[] majDi=checkMajorDiagonal();
        int[] minDi=checkMinorDiagonal();
        int draw=checkDraw();

        if(draw == 1){
            return State.DRAW;
        }else if(rows[0]==0 || cols[0]==0 || majDi[0]==0 || minDi[0]==0){
            return State.O_WINS;
        }else  if(rows[1]==1 || cols[1]==1 || majDi[1]==1 || minDi[1]==1) {
            return State.X_WINS;
        }else if(rows[0]==-1 && rows[1]==-1 && cols[0]==-1 && cols[1]==-1 && majDi[0]==-1 && majDi[1]==-1 && minDi[0]==-1 && minDi[1]==-1) {
            return State.PLAYING;
        }else {
            return State.IMPOSSIBLE;
        }

    }

    @Override
    public String toString() {
        Character cboard[] =new Character[9];
        int k=0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                cboard[k++]=board[i][j];
            }
        }
        return String.format("---------%n| %c %c %c |%n| %c %c %c |%n| %c %c %c |%n---------",cboard[0], cboard[1],
                cboard[2], cboard[3], cboard[4], cboard[5], cboard[6], cboard[7], cboard[8]);

    }


}