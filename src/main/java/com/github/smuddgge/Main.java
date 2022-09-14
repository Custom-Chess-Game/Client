package com.github.smuddgge;

/**
 * Application pointer
 */
public class Main {

    /**
     * Starting point
     * @param args No arguments required
     */
    public static void main(String[] args) {

        // Set up the client
        SimpleClient client = new SimpleClient();

        // Start the client application
        client.start();
    }
}