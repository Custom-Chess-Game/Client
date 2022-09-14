package com.github.smuddgge.game.pieces.standered;

import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessBoardTile;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.pieces.Piece;
import com.github.smuddgge.positions.TilePosition;

import java.util.ArrayList;

public class Rook extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link Rook}
     * @param colour Colour of the chess piece
     */
    public Rook(ChessColour colour) {
        super(colour);
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
        return 5;
    }

    @Override
    public ArrayList<ChessBoardTile> getValidPositions(ChessBoard board, ChessBoardTile tile) {
        ArrayList<ChessBoardTile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        // Tiles in the X direction
        for (int xIndex = 1; xIndex < board.getAmountOfTilesX(); xIndex++) {
            tiles.add(board.getTile(position.addVector(xIndex, 0, this.getColour())));
            tiles.add(board.getTile(position.addVector(-xIndex, 0, this.getColour())));
        }

        // Tiles in the Y direction
        for (int yIndex = 1; yIndex < board.getAmountOfTilesY(); yIndex++) {
            tiles.add(board.getTile(position.addVector(0, yIndex, this.getColour())));
            tiles.add(board.getTile(position.addVector(0, -yIndex, this.getColour())));
        }

        return tiles;
    }

    @Override
    public ArrayList<ChessBoardTile> getTakePositions(ChessBoard board, ChessBoardTile tile) {
        return this.getValidPositions(board, tile);
    }
}
