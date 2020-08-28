package com.plantpocalypse.gameclient;

import com.plantpocalypse.Player;
import com.plantpocalypse.Room;

import java.util.HashMap;

public enum Game {
    GAME_INSTANCE;

    Player player;
    Room outside, foyer, diningRoom, kitchen, guestRoom, livingRoom, bathroom, library, greenHouseOne, hiddenOffice,
            upperHall, masterBedroom, masterBathroom, laboratory, greenHouseTwo;
    HashMap<String, Room> mansion;

    /**
     * Loads assets for the game to run properly:
     * Instantiates Player, Rooms and other necessary assets.
     */
    public void loadAssets() {
        player = new Player();
        loadRooms();

        mansion.entrySet().forEach( entry -> {
            System.out.println(entry.getKey() + " > " + entry.getValue().getName());
        });

        System.out.println("Game has been loaded.");
    }

    private void loadRooms() {
        mansion = new HashMap<>();

        String[] outsideAdjRooms = {"Foyer"};
        outside = new Room("Outside", outsideAdjRooms);
        mansion.put("Outside", outside);

        String[] foyerAdjRooms = {"Dining Room", "Living Room", "Library", "Outside", "Upper Hall"};
        foyer = new Room("Foyer", foyerAdjRooms);
        mansion.put("Foyer", foyer);

        String[] diningRoomAdjRooms = {"Kitchen", "Foyer"};
        diningRoom = new Room("Dining Room", diningRoomAdjRooms);
        mansion.put("Dining Room", diningRoom);

        String[] kitchenAdjRooms = {"Dining Room", "Living Room"};
        kitchen = new Room("Kitchen", kitchenAdjRooms);
        mansion.put("Kitchen", kitchen);

        String[] guestRoomAdjRooms = {"Living Room"};
        guestRoom = new Room("Guest Room", guestRoomAdjRooms);
        mansion.put("Guest Room", guestRoom);

        String[] livingRoomAdjRooms = {"Guest Room", "Kitchen", "Foyer", "Green House Floor 1", "Bathroom"};
        livingRoom = new Room("Living Room", livingRoomAdjRooms);
        mansion.put("Living Room", livingRoom);

        String[] bathroomAdjRooms = {"Living Room"};
        bathroom = new Room("Bathroom", bathroomAdjRooms);
        mansion.put("Bathroom", bathroom);

        String[] libraryAdjRooms = {"Foyer", "Hidden Office"};
        library = new Room("Library", libraryAdjRooms);
        mansion.put("Library", library);

        String[] greenHouseAdjRooms = {"Living Room"};
        greenHouseOne = new Room("Green House Floor 1", greenHouseAdjRooms);
        mansion.put("Green House F1", greenHouseOne);

        String[] hiddenOfficeAdjRooms = {"Library"};
        hiddenOffice = new Room("Hidden Office", hiddenOfficeAdjRooms);
        mansion.put("Hidden Office", hiddenOffice);

        String[] upperHallAdjRooms = {"Foyer", "Master Bedroom", "Master Bathroom", "Laboratory"};
        upperHall = new Room("Upper Hall", upperHallAdjRooms);
        mansion.put("Upper Hall", upperHall);

        String[] masterBedRoomAdjRooms = {"Master Bathroom", "Upper Hall"};
        masterBedroom = new Room("Master Bedroom", masterBedRoomAdjRooms);
        mansion.put("Master Bedroom", masterBedroom);

        String[] masterBathRoomAdjRooms = {"Master Bedroom", "Upper Hall"};
        masterBathroom = new Room("Master Bathroom", masterBathRoomAdjRooms);
        mansion.put("Master Bathroom", masterBathroom);

        String[] laboratoryRoomAdjRooms = {"Upper Hall", "Green House Floor 2"};
        laboratory = new Room("Laboratory", laboratoryRoomAdjRooms);
        mansion.put("Laboratory", laboratory);

        String[] greenHouseTwoAdjRooms = {"Master Bedroom", "Upper Hall"};
        greenHouseTwo = new Room("Green House Floor 2", greenHouseTwoAdjRooms);
        mansion.put("Green House F2", greenHouseTwo);
    }

    /**
     * Will
     */
    public void startGame() {
        titleScreen();
        introDialogue();

        // loop until Player beats the game

        endingDialogue();
    }

    private void titleScreen() {
        System.out.println("This is the title screen");
    }

    private void introDialogue() {
        System.out.println("This is the intro dialogue");
    }

    private void endingDialogue() {
        System.out.println("This is the ending dialogue");
    }

}
