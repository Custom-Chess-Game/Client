package com.github.smudgge.game.pieces.custom;

import com.github.smudgge.game.ChessBoard;
import com.github.smudgge.game.ChessBoardTile;
import com.github.smudgge.game.ChessColour;
import com.github.smudgge.game.pieces.Piece;
import com.github.smudgge.positions.TilePosition;

import java.util.ArrayList;

public class Unicorn extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link Unicorn}
     *
     * @param colour Colour of the chess piece
     */
    public Unicorn(ChessColour colour) {
        super(colour);

        this.getOptions().canJump = true;
    }

    @Override
    public String getPathWhite() {
        return "src/main/resources/pieces/white_rook.png";
    }

    @Override
    public String getPathBlack() {
        return "src/main/resources/pieces/black_rook.png";
    }

    @Override
    public int getValue() {
        return 4;
    }

    @Override
    public ArrayList<ChessBoardTile> getValidPositions(ChessBoard board, ChessBoardTile tile) {
        ArrayList<ChessBoardTile> tiles = new ArrayList<>();

        for (ChessBoardTile temp : board.getAllTiles()) {
            TilePosition vector = new TilePosition(
                    tile.getTilePosition().getX() - temp.getTilePosition().getX(),
                    tile.getTilePosition().getY() - temp.getTilePosition().getY()
            );

            if (Math.abs(vector.getX()) > 3) continue;
            if (Math.abs(vector.getY()) > 3) continue;
            tiles.add(temp);
        }

        return tiles;
    }

    @Override
    public ArrayList<ChessBoardTile> getTakePositions(ChessBoard board, ChessBoardTile tile) {
        return this.getValidPositions(board, tile);
    }
}