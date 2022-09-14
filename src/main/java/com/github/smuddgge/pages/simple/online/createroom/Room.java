package com.github.smuddgge.pages.simple.online.createroom;

import com.github.smuddgge.controllers.MultiplayerPlayer;
import com.github.smuddgge.controllers.MultiplayerServer;
import com.github.smuddgge.engine.Application;
import com.github.smuddgge.engine.MultiplayerManager;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.ChessGame;
import com.github.smuddgge.game.layout.BoardLayoutDefault;
import com.github.smuddgge.items.ItemCollection;
import com.github.smuddgge.items.text.Text;
import com.github.smuddgge.pages.Page;
import com.github.smuddgge.pages.game.Game;
import com.github.smuddgge.positions.BoardSize;
import com.github.smuddgge.positions.ModularPosition;
import com.github.smuddgge.positions.Position;
import com.github.smuddgge.requests.GameRoomRequest;

import java.util.Map;

public class Room extends Page {

    private boolean runningThread = true;

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

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
