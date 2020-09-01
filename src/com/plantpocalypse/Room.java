package com.plantpocalypse;

import java.util.HashMap;

public class Room {
    private String name;
    private String description;
    private boolean isLocked = false;

    private Action action;
    private HashMap<String, Room> neighboringRooms;
    private HashMap<String, Item> items = new HashMap<String, Item>();

    /* CONSTRUCTORS */
    public Room(String name, Action action) {
        setName(name);
        setDescription("This is the " + name);
        setAction(action);
    }

    // Might use later, for now just calling setNeighboringRooms in Game.
    public Room(String name, HashMap<String, Room> neighboringRooms, Action action) {
        setName(name);
        setAction(action);
        setDescription("This is the " + name);
        setNeighboringRooms(neighboringRooms);
    }

    /* BUSINESS METHODS */
    public void addItem(String itemName, Item item){
        items.put(itemName, item);
    }

    public Item getItem(String itemName){
        Item pickedUpItem = items.get(itemName);
        items.remove(itemName);
        return pickedUpItem;
    }

    public void enterRoom(Player player) {
        action.entryEvent(player,this);
    }

    public void displayItems() {
        items.entrySet().forEach( entry -> {
            System.out.println( entry.getKey() + " => " + entry.getValue().getName() );
        });
    }

    /* GETTERS AND SETTERS */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void toggleLock() {
        this.isLocked = !isLocked;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public HashMap<String, Room> getNeighboringRooms() {
        return neighboringRooms;
    }

    public void setNeighboringRooms(HashMap<String, Room> neighboringRooms) {
        this.neighboringRooms = neighboringRooms;
    }

    public HashMap<String, Item> getItems() {
        return this.items;
    }

    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }
}
