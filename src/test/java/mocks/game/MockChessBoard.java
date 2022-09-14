package mocks.game;

import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.layout.BoardLayoutDefault;

/**
 * Used to get an example chess board
 */
public class MockChessBoard {

    /**
     * Used to get a mock chessboard
     * @return Chess board instance
     */
    public static ChessBoard get() {
        ChessBoard chessBoard = new ChessBoard(8, 8);

        chessBoard.setup();
        chessBoard.setLayout(new BoardLayoutDefault());

        return chessBoard;
    }
}
