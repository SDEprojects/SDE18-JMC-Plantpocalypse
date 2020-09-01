package com.plantpocalypse.gameclient;

import com.plantpocalypse.*;
import com.plantpocalypse.util.TextParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum Game {
    GAME_INSTANCE;

    private final int ALLOWED_MOVES = 10;

    private Player player;
    private Room outside, foyer, diningRoom, kitchen, guestRoom, livingRoom, bathroom, library, greenHouseOne, hiddenOffice,
            upperHall, masterBedroom, masterBathroom, laboratory, greenHouseTwo;
    private HashMap<String, Room> mansion;
    boolean winCondition = false;

    /**
     * Loads assets for the game to run properly:
     * Instantiates Player, Rooms and other necessary assets.
     */
    public void loadAssets() {
        loadRooms();
        player = new Player(outside);

    }

    private void loadRooms() {
        mansion = new HashMap<>();

        Item rambutan1 = new Food("rambutan");
        Item brassKey = new Key("brass key");
        outside = new Room("Outside", new ActionDefault());
        outside.addItem("rambutan", rambutan1);
        outside.addItem("brass key", brassKey);
//        outside.toggleLock();

        foyer = new Room("Foyer", new ActionFoyer());

        Item rambutan2 = new Food("rambutan");
        diningRoom = new Room("Dining Room",new ActionDefault());
        diningRoom.addItem("rambutan", rambutan2);

        kitchen = new Room("Kitchen",new ActionDefault());

        livingRoom = new Room("Living Room",new ActionDefault());

        guestRoom = new Room("Guest Room",new ActionDefault());

//        Item journal1 = new Journal("Journal 1");
        bathroom = new Room("Bathroom",new ActionDefault());
//        bathroom.addItem("Journal 1", journal1);

        Item steelKey = new Key("Steel Key");
        greenHouseOne = new Room("Green House Floor 1",new ActionDefault());
        greenHouseOne.addItem("Steel Key", steelKey);

        library = new Room("Library",new ActionLibrary());
//        library.toggleLock();

        hiddenOffice = new Room("Hidden Office",new ActionDefault());
//        hiddenOffice.toggleLock();

        upperHall = new Room("Upper Hall",new ActionDefault());

//        Item tornPage = new Journal("Torn Page");
        masterBedroom = new Room("Master Bedroom",new ActionDefault());
//        masterBedroom.addItem("Torn Page", tornPage);

        Item ironKey = new Key("Iron Key");
        masterBathroom = new Room("Master Bathroom",new ActionDefault());
        masterBathroom.addItem("Iron Key", ironKey);

        Item weedKiller = new Key("Weed Killer");
        laboratory = new Room("Laboratory",new ActionDefault());
        laboratory.addItem("Weed Killer", weedKiller);

        greenHouseTwo = new Room("Green House Floor 2",new ActionDefault());


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
     * Calls all necessary functions for game to perform in the sequence
     * that it should. Will continue looping until Player's win condition
     * is true.
     */
    public void startGame() {
        titleScreen();
        introDialogue();

        player.pickUpItem("rambutan");

        // loop until Player beats the game
        for (int i = 0; i < ALLOWED_MOVES; i++) {
            System.out.println("Player current room: " + player.getCurrentRoom().getName());

            player.getCurrentRoom().getNeighboringRooms().forEach( (k,v) -> System.out.println(k + ", " + v.getName()));
            player.getCurrentRoom().displayItems();

            if (player.getCurrentRoom().getName().equals("Hidden Office")) { // Win condition will really be if player uses elixir
                break;
            }

            playGame();
        }

        endingDialogue();
    }

    private void playGame() {
        player.interact();
    }



    private void titleScreen() {
        Dialogue.titleScreenDialogue();
    }

    private void introDialogue() {
        Dialogue.introDialogue();
    }

    private void endingDialogue() {
        Dialogue.endingDialogue();
    }

}
