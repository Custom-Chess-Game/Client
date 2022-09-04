package com.github.smudgge.configuration;

import com.github.smuddgge.configuration.YamlConfiguration;

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
}
