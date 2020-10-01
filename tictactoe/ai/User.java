package tictactoe.ai;

import tictactoe.engine.Mark;
import tictactoe.engine.Board;

import java.util.*;

public class User extends Ai {
    final Scanner scanner = new Scanner(System.in);
    public User(Board board,char mark){
        super(board,mark);
    }


    @Override
    public int[] getMove(){
        while(true){
            System.out.println("Enter the coordinates: ");
            try{
                final int x =scanner.nextInt();
                final int y=scanner.nextInt();
                final int[] index =Board.getIndex(x, y);

                if (board.isEmpty(index)) {
                    return index;
                }
                System.out.println("This cell is occupied! Choose another one!");
            }catch(InputMismatchException e){
                System.out.println("You should enter numbers!");
            }catch(IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }

        }
    }
}
