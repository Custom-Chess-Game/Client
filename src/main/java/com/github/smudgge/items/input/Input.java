package com.github.smudgge.items.input;

import com.github.smudgge.items.Item;
import com.github.smudgge.positions.ModularPosition;

import javax.swing.*;
import java.awt.*;

/**
 * Represents an input box
 */
public class Input extends Item {

    /**
     * The instance of the liable to put on the frame
     */
    private JTextField textField;

    /**
     * Used to create a new instance of some text
     * @param modularPosition The position of the text
     */
    public Input(ModularPosition modularPosition) {
        super(modularPosition);

        this.create();
    }

    /**
     * Used to create and update the label instance
     */
    public void create() {
        // <html> Allows the text to wrap
        this.textField = new JTextField();

        this.textField.setBounds(
                this.modularPosition.getPos1().getX(),
                this.modularPosition.getPos1().getY(),
                this.modularPosition.getWidth(),
                this.modularPosition.getHeight()
        );

        int fontSize = (int) (10 + (Math.min(this.modularPosition.getWidth(), this.modularPosition.getHeight()) * 0.1));
        this.textField.setFont(new Font(Font.DIALOG, Font.BOLD, fontSize));

        this.textField.setForeground(Color.white);
        this.textField.setBackground(Color.black);
        this.textField.setOpaque(true);
        this.textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    }

    /**
     * Used to get the input from the input box
     * @return String input
     */
    public String getInput() {
        return this.textField.getText();
    }

    @Override
    public void updateItem() {
        this.textField.setBounds(
                this.modularPosition.getPos1().getX(),
                this.modularPosition.getPos1().getY(),
                this.modularPosition.getWidth(),
                this.modularPosition.getHeight()
        );
    }

    @Override public void onHover() {}
    @Override public void onNotHover() {}

    @Override
    public void render(JPanel frame) {
        this.create();
        frame.add(this.textField);
    }

    @Override
    public Component getComponent() {
        return this.textField;
    }
}
