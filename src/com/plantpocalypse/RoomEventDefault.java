package com.plantpocalypse;

public class RoomEventDefault implements RoomEvent {
    @Override
    public void entryEvent(Room room) {
        System.out.println("You have entered the " + room.getName() +". Nothing in particular stands out about it.");
    }
}
