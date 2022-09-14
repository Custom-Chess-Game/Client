package tests.algorithms;

import com.github.smuddgge.results.ResultChecker;
import com.github.smuddgge.results.ResultNotNull;
import com.github.smuddgge.algorithms.Algorithm;
import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.ChessMove;
import mocks.game.MockChessBoard;
import org.junit.jupiter.api.Test;

/**
 * Used to test the algorithm class
 */
public class TestAlgorithm {

    @Test
    public void testGetMove() throws Exception {
        ChessBoard chessboard = MockChessBoard.get();

        Algorithm algorithm = new Algorithm() {
            @Override
            public int score(ChessBoard board, ChessColour colour) {
                return 0;
            }
        };

        ChessMove chessMove = algorithm.getMove(chessboard, ChessColour.WHITE);

        new ResultChecker().expect(chessMove.getPiece(), new ResultNotNull());
    }
}
