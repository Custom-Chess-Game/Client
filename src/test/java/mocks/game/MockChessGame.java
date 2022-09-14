package mocks.game;

import com.github.smuddgge.algorithms.AlgorithmBasic;
import com.github.smuddgge.controllers.Bot;
import com.github.smuddgge.controllers.Player;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.ChessGame;
import com.github.smuddgge.game.layout.BoardLayoutDefault;
import com.github.smuddgge.positions.BoardSize;
import com.github.smuddgge.positions.ModularPosition;

/**
 * Used to get an example game board
 */
public class MockChessGame {

    /**
     * Used to get a mock chess game
     * @return Chess game instance
     */
    public static ChessGame get() {
        return new ChessGame(
                new ModularPosition(0, 0),
                new Player(ChessColour.WHITE),
                new Bot(ChessColour.BLACK, new AlgorithmBasic()),
                new BoardSize(8, 8),
                new BoardLayoutDefault()
        );
    }
}
