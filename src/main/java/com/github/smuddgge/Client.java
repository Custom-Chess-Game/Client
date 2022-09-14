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
     * The instance of the main menu
     */
    private static Page mainMenu;

    /**
     * Used to start the client
     */
    public void start() {
        // Set up the config
        Client.config = new Config();

        // Setup main menu
        Client.mainMenu = this.getStartingPage();

        new Application("Chess");
    }

    /**
     * Used to get the default main menu
     * @return Returns the instance of the manu menu pageg
     */
    public static Page getMainMenu() {
        return Client.mainMenu;
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
