package com.github.smuddgge.configuration;

import java.util.ArrayList;

/**
 * Represents the main config file
 */
public class Config extends YamlConfiguration implements ConfigSelection {

    /**
     * Creates a new {@link YamlConfiguration}
     */
    public Config() {
        super("config.yml");

        // Load the config
        this.load();
    }

    @Override
    public int getMaxFPS() {
        return this.get().getInt("MaxFPS");
    }

    @Override
    public ArrayList<Integer> getScreenSize() {
        return new ArrayList<>(this.get().getIntList("ScreenSize"));
    }

    @Override
    public String getHost() {
        return this.get().getString("Host");
    }

    @Override
    public int getPort() {
        return this.get().getInt("Port");
    }
}
