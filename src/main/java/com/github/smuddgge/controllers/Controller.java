package com.github.smuddgge.controllers;

import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.ChessMove;

/**
 * Represents one of the players in the game
 */
public abstract class Controller {

    /**
     * The colour the controller plays as
     */
    private final ChessColour colour;

    /**
     * Used to create a new instance of a controller
     * @param colour The colour the controller plays
     */
    public Controller(ChessColour colour) {
        this.colour = colour;
    }

    /**
     * @return If this controller is able to click the screen
     */
    public abstract boolean canClick();

    /**
     * Called when it's the turn of the controllers
     * @param board The instance of the chess board
     * @return If the turn has ended
     */
    public abstract boolean onMyTurn(ChessBoard board);

    /**
     * Called when the controller has made a move
     * @param move Move that has been made
     */
    public abstract void onTurnEnd(ChessMove move);

    /**
     * Used to get the controller type
     * @return Controller type
     */
    public abstract ControllerType getType();

    /**
     * Used to get the controllers colour that they play as
     */
    public ChessColour getColour() {
        return this.colour;
    }
}
