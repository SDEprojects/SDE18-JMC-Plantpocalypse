package com.plantpocalypse.model;

import com.plantpocalypse.model.items.*;
import com.plantpocalypse.util.*;

import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.List;

public enum Game {
    GAME_INSTANCE;

    private final int ALLOWED_MOVES = 20;

    private Player player;
    private Item rambutan1, rambutan2, brassKey, ironKey, steelKey, weedKiller, floorPlan;
    private PlantMonster cactus, poisonIvy;
    private Room outside, foyer, diningRoom, kitchen, guestRoom, livingRoom, bathroom, library, greenHouseOne, hiddenOffice,
            upperHall, masterBedroom, masterBathroom, laboratory, greenHouseTwo;
    private HashMap<String, Room> mansion;

    /**
     * Loads assets for the game to run properly:
     * Instantiates Player, Rooms and other necessary assets.
     */
    public void loadAssets() {

        //loadGame();
        loadRooms();
        loadItems();
        loadMonsters();
        addItemsToRooms();
        addMonstersToRooms();
        connectRooms();
        //player = new Player(mansion.get("Outside"));
        player = new Player(outside);
    }

    /**
     * Instantiates all of the rooms in the mansion.
     */
    private void loadRooms() {
//        StaxRoomParser readRooms = new StaxRoomParser();
//        mansion = readRooms.readRoomsXML("./resources/newGame/rooms.xml");
        mansion = new HashMap<>();

        outside = new Room("Outside");
        foyer = new Room("Foyer");
        foyer.toggleLock();
        diningRoom = new Room("Dining Room");
        kitchen = new Room("Kitchen");
        livingRoom = new Room("Living Room");
        guestRoom = new Room("Guest Room");
        bathroom = new Room("Bathroom");
        greenHouseOne = new Room("Green House Floor 1");
        library = new Room("Library");
        library.toggleLock();
        hiddenOffice = new Room("Hidden Office");
        //hiddenOffice.toggleLock();
        upperHall = new Room("Upper Hall");
        upperHall.toggleLock();
        masterBedroom = new Room("Master Bedroom");
        masterBathroom = new Room("Master Bathroom");
        laboratory = new Room("Laboratory");
        greenHouseTwo = new Room("Green House Floor 2");

        mansion.put("Outside", outside);
        mansion.put("Foyer", foyer);
        mansion.put("Dining Room", diningRoom);
        mansion.put("Kitchen", kitchen);
        mansion.put("Guest Room", guestRoom);
        mansion.put("Living Room", livingRoom);
        mansion.put("Bathroom", bathroom);
        mansion.put("Library", library);
        mansion.put("Green House F1", greenHouseOne);
        mansion.put("Hidden Office", hiddenOffice);
        mansion.put("Upper Hall", upperHall);
        mansion.put("Master Bedroom", masterBedroom);
        mansion.put("Master Bathroom", masterBathroom);
        mansion.put("Laboratory", laboratory);
        mansion.put("Green House F2", greenHouseTwo);
    }

    private void loadItems() {
//        StaxItemParser readItems = new StaxItemParser();
//        readItems.readItemsXML("./resources/newGame/items.xml",mansion);

        rambutan1 = new Food("rambutan", 1);
        rambutan2 = new Food("rambutan", 1);

        brassKey = new Key("brass key", foyer);
        ironKey = new Key("iron key", upperHall);
        steelKey = new Key("steel key", library);
        weedKiller = new WeedKiller("weed killer");

        floorPlan = new FloorPlan("floor plan");
//        Item journal1 = new Journal("Journal 1");
//        Item tornPage = new Journal("Torn Page");
    }

    private void loadMonsters() {
//        StaxMonsterParser readMonsters = new StaxMonsterParser();
//        readMonsters.readMonstersXML("./resources/newGame/monsters.xml",mansion);
        cactus = new PlantMonster("Cactus", 10);
        poisonIvy = new PlantMonster("Poison Ivy", 2);
    }

    private void connectRooms() {
//        StaxAdjacentRoomParser readAdjacentRooms = new StaxAdjacentRoomParser();
//        readAdjacentRooms.readAdjacentRoomsXML("./resources/newGame/adjacentRooms.xml",mansion);

        HashMap<String, Room> outsideAdjRooms = new HashMap<>();
        outsideAdjRooms.put("north", foyer);
        outside.setNeighboringRooms(outsideAdjRooms);

        HashMap<String, Room> foyerAdjRooms = new HashMap<>();
        foyerAdjRooms.put("north", livingRoom);
        foyerAdjRooms.put("east", library);
        foyerAdjRooms.put("south", outside);
        foyerAdjRooms.put("west", diningRoom);
        foyerAdjRooms.put("up", upperHall);
        foyer.setNeighboringRooms(foyerAdjRooms);

        HashMap<String, Room> diningRoomAdjRooms = new HashMap<>();
        diningRoomAdjRooms.put("north", kitchen);
        diningRoomAdjRooms.put("east", foyer);
        diningRoom.setNeighboringRooms(diningRoomAdjRooms);

        HashMap<String, Room> kitchenAdjRooms = new HashMap<>();
        kitchenAdjRooms.put("east", livingRoom);
        kitchenAdjRooms.put("south", diningRoom);
        kitchenAdjRooms.put("up", masterBedroom);
        kitchen.setNeighboringRooms(kitchenAdjRooms);

        HashMap<String, Room> livingRoomAdjRooms = new HashMap<>();
        livingRoomAdjRooms.put("north", bathroom);
        livingRoomAdjRooms.put("east", greenHouseOne);
        livingRoomAdjRooms.put("south", foyer);
        livingRoomAdjRooms.put("west", kitchen);
        livingRoomAdjRooms.put("northwest", guestRoom);
        livingRoom.setNeighboringRooms(livingRoomAdjRooms);

        HashMap<String, Room> guestRoomAdjRooms = new HashMap<>();
        guestRoomAdjRooms.put("southeast", livingRoom);
        guestRoom.setNeighboringRooms(guestRoomAdjRooms);

        HashMap<String, Room> bathroomAdjRooms = new HashMap<>();
        bathroomAdjRooms.put("south", livingRoom);
        bathroom.setNeighboringRooms(bathroomAdjRooms);

        HashMap<String, Room> greenHouseOneAdjRooms = new HashMap<>();
        greenHouseOneAdjRooms.put("west", livingRoom);
        greenHouseOne.setNeighboringRooms(greenHouseOneAdjRooms);

        HashMap<String, Room> libraryAdjRooms = new HashMap<>();
        libraryAdjRooms.put("east", hiddenOffice);
        libraryAdjRooms.put("west", foyer);
        library.setNeighboringRooms(libraryAdjRooms);

        HashMap<String, Room> hiddenOfficeAdjRooms = new HashMap<>();
        hiddenOfficeAdjRooms.put("west", library);
        hiddenOffice.setNeighboringRooms(hiddenOfficeAdjRooms);

        HashMap<String, Room> upperHallAdjRooms = new HashMap<>();
        upperHallAdjRooms.put("northwest", masterBedroom);
        upperHallAdjRooms.put("southwest", masterBathroom);
        upperHallAdjRooms.put("east", laboratory);
        upperHall.setNeighboringRooms(upperHallAdjRooms);

        HashMap<String, Room> masterBedroomAdjRooms = new HashMap<>();
        masterBedroomAdjRooms.put("east", upperHall);
        masterBedroomAdjRooms.put("south", masterBathroom);
        masterBedroom.setNeighboringRooms(masterBedroomAdjRooms);

        HashMap<String, Room> masterBathroomAdjRooms = new HashMap<>();
        masterBathroomAdjRooms.put("north", masterBedroom);
        masterBathroomAdjRooms.put("east", upperHall);
        masterBathroom.setNeighboringRooms(masterBathroomAdjRooms);

        HashMap<String, Room> laboratoryAdjRooms = new HashMap<>();
        laboratoryAdjRooms.put("north", greenHouseTwo);
        laboratoryAdjRooms.put("west", upperHall);
        laboratory.setNeighboringRooms(laboratoryAdjRooms);

        HashMap<String, Room> greenHouseTwoAdjRooms = new HashMap<>();
        greenHouseTwoAdjRooms.put("south", laboratory);
        greenHouseTwo.setNeighboringRooms(greenHouseTwoAdjRooms);
    }

    private void addItemsToRooms() {
        outside.addItem("rambutan", rambutan1);
        outside.addItem("brass key", brassKey);

        diningRoom.addItem("rambutan", rambutan2);

//        bathroom.addItem("Journal 1", journal1);

        greenHouseOne.addItem("steel key", steelKey);

//        masterBedroom.addItem("Torn Page", tornPage);

        masterBathroom.addItem("iron key", ironKey);

        laboratory.addItem("weed killer", weedKiller);

        livingRoom.addItem("floor plan", floorPlan);
    }

    private void addMonstersToRooms() {
        greenHouseOne.setMonster(cactus);
        guestRoom.setMonster(poisonIvy);
    }

    public boolean checkLostGame() {
        /* Change to real lose condition when possible */
        return player.getMovesMade() >= ALLOWED_MOVES
                || !player.isAlive();
    }

    public boolean checkGameOver() {
        /* Change to real game over condition when possible */
        return player.getCurrentRoom().getName().equals("Hidden Office")
                || player.getMovesMade() >= ALLOWED_MOVES
                || !player.isAlive();
    }

    public void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./resources/saveGame/00.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            out.writeObject(mansion);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./resources/saveGame/00.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("./resources/saveGame/00.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            player = (Player) in.readObject();
            mansion = (HashMap<String, Room>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Player class not found");
            e.printStackTrace();
            return;
        }
    }

    /* GETTERS AND SETTERS */
    public Player getPlayer() {
        return player;
    }

    public int getAllowedMoves() {
        return ALLOWED_MOVES;
    }

    public HashMap<String, Room> getMansion() {
        return mansion;
    }
}
