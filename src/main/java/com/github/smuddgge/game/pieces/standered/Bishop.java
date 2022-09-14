package com.github.smuddgge.game.pieces.standered;

import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessBoardTile;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.pieces.Piece;
import com.github.smuddgge.positions.TilePosition;

import java.util.ArrayList;

public class Bishop extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link Bishop}
     * @param colour Colour of the chess piece
     */
    public Bishop(ChessColour colour) {
        super(colour);
    }

    @Override
    public String getPathWhite() {
        return "src/main/resources/pieces/white_bishop.png";
    }

    @Override
    public String getPathBlack() {
        return "src/main/resources/pieces/black_bishop.png";
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public ArrayList<ChessBoardTile> getValidPositions(ChessBoard board, ChessBoardTile tile) {
        ArrayList<ChessBoardTile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        // The amount of squares the piece can move diagonally
        int minimum = Math.min(board.getAmountOfTilesX(), board.getAmountOfTilesY());

        // Add all diagonal squares
        for (int index = 1; index < minimum; index++) {
            tiles.add(board.getTile(position.addVector(index, index, this.getColour())));
            tiles.add(board.getTile(position.addVector(index, -index, this.getColour())));
            tiles.add(board.getTile(position.addVector(-index, index, this.getColour())));
            tiles.add(board.getTile(position.addVector(-index, -index, this.getColour())));
        }

        return tiles;
    }

    @Override
    public ArrayList<ChessBoardTile> getTakePositions(ChessBoard board, ChessBoardTile tile) {
        return this.getValidPositions(board, tile);
    }
}
