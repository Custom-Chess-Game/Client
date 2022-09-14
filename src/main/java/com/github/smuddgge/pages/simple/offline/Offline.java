package com.github.smuddgge.pages.simple.offline;

import com.github.smuddgge.controllers.Player;
import com.github.smuddgge.engine.Application;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.items.ItemCollection;
import com.github.smuddgge.items.button.Button;
import com.github.smuddgge.items.button.ButtonExecute;
import com.github.smuddgge.items.button.ButtonText;
import com.github.smuddgge.pages.Page;
import com.github.smuddgge.positions.ModularPosition;

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
