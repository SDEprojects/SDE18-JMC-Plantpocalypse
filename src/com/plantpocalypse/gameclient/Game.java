package com.plantpocalypse.gameclient;

import com.plantpocalypse.Player;
import com.plantpocalypse.Room;

import java.util.HashMap;

public enum Game {
    GAME_INSTANCE;

    private Player player;
    private Room outside, foyer, diningRoom, kitchen, guestRoom, livingRoom, bathroom, library, greenHouseOne, hiddenOffice,
            upperHall, masterBedroom, masterBathroom, laboratory, greenHouseTwo;
    private HashMap<String, Room> mansion;

    /**
     * Loads assets for the game to run properly:
     * Instantiates Player, Rooms and other necessary assets.
     */
    public void loadAssets() {
        loadRooms();
        player = new Player(outside);

//        mansion.entrySet().forEach( entry -> {
//            System.out.println(entry.getKey() + " > " + entry.getValue().getName());
//        });

//        System.out.println("Game has been loaded.");
    }

    private void loadRooms() {
        mansion = new HashMap<>();

        outside = new Room("Outside");
        foyer = new Room("Foyer");
        diningRoom = new Room("Dining Room");
        kitchen = new Room("Kitchen");
        guestRoom = new Room("Guest Room");
        livingRoom = new Room("Living Room");
        bathroom = new Room("Bathroom");
        library = new Room("Library");
        greenHouseOne = new Room("Green House Floor 1");
        hiddenOffice = new Room("Hidden Office");
        upperHall = new Room("Upper Hall");
        masterBedroom = new Room("Master Bedroom");
        masterBathroom = new Room("Master Bathroom");
        laboratory = new Room("Laboratory");
        greenHouseTwo = new Room("Green House Floor 2");

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

    /**
     * Will
     */
    public void startGame() {
        titleScreen();
        introDialogue();


        System.out.println("Player current room: " + player.getCurrentRoom().getName());

        player.getCurrentRoom().getNeighboringRooms().entrySet().forEach( entry -> {
            System.out.println(entry.getKey() + " > " + entry.getValue().getName());
        });

        player.move(player.getCurrentRoom().getNeighboringRooms().get("north"));

        System.out.println("Player new room: " + player.getCurrentRoom().getName());

        player.getCurrentRoom().getNeighboringRooms().entrySet().forEach( entry -> {
            System.out.println(entry.getKey() + " > " + entry.getValue().getName());
        });


        // loop until Player beats the game

        endingDialogue();
    }

    private void titleScreen() {
//        System.out.println("This is the title screen");
    }

    private void introDialogue() {
//        System.out.println("This is the intro dialogue");
    }

    private void endingDialogue() {
//        System.out.println("This is the ending dialogue");
    }

}
