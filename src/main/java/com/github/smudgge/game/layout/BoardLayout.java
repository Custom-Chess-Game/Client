package com.github.smudgge.game.layout;

import com.github.smudgge.game.ChessBoard;

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
