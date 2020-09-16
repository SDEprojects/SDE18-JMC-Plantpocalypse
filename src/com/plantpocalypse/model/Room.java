package com.plantpocalypse.model;

import com.plantpocalypse.model.items.Item;
import com.plantpocalypse.model.items.NPC;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

public class Room implements Serializable {
    private String name;
    private String description;
    private boolean isLocked = false;
    //private boolean doesHaveMonster = false;
    // Initialized this here to use code from xml parser
    // using setNeighboringRooms seems to just overwrite it
    // with no issues
    private HashMap<String, Room> neighboringRooms = new HashMap<>();
    private HashMap<String, Item> items = new HashMap<String, Item>();
    private PlantMonster monster;
    private String path;
    private NPC character;
    private BufferedImage mapImage;
    private Boolean hasVisited;
    private int floorNumber;

    public NPC getCharacter() {
        return character;
    }

    public void setCharacter(String name) {
        this.character = new NPC(name);
    }

    public  BufferedImage changeAlpha(BufferedImage mapImage, double amount) {

            for (int x = 0; x < mapImage.getWidth(); x++) {
                for (int y = 0; y < mapImage.getHeight(); y++) {
                    //
                    int argb = mapImage.getRGB(x, y); //always returns TYPE_INT_ARGB
                    int alpha = (argb >> 24) & 0xff;  //isolate alpha

                    alpha *= amount; //similar distortion to tape saturation (has scrunching effect, eliminates clipping)
                    alpha &= 0xff;      //keeps alpha in 0-255 range

                    argb &= 0x00ffffff; //remove old alpha info
                    argb |= (alpha << 24);  //add new alpha info
                    mapImage.setRGB(x, y, argb);
                }
            }
            return mapImage;
        }

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

    public String getPath() { return path; }

    public void setPath(String path) {
        this.path = path;
    }

    public BufferedImage getMapImage() {
        return mapImage;
    }

    public void createMapImage() {
        //TODO create an else statement that returns the alphatized image if room has visited
        try {
            setMapImage(ImageIO.read(new File(this.getPath())));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void setMapImage(BufferedImage mapImage) {
        this.mapImage = mapImage;
    }

    public void updateMapImage() {
        if (hasVisited() == true) {
            setMapImage(changeAlpha(getMapImage(),.5));
        }
    }

    public Boolean hasVisited() {
        return hasVisited;
    }

    public void setHasVisited(Boolean hasVisited) {
        this.hasVisited = hasVisited;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
    // toString

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isLocked=" + isLocked + '\'' +
                ", hasVisited=" + hasVisited + '\'' +
                ", mapImage=" + mapImage + '\'' +
                '}';
    }
}
