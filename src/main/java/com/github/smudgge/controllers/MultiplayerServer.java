package com.github.smudgge.controllers;

import com.github.smuddgge.requests.PlayerMoveRequest;
import com.github.smudgge.engine.Application;
import com.github.smudgge.engine.ApplicationState;
import com.github.smudgge.engine.MultiplayerManager;
import com.github.smudgge.game.ChessBoard;
import com.github.smudgge.game.ChessColour;
import com.github.smudgge.game.ChessMove;

public class MultiplayerServer extends Controller {

    /**
     * Used to create a new instance of a controller
     * Used to create a new instance of a multiplayer server
     * This represents the other player on the chess board conencted
     * with the server.
     * @param colour The colour the controller plays
     */
    public MultiplayerServer(ChessColour colour) {
        super(colour);
    }

    @Override
    public boolean canClick() {
        return false;
    }

    @Override
    public boolean onMyTurn(ChessBoard board) {
        while(true) {
            if (Application.getState() == ApplicationState.Stopped) return false;

            String move = (String) MultiplayerManager.get().request(new PlayerMoveRequest());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (move == null) continue;

            ChessMove chessMove = ChessMove.convertStringToClass(move, board);
            board.makeMove(chessMove);

            return true;
        }
    }

    @Override
    public void onTurnEnd(ChessMove move) {

    }

    @Override
    public ControllerType getType() {
        return null;
    }
}
