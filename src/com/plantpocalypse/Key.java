package com.plantpocalypse;

public class Key extends Item {
    private Room roomKeyUnlocks;

    /* CONSTRUCTORS */
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
