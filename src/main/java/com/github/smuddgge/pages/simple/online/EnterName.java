package com.github.smuddgge.pages.simple.online;

import com.github.smuddgge.engine.Application;
import com.github.smuddgge.engine.MultiplayerManager;
import com.github.smuddgge.items.ItemCollection;
import com.github.smuddgge.items.button.Button;
import com.github.smuddgge.items.button.ButtonExecute;
import com.github.smuddgge.items.button.ButtonText;
import com.github.smuddgge.items.input.Input;
import com.github.smuddgge.items.text.Text;
import com.github.smuddgge.pages.Page;
import com.github.smuddgge.positions.ModularPosition;

import java.util.Objects;

public class EnterName extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    public EnterName(String title) {
        this.itemCollection = new ItemCollection().setColumns(1);

        this.itemCollection.addItem(new Text(
                new ModularPosition(500, 50),
                title
        ));

        Input input = new Input(
                new ModularPosition(500, 50)
        );

        this.itemCollection.addItem(input);

        this.itemCollection.addItem(new Button(
                new ModularPosition(500, 50),
                new ButtonText("Confirm"),
                new ButtonExecute(() -> {
                    if (Objects.equals(input.getInput(), "")) {
                        Application.setPage(new EnterName("Name cannot be null"));
                        return;
                    }
                    MultiplayerManager.setClientName(input.getInput());
                    Application.setPage(new Online());
                })
        ));

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}
