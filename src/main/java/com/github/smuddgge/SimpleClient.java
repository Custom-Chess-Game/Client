package com.github.smuddgge;

import com.github.smuddgge.pages.Page;
import com.github.smuddgge.pages.simple.MainMenu;

/**
 * Represents a {@link Client}
 */
public class SimpleClient extends Client {

    @Override
    public Page getStartingPage() {
        return new MainMenu();
    }
}