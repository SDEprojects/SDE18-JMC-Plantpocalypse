package com.plantpocalypse;

import java.util.List;

public interface Action {
    public void entryEvent(Player player, Room room);

    // DEFAULT METHODS
    default public void eat(Player player) {
        int health = player.getCurrentHealth()+1;
        if (health <= player.MAX_HEALTH){
            player.setCurrentHealth(health);
        }
        else {
            player.setCurrentHealth(player.MAX_HEALTH);
        }
        System.out.println("Omnomnom! Must have been organic");
    }

    default public void examine(Item item) {
        System.out.println(item.getDescription());
    }
}
