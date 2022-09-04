package com.github.smudgge.game.pieces.standered;

import com.github.smudgge.game.ChessBoard;
import com.github.smudgge.game.ChessBoardTile;
import com.github.smudgge.game.ChessColour;
import com.github.smudgge.game.pieces.Piece;
import com.github.smudgge.positions.TilePosition;

import java.util.ArrayList;

public class King extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link King}
     * @param colour Colour of the chess piece
     */
    public King(ChessColour colour) {
        super(colour);

        this.getOptions().endsGame = true;
    }

    @Override
    public String getPathWhite() {
        return "src/main/resources/pieces/white_king.png";
    }

    @Override
    public String getPathBlack() {
        return "src/main/resources/pieces/black_king.png";
    }

    @Override
    public int getValue() {
        return 999;
    }

    @Override
    public ArrayList<ChessBoardTile> getValidPositions(ChessBoard board, ChessBoardTile tile) {
        ArrayList<ChessBoardTile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        tiles.add(board.getTile(position.addVector(0, 1, this.getColour())));
        tiles.add(board.getTile(position.addVector(0, -1, this.getColour())));
        tiles.add(board.getTile(position.addVector(1, 0, this.getColour())));
        tiles.add(board.getTile(position.addVector(-1, 0, this.getColour())));

        tiles.add(board.getTile(position.addVector(1, 1, this.getColour())));
        tiles.add(board.getTile(position.addVector(-1, 1, this.getColour())));
        tiles.add(board.getTile(position.addVector(1, -1, this.getColour())));
        tiles.add(board.getTile(position.addVector(-1, -1, this.getColour())));
        return tiles;
    }

    @Override
    public ArrayList<ChessBoardTile> getTakePositions(ChessBoard board, ChessBoardTile tile) {
        return this.getValidPositions(board, tile);
    }
}
