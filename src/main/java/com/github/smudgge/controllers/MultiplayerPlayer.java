package com.github.smudgge.controllers;

import com.github.smuddgge.events.PlayerMoveEvent;
import com.github.smudgge.engine.MultiplayerManager;
import com.github.smudgge.game.ChessBoard;
import com.github.smudgge.game.ChessColour;
import com.github.smudgge.game.ChessMove;

public class MultiplayerPlayer extends Controller {

    /**
     * Used to create a new instance of a controller
     * Used to create a new instance of a multiplayer player
     * This player will broadcast events of its moves
     * @param colour The colour the controller plays
     */
    public MultiplayerPlayer(ChessColour colour) {
        super(colour);
    }

    @Override
    public boolean canClick() {
        return true;
    }

    @Override
    public boolean onMyTurn(ChessBoard board) {
        return false;
    }

    @Override
    public void onTurnEnd(ChessMove move) {
        MultiplayerManager.get().broadcastEvent(new PlayerMoveEvent(move.convertClassToString()));
    }

    @Override
    public ControllerType getType() {
        return null;
    }
}
