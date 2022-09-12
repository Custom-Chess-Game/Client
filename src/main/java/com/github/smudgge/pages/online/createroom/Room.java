package com.github.smudgge.pages.online.createroom;

import com.github.smuddgge.requests.GameRoomRequest;
import com.github.smudgge.controllers.MultiplayerPlayer;
import com.github.smudgge.controllers.MultiplayerServer;
import com.github.smudgge.engine.Application;
import com.github.smudgge.engine.MultiplayerManager;
import com.github.smudgge.game.ChessColour;
import com.github.smudgge.game.ChessGame;
import com.github.smudgge.game.layout.BoardLayoutDefault;
import com.github.smudgge.items.ItemCollection;
import com.github.smudgge.items.text.Text;
import com.github.smudgge.pages.Page;
import com.github.smudgge.pages.game.Game;
import com.github.smudgge.positions.BoardSize;
import com.github.smudgge.positions.ModularPosition;
import com.github.smudgge.positions.Position;

import java.util.Map;

public class Room extends Page {

    private boolean runningThread = true;

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the offline menu
     */
    public Room() {
        this.itemCollection = new ItemCollection().setColumns(1);

        this.itemCollection.addItem(new Text(
                new ModularPosition(500, 100),
                "Waiting for players..."
        ));

        this.itemCollection.toPage(this);

        new Thread(this::threaded).start();
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }

    public void threaded() {
        while(this.runningThread) {
            Map<String, Object> room = (Map<String, Object>) MultiplayerManager.get().request(new GameRoomRequest());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (room.get("player2") == null) continue;

            ChessGame chessBoard = new ChessGame(
                    new ModularPosition(500, 500).setStatic(true).setPos1(
                            new Position(10, 10)
                    ).setToFirstPosition(),
                    new MultiplayerPlayer(ChessColour.WHITE),
                    new MultiplayerServer(ChessColour.BLACK),
                    new BoardSize(8, 8),
                    new BoardLayoutDefault()
            );

            Application.setPage(new Game(chessBoard));
            this.runningThread = false;
        }
    }
}
