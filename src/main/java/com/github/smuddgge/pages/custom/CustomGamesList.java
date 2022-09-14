package com.github.smuddgge.pages.custom;

import com.github.smuddgge.ModdedClient;
import com.github.smuddgge.engine.Application;
import com.github.smuddgge.engine.MultiplayerManager;
import com.github.smuddgge.game.ChessGame;
import com.github.smuddgge.game.CustomChessGame;
import com.github.smuddgge.items.ItemCollection;
import com.github.smuddgge.items.button.Button;
import com.github.smuddgge.items.button.ButtonExecute;
import com.github.smuddgge.items.button.ButtonText;
import com.github.smuddgge.pages.Page;
import com.github.smuddgge.pages.game.Game;
import com.github.smuddgge.pages.simple.online.EnterName;
import com.github.smuddgge.positions.ModularPosition;

import java.util.ArrayList;

/**
 * Represents a menu where all custom games are listed
 */
public class CustomGamesList extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the main menu
     */
    public CustomGamesList() {
        this.itemCollection = new ItemCollection().setColumns(2);

        ArrayList<CustomChessGame> chessGames = ModdedClient.getClient().getChessGames();

        for (CustomChessGame chessGame : chessGames) {
            this.itemCollection.addItem(new Button(
                    new ModularPosition(400, 100).setCentered(true),
                    new ButtonText(chessGame.getName()),
                    new ButtonExecute(() -> Application.setPage(new Game(chessGame)))
            ));
        }

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}