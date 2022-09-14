package com.github.smuddgge;

import com.github.smuddgge.pages.MainMenu;
import com.github.smuddgge.pages.Page;

/**
 * Represents a {@link Client}
 */
public class SimpleClient extends Client {

    @Override
    public Page getStartingPage() {
        return new MainMenu();
    }
}