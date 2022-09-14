package com.github.smuddgge;

import com.github.smuddgge.algorithms.AlgorithmBasic;
import com.github.smuddgge.controllers.Bot;
import com.github.smuddgge.controllers.Player;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.CustomChessGame;
import com.github.smuddgge.game.layout.BoardLayoutDefault;
import com.github.smuddgge.positions.BoardSize;
import com.github.smuddgge.positions.ModularPosition;

import java.util.ArrayList;

/**
 * Application pointer
 */
public class Main {

    /**
     * Starting point
     * @param args No arguments required
     */
    public static void main(String[] args) {

        new SimpleClient();

    }
}