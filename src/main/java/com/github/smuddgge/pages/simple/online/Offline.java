package com.github.smuddgge.pages.simple.online;

import com.github.smuddgge.events.PlayerStatusEvent;
import com.github.smuddgge.utility.PlayerStatus;
import com.github.smuddgge.engine.Application;
import com.github.smuddgge.engine.MultiplayerManager;
import com.github.smuddgge.items.ItemCollection;
import com.github.smuddgge.items.button.Button;
import com.github.smuddgge.items.button.ButtonExecute;
import com.github.smuddgge.items.button.ButtonText;
import com.github.smuddgge.pages.Page;
import com.github.smuddgge.pages.simple.online.createroom.CreateRoom;
import com.github.smuddgge.pages.simple.online.joinroom.JoinRoom;
import com.github.smuddgge.positions.ModularPosition;

public class Offline extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    public Offline() {
        this.itemCollection = new ItemCollection().setColumns(1);

        this.itemCollection.addItem(new Button(
                new ModularPosition(500, 100),
                new ButtonText("Join Room"),
                new ButtonExecute(() -> {
                    MultiplayerManager.get().broadcastEvent(new PlayerStatusEvent(PlayerStatus.WAITING));
                    Application.setPage(new JoinRoom());
                })
        ));

        this.itemCollection.addItem(new Button(
                new ModularPosition(500, 100),
                new ButtonText("Create Room"),
                new ButtonExecute(() -> Application.setPage(new CreateRoom()))
        ));

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}
