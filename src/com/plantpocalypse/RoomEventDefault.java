package com.plantpocalypse;

public class RoomEventDefault implements RoomEvent {
    @Override
    public void entryEvent() {
        System.out.println("You have entered a new room. Nothing in particular stands out about it.");
    }
}
