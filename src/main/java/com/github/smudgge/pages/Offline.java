package com.github.smudgge.pages;

import com.github.smudgge.controllers.Player;
import com.github.smudgge.engine.Application;
import com.github.smudgge.game.ChessColour;
import com.github.smudgge.items.ItemCollection;
import com.github.smudgge.items.button.Button;
import com.github.smudgge.items.button.ButtonExecute;
import com.github.smudgge.items.button.ButtonText;
import com.github.smudgge.pages.offline.Normal;
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
                new ModularPosition(400, 100).setCentered(true),
                new ButtonText("Normal"),
                new ButtonExecute(() -> Application.setPage(new Normal(
                        new Player(ChessColour.WHITE),
                        new Player(ChessColour.BLACK)
                )))
        ));

        this.itemCollection.addItem(new Button(
                new ModularPosition(400, 100).setCentered(true),
                new ButtonText("Custom")
        ));

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}
