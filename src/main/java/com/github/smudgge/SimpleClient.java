package com.github.smudgge;

import com.github.smudgge.pages.MainMenu;
import com.github.smudgge.pages.Page;

/**
 * Represents a {@link Client}
 */
public class SimpleClient extends Client {

    @Override
    public Page getStartingPage() {
        return new MainMenu();
    }
}