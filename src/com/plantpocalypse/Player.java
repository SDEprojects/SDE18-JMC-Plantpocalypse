package com.plantpocalypse;

import java.util.List;

public class Player {
    String currentRoom; // Might change to Room type
    int movesMade;
    int totalHealth;
    int currentHealth;
    List<String> inventory;

//    public Player() {
//
//    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
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
