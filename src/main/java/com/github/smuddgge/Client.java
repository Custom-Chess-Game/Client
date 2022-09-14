package com.github.smuddgge;

import com.github.smuddgge.configuration.Config;
import com.github.smuddgge.engine.Application;
import com.github.smuddgge.pages.Page;

/**
 * Represents the client
 */
public abstract class Client {

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

        Application.setPage(this.getStartingPage());
    }

    /**
     * Used to get the page where the application will begin
     * @return Instance of a {@link Page}
     */
    public abstract Page getStartingPage();

    /**
     * Used to get main {@link Config}
     */
    public static Config getConfig() {
        return Client.config;
    }
}