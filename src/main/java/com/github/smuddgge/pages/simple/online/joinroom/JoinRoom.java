package com.github.smuddgge.pages.simple.online.joinroom;

import com.github.smuddgge.events.GameRoomJoinEvent;
import com.github.smuddgge.requests.GameRoomListRequest;
import com.github.smuddgge.controllers.MultiplayerPlayer;
import com.github.smuddgge.controllers.MultiplayerServer;
import com.github.smuddgge.engine.Application;
import com.github.smuddgge.engine.MultiplayerManager;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.ChessGame;
import com.github.smuddgge.game.layout.BoardLayoutDefault;
import com.github.smuddgge.items.ItemCollection;
import com.github.smuddgge.items.button.Button;
import com.github.smuddgge.items.button.ButtonExecute;
import com.github.smuddgge.items.button.ButtonText;
import com.github.smuddgge.items.text.Text;
import com.github.smuddgge.pages.Page;
import com.github.smuddgge.pages.game.Game;
import com.github.smuddgge.positions.BoardSize;
import com.github.smuddgge.positions.ModularPosition;
import com.github.smuddgge.positions.Position;

import java.util.Map;
import java.util.UUID;

public class JoinRoom extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    public JoinRoom() {
        this.itemCollection = new ItemCollection().setColumns(1);

        ChessGame chessBoard = new ChessGame(
                new ModularPosition(500, 500).setStatic(true).setPos1(
                        new Position(10, 10)
                ).setToFirstPosition(),
                new MultiplayerServer(ChessColour.WHITE),
                new MultiplayerPlayer(ChessColour.BLACK),
                new BoardSize(8, 8),
                new BoardLayoutDefault()
        );

        this.itemCollection.addItem(new Text(
                new ModularPosition(500, 50),
                "Available Rooms"
        ));

        Map<String, Object> rooms = (Map<String, Object>) MultiplayerManager.get().request(new GameRoomListRequest());

        for (Map.Entry<String, Object> entry : rooms.entrySet()) {
            Map<String, Object> room = (Map<String, Object>) entry.getValue();

            if ((boolean) room.get("isFull")) continue;

            this.itemCollection.addItem(new Button(
                    new ModularPosition(500, 50),
                    new ButtonText("Join Room: " + room.get("name")),
                    new ButtonExecute(() -> {
                        MultiplayerManager.get().broadcastEvent(new GameRoomJoinEvent(UUID.fromString(entry.getKey())));
                        Application.setPage(new Game(chessBoard));
                    })
            ));
        }

        this.itemCollection.addItem(new Button(
                new ModularPosition(500, 50),
                new ButtonText("Refresh"),
                new ButtonExecute(() -> {
                    Application.setPage(new JoinRoom());
                })
        ));

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}
