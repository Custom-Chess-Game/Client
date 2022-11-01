package com.github.smuddgge.game.pieces.standered;

import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessBoardTile;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.ChessMove;
import com.github.smuddgge.game.pieces.Piece;
import com.github.smuddgge.positions.TilePosition;

import java.util.ArrayList;

public class Pawn extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link Pawn}
     * @param colour Colour of the chess piece
     */
    public Pawn(ChessColour colour) {
        super(colour);
    }

    @Override
    public String getPathWhite() {
        return "src/main/resources/pieces/white_pawn.png";
    }

    @Override
    public String getPathBlack() {
        return "src/main/resources/pieces/black_pawn.png";
    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public ArrayList<ChessBoardTile> getValidPositions(ChessBoard board, ChessBoardTile tile) {
        ArrayList<ChessBoardTile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        tiles.add(board.getTile(position.addVector(0, 1, this.getColour())));

        // Check if the piece has not moved
        if (!this.hasMoved()) {
            tiles.add(board.getTile(position.addVector(0, 2, this.getColour())));
        }

        return tiles;
    }

    @Override
    public ArrayList<ChessBoardTile> getTakePositions(ChessBoard board, ChessBoardTile tile) {
        ArrayList<ChessBoardTile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        tiles.add(board.getTile(position.addVector(1, 1, this.getColour())));
        tiles.add(board.getTile(position.addVector(-1, 1, this.getColour())));

        return tiles;
    }

    /**
     * Used to test if a chess move is en passant
     * @param chessMove A chess move to test for en passant
     * @return True if the move is en passant
     */
    public static boolean isEnPassant(ChessMove chessMove) {
        if (!(chessMove.getPiece() instanceof Pawn)) return false;
        return chessMove.getDistanceInY() == 2;
    }
}
