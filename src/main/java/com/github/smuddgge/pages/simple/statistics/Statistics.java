package com.github.smuddgge.pages.simple.statistics;

import com.github.smuddgge.engine.Application;
import com.github.smuddgge.engine.MultiplayerManager;
import com.github.smuddgge.events.PlayerStatusEvent;
import com.github.smuddgge.items.ItemCollection;
import com.github.smuddgge.items.button.Button;
import com.github.smuddgge.items.button.ButtonExecute;
import com.github.smuddgge.items.button.ButtonText;
import com.github.smuddgge.items.text.Text;
import com.github.smuddgge.pages.Page;
import com.github.smuddgge.pages.simple.online.createroom.CreateRoom;
import com.github.smuddgge.pages.simple.online.joinroom.JoinRoom;
import com.github.smuddgge.positions.ModularPosition;
import com.github.smuddgge.utility.PlayerStatus;

public class Statistics extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    public Statistics() {
        this.itemCollection = new ItemCollection().setColumns(1);



        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}
