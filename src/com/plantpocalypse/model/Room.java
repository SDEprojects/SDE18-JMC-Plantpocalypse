package com.plantpocalypse.model;

import com.plantpocalypse.model.items.Item;
import com.plantpocalypse.model.items.NPC;

import java.io.Serializable;
import java.util.HashMap;

public class Room implements Serializable {
    private String name;
    private String description;
    private boolean isLocked = false;
    //private boolean doesHaveMonster = false;
    // Initialized this here to use code from xml parse
    // using setNeighboringRooms seems to just overwrite it
    // with no issues
    private HashMap<String, Room> neighboringRooms = new HashMap<>();
    private HashMap<String, Item> items = new HashMap<String, Item>();
    private PlantMonster monster;
    private NPC character;
    private int color;
    private String NPCdialogue;





    /* CONSTRUCTORS */
    public Room() {

    };
    public Room(String name) {
        setName(name);
        setDescription("This is the " + name);
    }

    // Might use later, for now just calling setNeighboringRooms in Game.
    public Room(String name, HashMap<String, Room> neighboringRooms) {
        setName(name);
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

    public HashMap<String, Room> getNeighboringRooms() {
        return neighboringRooms;
    }

    public void setNeighboringRooms(HashMap<String, Room> neighboringRooms) {
        this.neighboringRooms = neighboringRooms;
    }

    public void addNeighboringRoom(String direction, Room room) {
        this.neighboringRooms.put(direction, room);
    }

    public HashMap<String, Item> getItems() {
        return this.items;
    }

    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }

    public PlantMonster getMonster() {
        return monster;
    }

    public void setMonster(PlantMonster monster) {
        this.monster = monster;
        //doesHaveMonster = true;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getNPCdialogue() {
        return NPCdialogue;
    }

    public void setNPCdialogue(String NPCdialogue) {
        this.NPCdialogue = NPCdialogue;
    }



    public NPC getCharacter() {
        return character;
    }

    public void setCharacter(String name) {
        this.character = new NPC(name);
    }

    // toString

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isLocked=" + isLocked + '\'' +
                '}';
    }
}
