package com.plantpocalypse.model;

import com.plantpocalypse.util.TransparencyTool;
import com.plantpocalypse.util.reader.AdjacentRoomReader;
import com.plantpocalypse.util.reader.ItemReader;
import com.plantpocalypse.util.reader.MonsterReader;
import com.plantpocalypse.util.reader.RoomReader;
import com.plantpocalypse.view.ComponentMap;
import com.plantpocalypse.util.reader.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        player = new Player(mansion.get("Outside"));
//        loadNPC();

    }

    public void loadAssetsTutorial() {
        loadRooms();
        loadItems();
        loadMonsters();
        connectRooms();
        player = new Player(mansion.get("Estate Gates"));
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
        mansion = readRooms.readRoomsXML("./resources/newGame/rooms.xml");
        loadComponentMaps();
    }

    // Instantiates HashMaps of JPanel components for easy lookup of each room/floor's mini map overlays
    private void loadComponentMaps() {
        floor1 = new ComponentMap();
        floor2 = new ComponentMap();
        floor0 = new ComponentMap();
        // Load outline overlay into each floor's map

        JPanel tempComponent = TransparencyTool.createJPanelFromPath("./resources/map_labels_floor_1.png");
        floor1.addComponent("labels", tempComponent);
        tempComponent = TransparencyTool.createJPanelFromPath("./resources/map_labels_floor_2.png");
        floor2.addComponent("labels", tempComponent);

        // Load rooms overlays into map
        mansion.forEach((roomName, room) -> {
            JPanel component = TransparencyTool.createJPanelFromPath(room.getPath());
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

        tempComponent = TransparencyTool.createJPanelFromPath("./resources/map_background_floor_1.png");
        floor1.addComponent("background", tempComponent);

        tempComponent = TransparencyTool.createJPanelFromPath("./resources/map_background_floor_2.png");
        floor2.addComponent("background", tempComponent);

    }


    private void loadItems() {
        ItemReader readItems = new ItemReader();
        readItems.readItemsXML("./resources/newGame/items.xml",mansion);
    }

    private void loadMonsters() {
        MonsterReader readMonsters = new MonsterReader();
        readMonsters.readMonstersXML("./resources/newGame/monsters.xml",mansion);
    }

    private void connectRooms() {
        AdjacentRoomReader readAdjacentRooms = new AdjacentRoomReader();
        readAdjacentRooms.readAdjacentRoomsXML("./resources/newGame/adjacentRooms.xml",mansion);
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

    public int getAllowedMoves() {
        return ALLOWED_MOVES;
    }

    public HashMap<String, Room> getMansion() {
        return mansion;
    }
}
