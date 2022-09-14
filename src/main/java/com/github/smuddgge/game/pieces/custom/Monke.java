package com.github.smuddgge.game.pieces.custom;

import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessBoardTile;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.pieces.Piece;
import com.github.smuddgge.game.pieces.standered.Bishop;
import com.github.smuddgge.game.pieces.standered.Rook;
import com.github.smuddgge.positions.TilePosition;

import java.util.ArrayList;

public class Monke extends Piece {

    /**
     * Create a new instance of {@link Piece}
     *
     * @param colour Colour of the chess piece
     */
    public Monke(ChessColour colour) {
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
        return 10;
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

        ChessBoardTile bishop = new ChessBoardTile(tile.tileColour, new TilePosition(position.getX(), position.getY()));
        bishop.setPiece(new Bishop(this.getColour()));
        tiles.addAll(bishop.getPiece().getValidPositions(board, bishop));

        ChessBoardTile rook = new ChessBoardTile(tile.tileColour, new TilePosition(position.getX(), position.getY()));
        rook.setPiece(new Rook(this.getColour()));
        tiles.addAll(rook.getPiece().getValidPositions(board, bishop));

        return tiles;
    }

    @Override
    public ArrayList<ChessBoardTile> getTakePositions(ChessBoard board, ChessBoardTile tile) {
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

        ChessBoardTile bishop = new ChessBoardTile(tile.tileColour, new TilePosition(position.getX(), position.getY()));
        bishop.setPiece(new Bishop(this.getColour()));
        tiles.addAll(bishop.getPiece().getValidPositions(board, bishop));

        ChessBoardTile rook = new ChessBoardTile(tile.tileColour, new TilePosition(position.getX(), position.getY()));
        rook.setPiece(new Rook(this.getColour()));
        tiles.addAll(rook.getPiece().getValidPositions(board, bishop));

        return tiles;
    }
}
