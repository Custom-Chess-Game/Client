package com.github.smudgge.pages.online;

import com.github.smudgge.engine.Application;
import com.github.smudgge.engine.MultiplayerManager;
import com.github.smudgge.items.ItemCollection;
import com.github.smudgge.items.button.Button;
import com.github.smudgge.items.button.ButtonExecute;
import com.github.smudgge.items.button.ButtonText;
import com.github.smudgge.items.input.Input;
import com.github.smudgge.items.text.Text;
import com.github.smudgge.pages.Page;
import com.github.smudgge.positions.ModularPosition;

public class EnterName extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the offline menu
     */
    public EnterName() {
        this.itemCollection = new ItemCollection().setColumns(1);

        this.itemCollection.addItem(new Text(
                new ModularPosition(500, 50),
                "Enter Name"
        ));

        Input input = new Input(
                new ModularPosition(500, 50)
        );

        this.itemCollection.addItem(input);

        this.itemCollection.addItem(new Button(
                new ModularPosition(500, 50),
                new ButtonText("Confirm"),
                new ButtonExecute(() -> {
                    MultiplayerManager.setClientName(input.getInput());
                    Application.setPage(new Offline());
                })
        ));

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}
