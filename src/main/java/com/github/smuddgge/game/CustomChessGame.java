package com.github.smuddgge.game;

import com.github.smuddgge.controllers.Controller;
import com.github.smuddgge.game.layout.BoardLayout;
import com.github.smuddgge.positions.BoardSize;
import com.github.smuddgge.positions.ModularPosition;

public class CustomChessGame extends ChessGame {

    /**
     * The name of the custom chess game
     */
    private String name;

    /**
     * Used to create a new instance of the chessboard
     * Used to create a new instance of a custom chess game
     * @param modularPosition The position of the board
     * @param player1         The first player
     * @param player2         The second player
     * @param size            The size of the board
     * @param layout          The layout of the board
     * @param name            The name of the custom chess game
     */
    public CustomChessGame(ModularPosition modularPosition, Controller player1, Controller player2, BoardSize size, BoardLayout layout, String name) {
        super(modularPosition, player1, player2, size, layout);
        this.name = name;
    }

    /**
     * Used to get the name of the custom chess game
     * @return Name of the custom chess game
     */
    public String getName() {
        return this.name;
    }
}
