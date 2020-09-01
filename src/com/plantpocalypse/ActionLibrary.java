package com.plantpocalypse;

public class ActionLibrary implements Action {
    @Override
    public void entryEvent(Player player, Room room) {
        System.out.println("You enter the room to see a large library");
    }
}
