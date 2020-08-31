package com.plantpocalypse;

import com.plantpocalypse.RoomEvent;

public class RoomEventLibrary implements RoomEvent {
    @Override
    public void entryEvent() {
        System.out.println("You enter the room to see a large library");
    }
}
