package com.github.smuddgge.game;

import com.github.smuddgge.engine.Console;
import com.github.smuddgge.game.pieces.Piece;
import com.github.smuddgge.positions.TilePosition;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a chess move
 */
public class ChessMove {

    /**
     * Where the piece has moved to and from
     */
    private final ChessBoardTile from;
    private final ChessBoardTile to;

    /**
     * The piece that was moved
     */
    private final Piece piece;

    /**
     * Used to create a new chess move
     * @param from The tile the piece was before the move
     * @param to The tile the piece has moved to
     */
    public ChessMove(ChessBoardTile from, ChessBoardTile to, Piece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    /**
     * @return Position where the piece moved from
     */
    public TilePosition getFrom() {
        return this.from.getTilePosition();
    }

    /**
     * @return Position where the piece moved to
     */
    public TilePosition getTo() {
        return this.to.getTilePosition();
    }

    /**
     * @return The piece moved
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Get the move as a string
     * @return (X axis, Y axis as base 26)
     */
    public String asString() {
        return "(" + getTo().getX() + ", " + convertToLetters(getTo().getY()) + ")";
    }

    /**
     * Essential converting a number to base 26
     * @param number Number to convert
     * @return Converted number
     */
    private String convertToLetters(int number) {
        String[] letters = "abcdefghijklmnopqrstuvwxyz".split("");
        StringBuilder converted = new StringBuilder();

        while (number > 0) {
            converted.insert(0, letters[(number % letters.length) - 1]);
            number = number / letters.length;
        }

        return converted.toString();
    }

    /**
     * Used to debug and show the info contained in the chess move
     */
    public void asDebug() {
        Console.print("--- Debug ---");
        Console.print("From : " + this.from.getTilePosition().asString());
        Console.print("To : " + this.to.getTilePosition().asString());
    }

    /**
     * Used to convert the class into a string
     */
    public String convertClassToString() {
        return this.from.getTilePosition().getX() + ":" + this.from.getTilePosition().getY() + ":" +
                this.to.getTilePosition().getX() + ":" + this.to.getTilePosition().getY();
    }

    /**
     * Used to turn a string into a chess move class
     * @return Chess move class instance
     */
    public static ChessMove convertStringToClass(String data, ChessBoard board) {
        ArrayList<String> split = new ArrayList<>(Arrays.asList(data.split(":")));

        TilePosition first = new TilePosition(Integer.parseInt(split.get(0)), Integer.parseInt(split.get(1)));

        return new ChessMove(
                board.getTile(first),
                board.getTile(new TilePosition(Integer.parseInt(split.get(2)), Integer.parseInt(split.get(3)))),
                board.getTile(first).getPiece()
        );
    }
}
