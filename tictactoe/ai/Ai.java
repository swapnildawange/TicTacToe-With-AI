package tictactoe.ai;
import tictactoe.engine.Board;
// import engine.Mark;

import java.util.Random;
import java.util.logging.Logger;


public abstract class Ai {
    protected static final Logger log = Logger.getLogger(Ai.class.getName());

    protected final Random random =new Random();
    protected final Board board;
    protected final char mark;
    public Ai(Board board,char mark){
        this.board =board;
        this.mark =mark;

    }

    public char getMark(){
        return mark;
    }


    public abstract int[] getMove();
}


