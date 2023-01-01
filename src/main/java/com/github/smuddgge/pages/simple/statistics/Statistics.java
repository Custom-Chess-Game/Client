package com.github.smuddgge.pages.simple.statistics;

import com.github.smuddgge.items.ItemCollection;
import com.github.smuddgge.pages.Page;

public class Statistics extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    public Statistics() {
        this.itemCollection = new ItemCollection().setColumns(1);



        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}
