package com.plantpocalypse;

public class Room {
    private String name;  //name of room
    private String[] neighboringRooms; //names of neighboring rooms
    //private String description;  //short descr
    //private HashMap<String, Item> items = new HashMap<String, Item>(); //items in the room

    public Room(String name, String[] neighboringRooms) {
        this.name = name;
        this.neighboringRooms = neighboringRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getNeighboringRooms() {
        return neighboringRooms;
    }

    public void setNeighboringRooms(String[] neighboringRooms) {
        this.neighboringRooms = neighboringRooms;
    }
}
