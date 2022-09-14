package tests.game;

import com.github.smuddgge.results.ResultChecker;
import com.github.smuddgge.results.ResultInstanceOf;
import com.github.smuddgge.results.ResultNotNull;
import com.github.smuddgge.controllers.Controller;
import com.github.smuddgge.game.ChessGame;
import mocks.game.MockChessGame;
import org.junit.jupiter.api.Test;

/**
 * Used to test the chess game class
 */
public class TestChessGame {

    @Test
    public void testGetBoard() throws Exception {
        ChessGame chessGame = MockChessGame.get();

        new ResultChecker().expect(chessGame.getBoard(), new ResultNotNull());
    }

    @Test
    public void testGetWhoseTurn() throws Exception {
        ChessGame chessGame = MockChessGame.get();

        new ResultChecker().expect(chessGame.getWhoseTurn(), new ResultInstanceOf(Controller.class));
    }
}
