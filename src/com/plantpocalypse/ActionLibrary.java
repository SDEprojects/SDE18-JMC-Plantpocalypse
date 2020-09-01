package com.plantpocalypse;

public class ActionLibrary implements Action {
    @Override
    public void entryEvent(Room room) {
        System.out.println("You enter the room to see a large library");
    }
}
