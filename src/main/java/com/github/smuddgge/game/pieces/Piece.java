package com.github.smuddgge.game.pieces;

import com.github.smuddgge.Client;
import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessBoardTile;
import com.github.smuddgge.game.ChessColour;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a chess board piece
 */
public abstract class Piece implements Cloneable {

    /**
     * The colour of the chess piece
     */
    private final ChessColour colour;

    /**
     * The options tied to the piece
     */
    private final PieceOptions options;

    /**
     * Represents if the piece has moved
     */
    private boolean hasMoved;

    /**
     * Create a new instance of {@link Piece}
     * @param colour Colour of the chess piece
     */
    public Piece(ChessColour colour) {
        this.colour = colour;
        this.hasMoved = false;
        this.options = new PieceOptions();
    }

    /**
     * Get image paths
     */
    public abstract String getPathWhite();
    public abstract String getPathBlack();

    /**
     * Used to get the value of the piece that an algorithm can use
     * to determine what is the best move to make
     * @return Value of the piece as an integer
     */
    public abstract int getValue();

    /**
     * Used to get valid positions on the board
     * @return Valid tiles
     */
    public abstract ArrayList<ChessBoardTile> getValidPositions(ChessBoard board, ChessBoardTile tile);

    /**
     * Used to get the positions that the piece can take other pieces from
     * @return Valid tiles
     */
    public abstract ArrayList<ChessBoardTile> getTakePositions(ChessBoard board, ChessBoardTile tile);

    /**
     * Used to get the pieces options
     * @return Piece options
     */
    public PieceOptions getOptions() {
        return options;
    }

    /**
     * Get piece colour
     */
    public ChessColour getColour() {
        return colour;
    }

    /**
     * Used to get if the piece has moved since the start
     * @return True if the piece has moved
     */
    public boolean hasMoved() {
        return this.hasMoved;
    }

    /**
     * Triggered when the piece has moved
     */
    public void setMoved() {
        this.hasMoved = true;
    }

    /**
     * Used to render the piece to the tile panel
     * @param panel The panel of the tile
     */
    public void render(JButton panel) {
        String path = getPathBlack();
        if (this.colour == ChessColour.WHITE) path = getPathWhite();

        // How to screen relates to its default size
        float multiplierX = (float) (Client.getConfig().getScreenSize().get(0) / (double)1000);
        float multiplierY = (float) (Client.getConfig().getScreenSize().get(1) / (double)600);
        float multiplier = Math.min(multiplierX, multiplierY);

        ImageIcon icon = new ImageIcon(path);
        ImageIcon scaled = new ImageIcon(icon.getImage().getScaledInstance((int) (60 * multiplier), (int) (60 * multiplier), Image.SCALE_SMOOTH));

        panel.setIcon(scaled);
        panel.setVisible(true);
    }

    @Override
    public Piece clone() {
        try {
            return (Piece) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}