package com.plantpocalypse;

import com.plantpocalypse.gameclient.Game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Room currentRoom; // Might change to Room type
    private Action currentAction;
    private int movesMade;
    //private int totalHealth = 5;
    private int currentHealth = MAX_HEALTH;
    private List<Item> inventory;
    static final int MAX_HEALTH = 10;



    public Player(Room startingLocation) {
//        System.out.println("Setting player current location to: " + startingLocation.getName());
        currentRoom = startingLocation;
        currentAction = startingLocation.getAction();
        inventory = new ArrayList<Item>();
    }

    /**
     * Will put an item in the Player's inventory if it is in the Player's current room.
     * @param itemName Name of the item to be passed in as a key to a HashMap to get a Item object.
     */
    public void pickUpItem(String itemName){
        Item pickedUpItem = currentRoom.getItem(itemName);
        inventory.add(pickedUpItem);
        System.out.println("picked up " + inventory.get(0).getName());
    }

    public void eatItem(String itemName){
        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i).getName().equals(itemName)){
                inventory.remove(i);
                int health = getCurrentHealth()+1;
                if (health <= MAX_HEALTH){
                setCurrentHealth(health);
                }
                else {
                    setCurrentHealth(MAX_HEALTH);
                }
            }
            System.out.println("Omnomnom! Must have been organic");
        }
    }

    public void getHurt(){
        int health = getCurrentHealth() - 1;
        if (health >0){
            setCurrentHealth(health);
        }
        else {
            Dialogue.endingDialogue();
        }
        System.out.println("Ouch!");
    }

    public void getPoisoned(){
        int demage = (int) (Math.random()*10);
        int health = (getCurrentHealth() - demage);
        if (health >0){
            setCurrentHealth(health);
        }
        else {
            Dialogue.endingDialogue();
        }
        System.out.println("Oh no! That was poisonous! Health demage: " + demage);
        System.out.println(getCurrentHealth());;
    }

    public void displayInventory() {
        for(int i=0; i <inventory.size(); i++){
            System.out.println((i+1) + ". " + inventory.get(i).getName() + "\n");
        }
    }


    public void move(Room nextRoom) {
        currentRoom = nextRoom;
        currentAction = currentRoom.getAction();
        currentRoom.enterRoom(this);
    }

    /* GETTERS AND SETTERS */

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

//    public int getTotalHealth() {
//        return totalHealth;
//    }
//
//    public void setTotalHealth(int totalHealth) {
//        this.totalHealth = totalHealth;
//    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory() {
        this.inventory = inventory;
    }

}
