package com.github.smudgge.pages.online.joinroom;

import com.github.smuddgge.events.GameRoomCreateEvent;
import com.github.smuddgge.events.GameRoomJoinEvent;
import com.github.smuddgge.requests.GameRoomListRequest;
import com.github.smudgge.controllers.MultiplayerPlayer;
import com.github.smudgge.controllers.MultiplayerServer;
import com.github.smudgge.engine.Application;
import com.github.smudgge.engine.MultiplayerManager;
import com.github.smudgge.game.ChessColour;
import com.github.smudgge.game.ChessGame;
import com.github.smudgge.game.layout.BoardLayoutDefault;
import com.github.smudgge.items.ItemCollection;
import com.github.smudgge.items.button.Button;
import com.github.smudgge.items.button.ButtonExecute;
import com.github.smudgge.items.button.ButtonText;
import com.github.smudgge.items.input.Input;
import com.github.smudgge.items.text.Text;
import com.github.smudgge.pages.Page;
import com.github.smudgge.pages.game.Game;
import com.github.smudgge.pages.online.createroom.Room;
import com.github.smudgge.positions.BoardSize;
import com.github.smudgge.positions.ModularPosition;
import com.github.smudgge.positions.Position;

import java.util.Map;
import java.util.UUID;

public class JoinRoom extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the offline menu
     */
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
        System.out.println(rooms);

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
