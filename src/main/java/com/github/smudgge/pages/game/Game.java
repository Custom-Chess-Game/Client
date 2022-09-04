package com.github.smudgge.pages.game;

import com.github.smudgge.game.ChessGame;
import com.github.smudgge.items.ItemCollection;
import com.github.smudgge.pages.Page;

/**
 * Represents the menu where the game is played
 */
public class Game extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the offline menu
     * @param chessGame Instance of the game
     */
    public Game(ChessGame chessGame) {
        this.itemCollection = new ItemCollection().setColumns(3);

        this.itemCollection.addItem(chessGame);

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}