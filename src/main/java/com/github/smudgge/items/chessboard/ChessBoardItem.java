package com.github.smudgge.items.chessboard;

import com.github.smudgge.game.ChessBoard;
import com.github.smudgge.game.ChessBoardTile;
import com.github.smudgge.items.Item;
import com.github.smudgge.positions.ModularPosition;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the interface for the chessboard
 */
public abstract class ChessBoardItem extends Item {

    /**
     * The chessboard panel
     */
    private JButton panel = new JButton();

    /**
     * Used to create a new chessboard interface
     * @param modularPosition The position of the chessboard
     */
    public ChessBoardItem(ModularPosition modularPosition) {
        super(modularPosition);
    }

    /**
     * Creates the chessboard based on its position
     */
    private void create() {
        this.panel = new JButton();

        this.panel.setBounds(
                this.getItemRegion().getMin().getX(),
                this.getItemRegion().getMin().getY(),
                this.modularPosition.getWidth(), this.modularPosition.getHeight()
        );

        this.panel.setBackground(Color.white);
        this.getBoard().render(this.panel);

        // Adding a listener to all buttons
        for (Component component : this.panel.getComponents()) {
            if (!(component instanceof JButton button)) continue;
            button.addActionListener(event -> this.onClick(this.getBoard().getTileAt(this.getMouseLocation())));
        }
    }

    /**
     * Used to get the board
     */
    public abstract ChessBoard getBoard();

    /**
     * When the mouse is hovering over a tile
     */
    public abstract void onTileHover(ChessBoardTile tile);

    /**
     * When the mouse clicks a tile
     */
    public abstract void onClick(ChessBoardTile tile);

    /**
     * When the application ticks
     */
    public abstract void tick();

    /**
     * Item hover events
     */
    @Override public void onHover() {}
    @Override public void onNotHover() {}

    @Override
    public void updateItem() {
        this.tick();
        if (this.getBoard() == null) return;
        this.onTileHover(this.getBoard().getTileAt(this.getMouseLocation()));
    }

    @Override
    public void render(JPanel frame) {
        this.create();
        frame.add(this.panel);
    }

    @Override
    public Component getComponent() {
        return this.panel;
    }
}
