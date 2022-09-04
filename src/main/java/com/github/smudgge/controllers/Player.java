package com.github.smudgge.controllers;

import com.github.smudgge.game.ChessBoard;
import com.github.smudgge.game.ChessColour;

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
    public ControllerType getType() {
        return ControllerType.PLAYER;
    }
}
