package com.github.smuddgge;

import com.github.smuddgge.pages.Page;
import com.github.smuddgge.pages.simple.MainMenu;

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
        Client client = new Client() {
            @Override
            public Page getStartingPage() {
                return new MainMenu();
            }
        };

        // Start the client application
        client.start();
    }
}