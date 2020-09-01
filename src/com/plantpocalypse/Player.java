package com.plantpocalypse;

import com.plantpocalypse.gameclient.Game;
import com.plantpocalypse.util.TextParser;

import java.util.*;

public class Player {
    private Room currentRoom;
    private Action currentAction;
    private List<Item> inventory;

    private int movesMade = 0;
    static final int MAX_HEALTH = 10;
    private int currentHealth = MAX_HEALTH;

    /* CONSTRUCTORS */
    public Player(Room startingLocation) {
        setCurrentRoom(startingLocation);
        setCurrentAction(startingLocation.getAction());
        inventory = new ArrayList<Item>();
    }

    /* BUSINESS METHODS */
    /**
     * Will put an item in the Player's inventory if it is in the Player's current room.
     * @param itemName Name of the item to be passed in as a key to a HashMap to get a Item object.
     */
    public void pickUpItem(String itemName) {
        Item pickedUpItem = currentRoom.getItem(itemName);
        inventory.add(pickedUpItem);
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
        int damage = (int) (Math.random() * 10);
        int health = (getCurrentHealth() - damage);
        if (health >0){
            setCurrentHealth(health);
        }
        else {
            Dialogue.endingDialogue();
        }
        System.out.println("Oh no! That was poisonous! Health damage: " + damage);
        System.out.println(getCurrentHealth());
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

    public void eat(String itemName) {
        if(retrieveItemFromInventory(itemName) != null) {
            int health = getCurrentHealth() + 1;

            if (health <= getMaxHealth()) {
                setCurrentHealth(health);
            } else {
                setCurrentHealth(getMaxHealth());
            }
            System.out.println("Omnomnom! Must have been organic");
        }
    }

    public void examine(String itemName) {
        Item item = retrieveItemFromInventory(itemName);
        if (item != null) {
            currentAction.examine(item);
        } else {
            System.out.println("You do not have that item!");
        }
    }

    private Item retrieveItemFromInventory(String itemName) {
        Item result = null;
        Iterator<Item> iterator = inventory.iterator();
        inventory.forEach(item -> System.out.println(item.getName()));
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getName().equals(itemName)) {
                result = item;
                inventory.remove(item);
                break;
            }
        }
        return result;
    }

    /* GETTERS AND SETTERS */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }

    public int getMovesMade() {
        return movesMade;
    }

    public void setMovesMade(int movesMade) {
        this.movesMade = movesMade;
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

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
