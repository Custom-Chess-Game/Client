package com.github.smuddgge.pages.game;

import com.github.smuddgge.Client;
import com.github.smuddgge.engine.Application;
import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.items.ItemCollection;
import com.github.smuddgge.items.button.Button;
import com.github.smuddgge.items.button.ButtonExecute;
import com.github.smuddgge.items.button.ButtonText;
import com.github.smuddgge.items.text.Text;
import com.github.smuddgge.pages.simple.MainMenu;
import com.github.smuddgge.pages.Page;
import com.github.smuddgge.positions.ModularPosition;

/**
 * Represents the menu when the game ends
 */
public class GameEnd extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the game end menu
     * @param colour The colour of the winning player
     *               This can be null if it's a tie
     * @param board The board instance
     */
    public GameEnd(ChessColour colour, ChessBoard board) {
        this.itemCollection = new ItemCollection().setColumns(2);

        this.itemCollection.addItem(new Text(
                new ModularPosition(200, 50),
                colour.name() + " Won!"
        ));

        this.itemCollection.addItem(new Button(
                new ModularPosition(200, 50),
                new ButtonText("Exit to main menu"),
                new ButtonExecute(() -> Application.setPage(Client.getMainMenu()))
        ));

        this.itemCollection.addItem(new Text(
                new ModularPosition(200, 50),
                board.getLog().asString()
        ));

        this.itemCollection.addItem(new Text(
                new ModularPosition(100, 200),
                board.asString().replace("\n", "<br>")
        ));

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}