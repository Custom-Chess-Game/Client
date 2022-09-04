package com.github.smudgge.game.pieces.standered;

import com.github.smudgge.game.ChessBoard;
import com.github.smudgge.game.ChessBoardTile;
import com.github.smudgge.game.ChessColour;
import com.github.smudgge.game.pieces.Piece;
import com.github.smudgge.positions.TilePosition;

import java.util.ArrayList;

public class Knight extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link Knight}
     * @param colour Colour of the chess piece
     */
    public Knight(ChessColour colour) {
        super(colour);

        this.getOptions().canJump = true;
    }

    @Override
    public String getPathWhite() {
        return "src/main/resources/pieces/white_knight.png";
    }

    @Override
    public String getPathBlack() {
        return "src/main/resources/pieces/black_knight.png";
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public ArrayList<ChessBoardTile> getValidPositions(ChessBoard board, ChessBoardTile tile) {
        ArrayList<ChessBoardTile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        tiles.add(board.getTile(position.addVector(1, 2, this.getColour())));
        tiles.add(board.getTile(position.addVector(1, -2, this.getColour())));
        tiles.add(board.getTile(position.addVector(-1, 2, this.getColour())));
        tiles.add(board.getTile(position.addVector(-1, -2, this.getColour())));

        tiles.add(board.getTile(position.addVector(2, 1, this.getColour())));
        tiles.add(board.getTile(position.addVector(2, -1, this.getColour())));
        tiles.add(board.getTile(position.addVector(-2, 1, this.getColour())));
        tiles.add(board.getTile(position.addVector(-2, -1, this.getColour())));

        return tiles;
    }

    @Override
    public ArrayList<ChessBoardTile> getTakePositions(ChessBoard board, ChessBoardTile tile) {
        return this.getValidPositions(board, tile);
    }
}
