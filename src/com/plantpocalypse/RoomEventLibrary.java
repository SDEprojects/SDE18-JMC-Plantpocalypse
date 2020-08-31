package com.plantpocalypse;

import com.plantpocalypse.RoomEvent;

public class RoomEventLibrary implements RoomEvent {
    @Override
    public void entryEvent(Room room) {
        System.out.println("You enter the room to see a large library");
    }
}
