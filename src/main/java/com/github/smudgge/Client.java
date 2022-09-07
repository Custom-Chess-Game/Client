package com.github.smudgge;

import com.github.smudgge.configuration.Config;
import com.github.smudgge.engine.Application;

/**
 * Represents the client
 */
public class Client {

    /**
     * Instance of the configuration file
     */
    private static Config config;

    /**
     * Used to create a new instance of the chess application
     */
    public Client() {

        // Set up the config
        Client.config = new Config();

        new Application("Chess");
    }

    /**
     * Used to get main {@link Config}
     */
    public static Config getConfig() {
        return Client.config;
    }
}
