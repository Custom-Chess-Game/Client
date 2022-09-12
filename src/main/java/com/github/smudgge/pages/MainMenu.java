package com.github.smudgge.pages;

import com.github.smudgge.engine.Application;
import com.github.smudgge.engine.MultiplayerManager;
import com.github.smudgge.items.ItemCollection;
import com.github.smudgge.items.button.Button;
import com.github.smudgge.items.button.ButtonExecute;
import com.github.smudgge.items.button.ButtonText;
import com.github.smudgge.pages.offline.Offline;
import com.github.smudgge.pages.online.EnterName;
import com.github.smudgge.positions.ModularPosition;

/**
 * Represents the main menu
 */
public class MainMenu extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the main menu
     */
    public MainMenu() {
        this.itemCollection = new ItemCollection().setColumns(1);

        this.itemCollection.addItem(new Button(
                new ModularPosition(400, 100).setCentered(true),
                new ButtonText("Offline"),
                new ButtonExecute(() -> Application.setPage(new Offline()))
        ));

        if (MultiplayerManager.isConnected()) {
            this.itemCollection.addItem(new Button(
                    new ModularPosition(400, 100).setCentered(true),
                    new ButtonText("Online"),
                    new ButtonExecute(() -> Application.setPage(new EnterName()))
            ));
        } else {
            this.itemCollection.addItem(new Button(
                    new ModularPosition(400, 100).setCentered(true),
                    new ButtonText("Refresh connection"),
                    new ButtonExecute(() -> {
                        MultiplayerManager.registerClient();
                        Application.setPage(new MainMenu());
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
