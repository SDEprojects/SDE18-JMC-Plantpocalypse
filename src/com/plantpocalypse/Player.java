package com.plantpocalypse;

import java.util.List;

public class Player {
    private Room currentRoom; // Might change to Room type
    private int movesMade;
    private int totalHealth;
    private int currentHealth;
    private List<String> inventory;

    public Player(Room startingLocation) {
//        System.out.println("Setting player current location to: " + startingLocation.getName());
        currentRoom = startingLocation;
    }

    public void move(Room nextRoom) {
        this.currentRoom = nextRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getMovesMade() {
        return movesMade;
    }

    public void setMovesMade(int movesMade) {
        this.movesMade = movesMade;
    }

    public int getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }
}
