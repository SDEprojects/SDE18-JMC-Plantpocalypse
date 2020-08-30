package com.plantpocalypse.gameclient;

import com.plantpocalypse.Dialogue;
import com.plantpocalypse.Player;
import com.plantpocalypse.Room;
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
     * Calls all necessary functions for game to perform in the sequence
     * that it should. Will continue looping until Player's win condition
     * is true.
     */
    public void startGame() {
        titleScreen();
        introDialogue();

        // loop until Player beats the game
        for (int i = 0; i < ALLOWED_MOVES; i++) {
            System.out.println("Player current room: " + player.getCurrentRoom().getName());
            player.getCurrentRoom().getNeighboringRooms().forEach( (k,v) -> System.out.println(k + ", " + v.getName()));
            doCommand();
        }

        endingDialogue();
    }

    private void doCommand() {
        List<String> input = TextParser.getInput();

        List<String> commands = Arrays.asList("go");
        if (input.size() < 2 || !commands.contains(input.get(0))) {
            System.out.println("Please enter command with correct format: command [option]");
        } else if (input.get(0).equals("go")) {
            go(input.get(1));
        }
    }

    private void go(String direction) {
        //List<String> directions = Arrays.asList("north","northwest","up","west","east");
        HashMap<String, Room> adjacentRooms = player.getCurrentRoom().getNeighboringRooms();
        if (adjacentRooms.containsKey(direction)) {
            player.move(adjacentRooms.get(direction));
        } else {
            System.out.println("Please enter a valid direction.");
        }
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
