package com.plantpocalypse.gameclient;

import com.plantpocalypse.Player;
import com.plantpocalypse.Room;

import java.util.HashMap;

public enum Game {
    GAME_INSTANCE;

    Player player;
    Room outside, foyer, diningRoom, kitchen, guestRoom, livingRoom, bathroom, library, greenHouseOne, hiddenOffice;
    Room upperHall, masterBedroom, masterBathroom, laboratory, greenHouseTwo;

    HashMap<String, Room> mansion;

    /**
     * Loads assets for the game to run properly:
     * Instantiates Player, Rooms and other necessary assets.
     */
    public void loadAssets() {
        player = new Player();
        loadRooms();

        System.out.println("Game has been loaded.");
    }

    private void loadRooms() {
        String[] outsideAdjRooms = {"Foyer"};
        outside = new Room("Outside", outsideAdjRooms);

        String[] foyerAdjRooms = {"Dining Room", "Living Room", "Library", "Outside", "Upper Hall"};
        foyer = new Room("Foyer", foyerAdjRooms);

        String[] diningRoomAdjRooms = {"Kitchen", "Foyer"};
        diningRoom = new Room("Dining Room", diningRoomAdjRooms);

        String[] kitchenAdjRooms = {"Dining Room", "Living Room"};
        kitchen = new Room("Kitchen", kitchenAdjRooms);

        String[] guestRoomAdjRooms = {"Living Room"};
        guestRoom = new Room("Guest Room", guestRoomAdjRooms);

        String[] livingRoomAdjRooms = {"Guest Room", "Kitchen", "Foyer", "Green House Floor 1", "Bathroom"};
        livingRoom = new Room("Living Room", livingRoomAdjRooms);

        String[] bathroomAdjRooms = {"Living Room"};
        bathroom = new Room("Bathroom", bathroomAdjRooms);

        String[] libraryAdjRooms = {"Foyer", "Hidden Office"};
        library = new Room("Library", libraryAdjRooms);

        String[] greenHouseAdjRooms = {"Living Room"};
        greenHouseOne = new Room("Green House Floor 1", greenHouseAdjRooms);

        String[] hiddenOfficeAdjRooms = {"Library"};
        hiddenOffice = new Room("Hidden Office", hiddenOfficeAdjRooms);

        String[] upperHallAdjRooms = {"Foyer", "Master Bedroom", "Master Bathroom", "Laboratory"};
        upperHall = new Room("Upper Hall", upperHallAdjRooms);

        String[] masterBedRoomAdjRooms = {"Master Bathroom", "Upper Hall"};
        masterBedroom = new Room("Master Bedroom", masterBedRoomAdjRooms);

        String[] masterBathRoomAdjRooms = {"Master Bedroom", "Upper Hall"};
        masterBathroom = new Room("Master Bathroom", masterBathRoomAdjRooms);

        String[] laboratoryRoomAdjRooms = {"Upper Hall", "Green House Floor 2"};
        laboratory = new Room("Laboratory", laboratoryRoomAdjRooms);

        String[] greenHouseTwoAdjRooms = {"Master Bedroom", "Upper Hall"};
        greenHouseTwo = new Room("Green House Floor 2", greenHouseTwoAdjRooms);
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
