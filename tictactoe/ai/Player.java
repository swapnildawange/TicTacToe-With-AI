package tictactoe.ai;


import tictactoe.engine.Board;
// import engine.Mark;
@FunctionalInterface
public interface Player {
    Ai create(Board board,char mark);
}
