package com.plantpocalypse.model;

import com.plantpocalypse.model.items.Food;
import com.plantpocalypse.model.items.Item;
import com.plantpocalypse.model.items.Key;
import com.plantpocalypse.util.ConsoleDisplay;
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
            setCurrentHealth(0);
            isAlive = false;
        }
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

    public boolean displayInventory() {

        if (inventory.size() > 0) {
            return true;
        }

        return false;
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
                case "Food" -> eat(itemName);
                case "Key" -> {
                    Key key = (Key) selectedItem;
                    unlockDoor(key);
                }
                case "Journal" -> System.out.println("You read the journal.");
                case "FloorPlan" -> {
                    System.out.println("You opened the floor plan");
                    seeFloorPlan(itemName);
                }
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
            }
        }
    }

    public boolean examine(String itemName) {
        Item item = retrieveItemFromInventory(itemName);

        if (item != null) {
            return true;
        }

        return false;
    }
    public boolean seeFloorPlan(String itemName) {

        if (itemName != null) {
            ConsoleDisplay.printFloorPlan();
            return true;
        }
        return false;
    }

    public Item retrieveItemFromInventory(String itemName) {
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
