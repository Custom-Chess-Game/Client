package com.github.smuddgge.game.layout;

import com.github.smuddgge.game.ChessBoard;

/**
 * Represents a chess board layout
 */
public interface BoardLayout {

    /**
     * Used to update the board to the layout
     * @param board Instance of the chessboard
     */
    void update(ChessBoard board);
}
