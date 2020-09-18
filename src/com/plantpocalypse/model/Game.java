package com.plantpocalypse.model;

import com.plantpocalypse.util.ImageTools;
import com.plantpocalypse.util.reader.AdjacentRoomReader;
import com.plantpocalypse.util.reader.ItemReader;
import com.plantpocalypse.util.reader.MonsterReader;
import com.plantpocalypse.util.reader.RoomReader;
import com.plantpocalypse.view.ComponentMap;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public enum Game {
    GAME_INSTANCE;

    private final int ALLOWED_MOVES = 40;

    private Player player;
    private HashMap<String, Room> mansion;
    public ComponentMap floor1;
    public ComponentMap floor2;
    public ComponentMap floor0;
    private String adjacentRoomsPath = "./resources/newGame/adjacentRooms.xml";
    private String itemsPath = "./resources/newGame/items.xml";
    private String monstersPath = "./resources/newGame/monsters.xml";
    private String roomsPath = "./resources/newGame/rooms.xml";


    /**
     * Loads assets for the game to run properly:
     * Instantiates Player, Rooms and other necessary assets.
     */
    public void loadAssets() {
//        loadGame();
        loadRooms();
        loadItems();
        loadMonsters();
        connectRooms();
        setPlayer("Outside");
//        loadNPC();

    }

    public void loadAssetsTutorial() {
        loadRooms();
        loadItems();
        loadMonsters();
        connectRooms();
        setPlayer("Estate Gates");
    }

    /**
     * Instantiates all of the rooms in the mansion.
     */

//    private void loadNPC(){
//        NpcReader readNPC = new NpcReader();
//        readNPC.readNPCXML("./resources/newGame/NPC.xml");
//
//    }
    private void loadRooms() {
        RoomReader readRooms = new RoomReader();
        mansion = readRooms.readRoomsXML(getRoomsPath());
        loadComponentMaps();
    }

    // Instantiates HashMaps of JPanel components for easy lookup of each room/floor's mini map overlays
    private void loadComponentMaps() {
        floor1 = new ComponentMap();
        floor2 = new ComponentMap();
        floor0 = new ComponentMap();
        // Load outline overlay into each floor's map

        JPanel tempComponent = ImageTools.createJPanelFromPath("./resources/map_labels_floor_1.png");
        floor1.addComponent("labels", tempComponent);
        tempComponent = ImageTools.createJPanelFromPath("./resources/map_labels_floor_2.png");
        floor2.addComponent("labels", tempComponent);

        // Load rooms overlays into map
        mansion.forEach((roomName, room) -> {
            JPanel component = ImageTools.createJPanelFromPath(room.getPath());
            switch (room.getFloorNumber()) {
                case 1: floor1.addComponent(room.getName(), component);
                    break;
                case 2: floor2.addComponent(room.getName(), component);
                    break;
                default: floor0.addComponent(room.getName(), component);
                    break;
            }
        });

        // Load background images into maps

        tempComponent = ImageTools.createJPanelFromPath("./resources/map_background_floor_1.png");
        floor1.addComponent("background", tempComponent);

        tempComponent = ImageTools.createJPanelFromPath("./resources/map_background_floor_2.png");
        floor2.addComponent("background", tempComponent);

    }


    private void loadItems() {
        ItemReader readItems = new ItemReader();
        readItems.readItemsXML(getItemsPath(),mansion);
    }

    private void loadMonsters() {
        MonsterReader readMonsters = new MonsterReader();
        readMonsters.readMonstersXML(getMonstersPath(),mansion);
    }

    private void connectRooms() {
        AdjacentRoomReader readAdjacentRooms = new AdjacentRoomReader();
        readAdjacentRooms.readAdjacentRoomsXML(getAdjacentRoomsPath(),mansion);
    }


    public boolean checkLostGame() {
        return player.getMovesMade() >= ALLOWED_MOVES
                || !player.isAlive();
    }

    public boolean checkTutorialComplete() {
        return player.tutorialComplete();
    }

    public boolean checkPlayerWon() {
        return player.playerWon();
    }

    public boolean checkGameOver() {
        return player.getMovesMade() >= ALLOWED_MOVES
                || !player.isAlive()
                || player.playerWon()
                || player.tutorialComplete();
    }

    public void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./resources/saveGame/00.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            out.writeObject(mansion);
            out.writeObject(floor1);
            out.writeObject(floor2);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./resources/saveGame/00.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("./resources/saveGame/00.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            player = (Player) in.readObject();
            mansion = (HashMap<String, Room>) in.readObject();
            floor1 = (ComponentMap) in.readObject();
            floor2 = (ComponentMap) in.readObject();
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

    public void setPlayer(String startingRoomName) {
        this.player = new Player(mansion.get(startingRoomName));
    }

    public int getAllowedMoves() {
        return ALLOWED_MOVES;
    }

    public HashMap<String, Room> getMansion() {
        return mansion;
    }

    public String getAdjacentRoomsPath() {
        return adjacentRoomsPath;
    }

    public void setAdjacentRoomsPath(String adjacentRoomsPath) {
        this.adjacentRoomsPath = adjacentRoomsPath;
    }

    public String getItemsPath() {
        return itemsPath;
    }

    public void setItemsPath(String itemsPath) {
        this.itemsPath = itemsPath;
    }

    public String getMonstersPath() {
        return monstersPath;
    }

    public void setMonstersPath(String monstersPath) {
        this.monstersPath = monstersPath;
    }

    public String getRoomsPath() {
        return roomsPath;
    }

    public void setRoomsPath(String roomsPath) {
        this.roomsPath = roomsPath;
    }
}
