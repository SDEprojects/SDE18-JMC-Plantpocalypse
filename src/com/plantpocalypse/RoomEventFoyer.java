package com.plantpocalypse;

import com.plantpocalypse.RoomEvent;

public class RoomEventFoyer implements RoomEvent {
    @Override
    public void entryEvent(Room room) {
        System.out.println("You look in awe at the multitude of exotic plants in the grand entry of the mansion.");
    }
}
