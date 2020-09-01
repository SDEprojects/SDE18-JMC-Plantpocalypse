package com.plantpocalypse;

import java.util.List;

public interface Action {
    public void entryEvent(Player player, Room room);

    // DEFAULT METHODS

    default public void examine(Item item) {
        System.out.println(item.getDescription());
    }
}
