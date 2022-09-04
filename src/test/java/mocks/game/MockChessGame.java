package mocks.game;

import com.github.smudgge.algorithms.AlgorithmBasic;
import com.github.smudgge.controllers.Bot;
import com.github.smudgge.controllers.Player;
import com.github.smudgge.game.ChessColour;
import com.github.smudgge.game.ChessGame;
import com.github.smudgge.game.layout.BoardLayoutDefault;
import com.github.smudgge.positions.BoardSize;
import com.github.smudgge.positions.ModularPosition;

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
