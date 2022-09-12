package com.github.smudgge.pages.online;

import com.github.smuddgge.events.PlayerStatusEvent;
import com.github.smuddgge.utility.PlayerStatus;
import com.github.smudgge.engine.Application;
import com.github.smudgge.engine.MultiplayerManager;
import com.github.smudgge.items.ItemCollection;
import com.github.smudgge.items.button.Button;
import com.github.smudgge.items.button.ButtonExecute;
import com.github.smudgge.items.button.ButtonText;
import com.github.smudgge.pages.Page;
import com.github.smudgge.pages.online.createroom.CreateRoom;
import com.github.smudgge.pages.online.joinroom.JoinRoom;
import com.github.smudgge.positions.ModularPosition;

public class Offline extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the offline menu
     */
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
