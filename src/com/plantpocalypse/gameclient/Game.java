package com.plantpocalypse.gameclient;

import com.plantpocalypse.*;
import com.plantpocalypse.util.ConsoleDisplay;

import java.util.HashMap;

public enum Game {
    GAME_INSTANCE;

    private final int ALLOWED_MOVES = 20;
    boolean lostGame = false;

    private Player player;
    private PlantMonster monster;
    private Item rambutan1, rambutan2, brassKey, ironKey, steelKey, weedKiller;
    private Room outside, foyer, diningRoom, kitchen, guestRoom, livingRoom, bathroom, library, greenHouseOne, hiddenOffice,
            upperHall, masterBedroom, masterBathroom, laboratory, greenHouseTwo;
    private HashMap<String, Room> mansion;

    /**
     * Loads assets for the game to run properly:
     * Instantiates Player, Rooms and other necessary assets.
     */
    public void loadAssets() {
        loadRooms();
        loadItems();
        addItemsToRooms();
        connectRooms();
        player = new Player(outside);
    }

    /**
     * Instantiates all of the rooms in the mansion.
     */
    private void loadRooms() {
        mansion = new HashMap<>();

        outside = new Room("Outside", new ActionDefault());
        foyer = new Room("Foyer", new ActionFoyer());
        foyer.toggleLock();
        diningRoom = new Room("Dining Room",new ActionDefault());
        kitchen = new Room("Kitchen",new ActionDefault());
        livingRoom = new Room("Living Room",new ActionDefault());
        guestRoom = new Room("Guest Room",new ActionGuestRoom());
        bathroom = new Room("Bathroom",new ActionDefault());
        greenHouseOne = new Room("Green House Floor 1",new ActionGreenHouseOne());
        library = new Room("Library",new ActionLibrary());
//        library.toggleLock();
        hiddenOffice = new Room("Hidden Office",new ActionDefault());
//        hiddenOffice.toggleLock();
        upperHall = new Room("Upper Hall",new ActionDefault());
        masterBedroom = new Room("Master Bedroom",new ActionDefault());
        masterBathroom = new Room("Master Bathroom",new ActionDefault());
        laboratory = new Room("Laboratory",new ActionDefault());
        greenHouseTwo = new Room("Green House Floor 2",new ActionDefault());

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
        rambutan1 = new Food("rambutan", 1);
        rambutan2 = new Food("rambutan", 1);

        brassKey = new Key("brass key", foyer);
        ironKey = new Key("iron key", upperHall);
        steelKey = new Key("steel key", library);
        weedKiller = new Key("weed killer", greenHouseOne);

//        Item journal1 = new Journal("Journal 1");
//        Item tornPage = new Journal("Torn Page");
    }

    private void connectRooms() {
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

        greenHouseOne.addItem("Steel Key", steelKey);

//        masterBedroom.addItem("Torn Page", tornPage);

        masterBathroom.addItem("Iron Key", ironKey);

        laboratory.addItem("Weed Killer", weedKiller);
    }

    /**
     * Calls all necessary functions for game to perform in the sequence
     * that it should. Will continue looping until Player's win condition
     * is true.
     */
    public void startGame() {
        ConsoleDisplay.welcomeScreen();
        titleScreen();
        intro();
        /* Loop until Player beats the game */
        while (!player.getCurrentRoom().getName().equals("Hidden Office")) {
            currentRoom();
            itemsInRoom();
            neighboringRooms();

            nextCommand();

            if (player.getMovesMade() >= ALLOWED_MOVES) {
                lostGame = true;
                break;
            }
        }

        if (lostGame) {
            lost();
        }
        else {
            won();
        }

        ending();
    }

    private void nextCommand() {
        player.setMovesMade(player.getMovesMade() + 1);
        GameDirector.interact(player);
        //ConsoleDisplay.clearConsole();
    }

    private void currentRoom() {
        System.out.println("Player current room: " + player.getCurrentRoom().getName() + "\n");
    }

    private void titleScreen() {
        Dialogue.titleScreenDialogue();
    }

    private void intro() {
        Dialogue.introDialogue();
    }

    private void ending() {
        Dialogue.endingDialogue();
    }

    private void lost() {
        Dialogue.losingDialogue();
    }

    private void won() {
        Dialogue.winningDialogue();
    }

    /* METHODS FOR TESTING */
    private void itemsInRoom() {
        System.out.println("\nItems in " + player.getCurrentRoom().getName() + ":");
        player.getCurrentRoom().displayItems();
    }

    private void neighboringRooms() {
        System.out.println("Connected Rooms: ");
        player.getCurrentRoom().getNeighboringRooms().forEach( (k,v) -> System.out.println(k + " => " + v.getName()));
    }

}
