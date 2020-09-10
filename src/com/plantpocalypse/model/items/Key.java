package com.plantpocalypse.model.items;

import com.plantpocalypse.model.Room;

public class Key extends Item {
    private Room roomKeyUnlocks;

    /* CONSTRUCTORS */
    public Key() {}
    public Key(String name, Room roomKeyUnlocks){
        super(name);
        setRoomKeyUnlocks(roomKeyUnlocks);
    }

    /* ABSTRACT METHODS */
    @Override
    public void use() {
        System.out.println(getName() + " used");
    }

    /* BUSINESS METHODS */

    /* GETTERS AND SETTERS */
    public Room getRoomKeyUnlocks() {
        return roomKeyUnlocks;
    }

    public void setRoomKeyUnlocks(Room roomKeyUnlocks) {
        this.roomKeyUnlocks = roomKeyUnlocks;
    }
}
