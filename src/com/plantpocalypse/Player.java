package com.plantpocalypse;

import com.plantpocalypse.items.Food;
import com.plantpocalypse.items.Item;
import com.plantpocalypse.items.Key;
import com.plantpocalypse.util.Dialogue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
    private Room currentRoom;
    private List<Item> inventory;

    private int movesMade = 0;
    static final int MAX_HEALTH = 10;
    private int currentHealth = MAX_HEALTH;
    private boolean isAlive = true;

    /* CONSTRUCTORS */
    public Player(Room startingLocation) {
        setCurrentRoom(startingLocation);
        inventory = new ArrayList<Item>();
    }

    /* BUSINESS METHODS */
    /**
     * Will put an item in the Player's inventory if it is in the Player's current room.
     * @param itemName Name of the item to be passed in as a key to a HashMap to get a Item object.
     */
    public boolean pickUpItem(String itemName) {
        Item pickedUpItem = currentRoom.getItem(itemName);

        if (pickedUpItem != null) {
            inventory.add(pickedUpItem);
            return true;
        }

        return false;
    }

    public void getHurt(int attack){
        int health = getCurrentHealth() - attack;

        if (health > 0){
            setCurrentHealth(health);
        }
        else {
            isAlive = false;
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
        System.out.println("Player Inventory: ");
        for(int i=0; i <inventory.size(); i++){
            System.out.println((i+1) + ". " + inventory.get(i).getName() + "\n");
        }
    }

    public boolean move(Room nextRoom) {
        if (!nextRoom.isLocked()) {
            currentRoom = nextRoom;
            setMovesMade(getMovesMade() + 1);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean use(String itemName) {
        Item selectedItem = retrieveItemFromInventory(itemName);

        if (selectedItem != null) {
            String selectedItemType = selectedItem.getClass().getSimpleName();

            switch (selectedItemType) {
                case "Food":
                    eat(itemName);
                    break;

                case "Key":
                    Key key = (Key) selectedItem;
                    unlockDoor(key);
                    break;

                case "Journal":
                    System.out.println("You read the journal");
                    break;

                default:
                    System.out.println("You cannot use that item, silly.");
            }
            return true;
        }
        return false;
    }

    /**
     * Will restore Player health for the amount of health that
     * the Food restores.
     * @param itemName The Food object that the player is eating
     */
    public boolean eat(String itemName) {
        Item selectedItem = retrieveItemFromInventory(itemName);

        if (selectedItem != null) {
            if (selectedItem.getClass().getSimpleName().equals("Food")) {
                Food selectedFood = (Food)selectedItem;

                int health = getCurrentHealth() + selectedFood.getHealthRestored();

                if (health <= getMaxHealth()) {
                    setCurrentHealth(health);
                } else {
                    setCurrentHealth(getMaxHealth());
                }
                System.out.println("Omnomnom! Must have been organic");
            }
            else {
                System.out.println("You ate the " + selectedItem.getName() + ", what's wrong with you?");
            }
            removeItemFromInventory(selectedItem.getName());
            return true;
        }

        return false;
    }

    public void unlockDoor(Key key) {
        if (key != null) {
            boolean validRoom = getCurrentRoom().getNeighboringRooms().containsValue(key.getRoomKeyUnlocks());
            if (validRoom && key.getRoomKeyUnlocks().isLocked()) {
                key.getRoomKeyUnlocks().toggleLock();
                removeItemFromInventory(key.getName());
                System.out.println("\nYou unlocked the " + key.getRoomKeyUnlocks().getName());
            }
        }
    }

    public boolean examine(String itemName) {
        Item item = retrieveItemFromInventory(itemName);

        if (item != null) {
            System.out.println(item.getDescription());
            return true;
        }

        return false;
    }

    private Item retrieveItemFromInventory(String itemName) {
        Item result = null;
        Iterator<Item> iterator = inventory.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getName().equals(itemName)) {
                result = item;
                break;
            }
        }
        return result;
    }

    private void removeItemFromInventory(String itemName) {
        Iterator<Item> iterator = inventory.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getName().equals(itemName)) {
                inventory.remove(item);
                break;
            }
        }
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

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory() {
        this.inventory = inventory;
    }

}
