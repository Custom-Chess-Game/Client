package com.github.smudgge.configuration;

import java.util.ArrayList;

/**
 * Represents the main config options
 */
public interface ConfigSelection {

    /**
     * The maximum amount of frames per second
     * @return Integer config value
     */
    int getMaxFPS();

    /**
     * The current window size
     * @return Array containing with and height
     */
    ArrayList<Integer> getScreenSize();

    /**
     * Used to get the host ip
     * @return String ip
     */
    String getHost();

    /**
     * Used to get the port which the server is running on
     * @return Integer port
     */
    int getPort();
}
