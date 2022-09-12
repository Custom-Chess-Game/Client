package com.github.smudgge.controllers;

import com.github.smudgge.algorithms.Algorithm;
import com.github.smudgge.game.ChessBoard;
import com.github.smudgge.game.ChessColour;
import com.github.smudgge.game.ChessMove;

/**
 * Represents a computer player
 */
public class Bot extends Controller {

    /**
     * The algorithm instance used by the bot
     */
    private Algorithm algorithm;

    /**
     * Create a new instance of a {@link Controller}
     * @param colour The colour the bot will play as
     * @param algorithm The algorithm used
     */
    public Bot(ChessColour colour, Algorithm algorithm) {
        super(colour);

        this.algorithm = algorithm;
    }

    @Override
    public boolean canClick() {
        return false;
    }

    @Override
    public boolean onMyTurn(ChessBoard board) {
        ChessMove move = this.algorithm.getMove(board, this.getColour());
        board.makeMove(move);
        return true;
    }

    @Override
    public void onTurnEnd(ChessMove move) {

    }

    @Override
    public ControllerType getType() {
        return ControllerType.BOT;
    }
}
