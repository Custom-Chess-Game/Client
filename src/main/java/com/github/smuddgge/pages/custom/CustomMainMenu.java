package com.github.smuddgge.pages.custom;

import com.github.smuddgge.engine.Application;
import com.github.smuddgge.engine.MultiplayerManager;
import com.github.smuddgge.items.ItemCollection;
import com.github.smuddgge.items.button.Button;
import com.github.smuddgge.items.button.ButtonExecute;
import com.github.smuddgge.items.button.ButtonText;
import com.github.smuddgge.pages.Page;
import com.github.smuddgge.pages.simple.online.EnterName;
import com.github.smuddgge.positions.ModularPosition;

/**
 * Represents a modded main menu
 */
public class CustomMainMenu extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the main menu
     */
    public CustomMainMenu() {
        this.itemCollection = new ItemCollection().setColumns(1);

        this.itemCollection.addItem(new Button(
                new ModularPosition(400, 100).setCentered(true),
                new ButtonText("Custom"),
                new ButtonExecute(() -> Application.setPage(new CustomGamesList()))
        ));

        if (MultiplayerManager.isConnected()) {
            this.itemCollection.addItem(new Button(
                    new ModularPosition(400, 100).setCentered(true),
                    new ButtonText("Online"),
                    new ButtonExecute(() -> Application.setPage(new EnterName("Enter name")))
            ));
        } else {
            this.itemCollection.addItem(new Button(
                    new ModularPosition(400, 100).setCentered(true),
                    new ButtonText("Refresh connection"),
                    new ButtonExecute(() -> {
                        MultiplayerManager.registerClient();
                        Application.setPage(new CustomMainMenu());
                    }))
            );
        }

        this.itemCollection.addItem(new Button(
                new ModularPosition(400, 100).setCentered(true),
                new ButtonText("Statistics")
        ));

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}