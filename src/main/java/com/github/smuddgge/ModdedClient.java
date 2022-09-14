package com.github.smuddgge;

import com.github.smuddgge.game.CustomChessGame;
import com.github.smuddgge.pages.Page;
import com.github.smuddgge.pages.custom.CustomMainMenu;

import java.util.ArrayList;

/**
 * Represents a modded client
 */
public abstract class ModdedClient extends Client {

    /**
     * Tells the client this is a modded client
     */
    private static boolean enabled = false;

    /**
     * The instance of the modded client
     */
    private static ModdedClient instance;

    /**
     * Used to initiate the modded client
     */
    public ModdedClient() {
        ModdedClient.enabled = true;
        ModdedClient.instance = this;
    }

    @Override
    public Page getStartingPage() {
        return new CustomMainMenu();
    }

    /**
     * Used to get the list of custom chess games they can play
     * @return List of custom chess games
     */
    public abstract ArrayList<CustomChessGame> getChessGames();

    /**
     * Used to get it the client is modded
     * @return True if the client is modded
     */
    public static boolean isModded() {
        return ModdedClient.enabled;
    }

    /**
     * Used to get the instance of the modded client
     * @return The instance of the modded client
     */
    public static ModdedClient getClient() {
        return ModdedClient.instance;
    }
}
