package com.github.smuddgge.controllers;

import com.github.smuddgge.engine.Application;
import com.github.smuddgge.engine.ApplicationState;
import com.github.smuddgge.engine.MultiplayerManager;
import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.ChessMove;
import com.github.smuddgge.pages.game.GameEnd;
import com.github.smuddgge.requests.GameRoomRequest;
import com.github.smuddgge.requests.PlayerMoveRequest;

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

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String move = (String) MultiplayerManager.get().request(new PlayerMoveRequest());

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
