package com.github.smuddgge.algorithms;

import com.github.smuddgge.engine.Console;
import com.github.smuddgge.engine.ConsoleColour;
import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.game.ChessMove;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h2>Represents a chess algorithm</h2>
 * A controller can use an algorithm to get the next move
 */
public abstract class Algorithm {

    /**
     * Counts how many instances of the chess board were crated
     */
    private int instances = 0;

    /**
     * Used to calculate the score of the board, in favour of a players colour,
     * by returning a value given the instance of the chess board
     * @param board Instance of the chess board to calculate
     * @param colour In context of this players colour
     * @return A value determining if they are winning
     *         A positive value means they are winning
     *         A negative value means the other player is winning
     */
    public abstract int score(ChessBoard board, ChessColour colour);

    /**
     * Used to get the best move given the instance of the board
     * @param board Instance of the chess board
     * @param scoring The player which the move should benefit
     * @return The best move, given the algorithm, to make on the board
     */
    public ChessMove getMove(ChessBoard board, ChessColour scoring) {
        HashMap<Integer, ChessMove> moves = new HashMap<>();
        AtomicInteger movesToWaitFor = new AtomicInteger();
        int depth = 4;

        long startTime = System.currentTimeMillis();
        AtomicInteger threadsCreated = new AtomicInteger();

        // Loop though the possible moves
        for (ChessMove move : board.getPossibleMoveForColour(scoring)) {
            movesToWaitFor.getAndIncrement();

            new Thread(() -> {
                threadsCreated.getAndIncrement();

                // Make the move
                ChessBoard temp = new ChessBoard(board);
                temp.makeSilentMove(move);

                // Score the move
                int score = this.calculate(depth, temp, scoring, scoring, -1000, 1000);

                // Add the move to a map
                moves.put(score, move);

                // Remove from counter
                movesToWaitFor.getAndDecrement();

            }).start();
        }

        // Wait for all the threads to complete
        while (movesToWaitFor.get() != 0) {
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        Map.Entry<Integer, ChessMove> entry = Collections.max(moves.entrySet(), Map.Entry.comparingByKey());

        long timeTaken = (System.currentTimeMillis() - startTime) / 1000;

        String summary = ConsoleColour.PINK + "Algorithm \n" + ConsoleColour.WHITE +
                "Tree Depth : " + ConsoleColour.YELLOW + depth + "\n" + ConsoleColour.WHITE +
                "Scored Move (Value) : " + ConsoleColour.YELLOW + entry.getKey() + "\n" + ConsoleColour.WHITE +
                "Chess Board Instances Created : " + ConsoleColour.YELLOW + this.instances + "\n"  + ConsoleColour.WHITE +
                "Threads Created : " + ConsoleColour.YELLOW + threadsCreated + "\n"  + ConsoleColour.WHITE +
                "Time Taken : " + ConsoleColour.YELLOW + timeTaken + "s";

        Console.print(summary);

        // Return the move that scores the highest
        return entry.getValue();
    }

    /**
     * The main algorithm to calculate the tree
     * This algorithm is NegaMax
     * @param depth Maximum depth of the tree
     * @param board The instance of the board to score
     * @param turn Who's turn it currently is
     * @param scoring The player of which the move should benefit
     * @param alfa The minimum value
     * @param beta The maximum value
     * @return The score of the board
     */
    protected int calculate(int depth, ChessBoard board, ChessColour turn, ChessColour scoring, int alfa, int beta) {
        // When the maximum depth has been reached
        if (depth == 0) {
            instances++;
            return this.score(board, scoring);
        }

        int max = -1000;

        for (ChessMove move : board.getPossibleMoveForColour(turn)) {

            ChessBoard temp = new ChessBoard(board);
            temp.makeSilentMove(move);

            int value = this.calculate(depth - 1, temp, ChessColour.opposite(turn), scoring, alfa, beta);

            if (value > max) max = value;
            if (max > alfa) alfa = max;
            if (alfa >= beta) return beta;
        }

        return max;
    }
}
