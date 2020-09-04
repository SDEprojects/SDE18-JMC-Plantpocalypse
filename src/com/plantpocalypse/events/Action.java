package com.plantpocalypse.events;

import com.plantpocalypse.Player;
import com.plantpocalypse.Room;
import com.plantpocalypse.items.Item;

public interface Action {
    public void entryEvent(Player player, Room room);

    // DEFAULT METHODS

    default public void examine(Item item) {
        System.out.println(item.getDescription());
    }
}
