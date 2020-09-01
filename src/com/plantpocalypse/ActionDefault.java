package com.plantpocalypse;

public class ActionDefault implements Action {
    @Override
    public void entryEvent(Room room) {
        System.out.println("You have entered the " + room.getName() +". Nothing in particular stands out about it.");
    }
}
