package tictactoe.ai;


// import engine.Mark;
import tictactoe.engine.Board;

public final class Easy extends Ai {

    public Easy(Board board, char mark) {
        super(board, mark);
    }

    @Override
    public int[] getMove() {
        System.out.println("Making move level Easy");
        return board.getRandomFree();
    }
}
