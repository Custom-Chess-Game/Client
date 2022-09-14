package com.github.smuddgge.pages.offline;

import com.github.smuddgge.algorithms.AlgorithmBasic;
import com.github.smuddgge.controllers.Bot;
import com.github.smuddgge.controllers.Controller;
import com.github.smuddgge.controllers.ControllerType;
import com.github.smuddgge.controllers.Player;
import com.github.smuddgge.engine.Application;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.ChessGame;
import com.github.smuddgge.game.layout.BoardLayoutDefault;
import com.github.smuddgge.items.ItemCollection;
import com.github.smuddgge.items.button.Button;
import com.github.smuddgge.items.button.ButtonExecute;
import com.github.smuddgge.items.button.ButtonText;
import com.github.smuddgge.items.text.Text;
import com.github.smuddgge.pages.Page;
import com.github.smuddgge.pages.game.Game;
import com.github.smuddgge.positions.BoardSize;
import com.github.smuddgge.positions.ModularPosition;
import com.github.smuddgge.positions.Position;

/**
 * Represents the normal game options page
 */
public class Normal extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * Game settings
     */
    private Controller player1;
    private Controller player2;

    /**
     * New instance of the normal menu
     */
    public Normal(Controller p1, Controller p2) {
        this.player1 = p1;
        this.player2 = p2;

        this.itemCollection = new ItemCollection().setColumns(2);

        Text player1Text = new Text(
                new ModularPosition(100, 100),
                this.player1.getType().name()
        );

        Text player2Text = new Text(
                new ModularPosition(100, 100),
                this.player2.getType().name()
        );

        Button player1Button = new Button(
                new ModularPosition(200, 100).setCentered(true),
                new ButtonText("Switch"),
                new ButtonExecute(() -> {
                    if (player1.getType() == ControllerType.PLAYER) {
                        player1 = new Bot(ChessColour.WHITE, new AlgorithmBasic());
                    } else {
                        player1 = new Player(ChessColour.WHITE);
                    }

                    Application.setPage(new Normal(player1, player2));
                })
        );

        Button player2Button = new Button(
                new ModularPosition(200, 100).setCentered(true),
                new ButtonText("Switch"),
                new ButtonExecute(() -> {
                    if (player2.getType() == ControllerType.PLAYER) {
                        player2 = new Bot(ChessColour.BLACK, new AlgorithmBasic());
                    } else {
                        player2 = new Player(ChessColour.BLACK);
                    }

                    Application.setPage(new Normal(player1, player2));
                })
        );

        ChessGame chessBoard = new ChessGame(
                new ModularPosition(500, 500).setStatic(true).setPos1(
                        new Position(10, 10)
                ).setToFirstPosition(),

                player1, player2,

                new BoardSize(8, 8),
                new BoardLayoutDefault()
        );

        Button create = new Button(
                new ModularPosition(200, 100),
                new ButtonText("Start"),
                new ButtonExecute(() -> Application.setPage(new Game(chessBoard)))
        );

        this.itemCollection.addItem(player1Text);
        this.itemCollection.addItem(player1Button);
        this.itemCollection.addItem(create);
        this.itemCollection.addItem(player2Text);
        this.itemCollection.addItem(player2Button);

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}