package tests.game;

import com.github.smuddgge.results.ResultChecker;
import com.github.smuddgge.results.ResultNotNull;
import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessBoardTile;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.positions.TilePosition;
import mocks.game.MockChessBoard;
import org.junit.jupiter.api.Test;

/**
 * Used to test the chess board
 */
public class TestChessBoard {

    @Test
    public void testGetTile() throws Exception {
        ChessBoard chessBoard = MockChessBoard.get();

        TilePosition tilePosition = new TilePosition(0, 0);
        chessBoard.addTile(new ChessBoardTile(ChessColour.WHITE, tilePosition));

        new ResultChecker().expect(chessBoard.getTile(tilePosition), new ResultNotNull());
    }
}
