package com.github.smuddgge.controllers;

import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.ChessMove;

/**
 * Represents a real player
 */
public class Player extends Controller {

    /**
     * Used to create a player
     * @param colour Colour that the player will play as
     */
    public Player(ChessColour colour) {
        super(colour);
    }

    @Override
    public boolean canClick() {
        return true;
    }

    @Override public boolean onMyTurn(ChessBoard board) {
        return false;
    }

    @Override
    public void onTurnEnd(ChessMove move) {

    }

    @Override
    public ControllerType getType() {
        return ControllerType.PLAYER;
    }
}
