package com.plantpocalypse;

import java.util.HashMap;

public class Room {
    RoomEvent roomEvent;
    private String name;  //name of room
    private HashMap<String, Room> neighboringRooms; //names of neighboring rooms
    //private String description;  //short descr
    private HashMap<String, Item> items = new HashMap<String, Item>(); //items in the room
    private boolean isLocked = false;

    public void addItem(String itemName, Item item){
        items.put(itemName, item);
    }

    public Item getItem(String itemName){
        Item pickedUpItem = items.get(itemName);
        items.remove(itemName);
        return pickedUpItem;
    }

    public Room(String name, RoomEvent roomEvent) {
        this.name = name;
        this.roomEvent = roomEvent;
    }

    public Room(String name, HashMap<String, Room> neighboringRooms) {
        this.name = name;
        this.neighboringRooms = neighboringRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Room> getNeighboringRooms() {
        return neighboringRooms;
    }

    public void setNeighboringRooms(HashMap<String, Room> neighboringRooms) {
        this.neighboringRooms = neighboringRooms;
    }

    public void displayItems() {
        items.entrySet().forEach( entry -> {
            System.out.println( entry.getKey() + " => " + entry.getValue().getName() );
        });
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void toggleLock() {
        this.isLocked = !isLocked;
    }

    public void enterRoom() {
        roomEvent.entryEvent(this);
    }
}
