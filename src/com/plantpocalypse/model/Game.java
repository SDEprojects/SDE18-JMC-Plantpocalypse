package com.plantpocalypse.model;

import com.plantpocalypse.util.reader.AdjacentRoomReader;
import com.plantpocalypse.util.reader.ItemReader;
import com.plantpocalypse.util.reader.MonsterReader;
import com.plantpocalypse.util.reader.RoomReader;
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
        connectRooms();
        player = new Player(mansion.get("Outside"));

    }

    /**
     * Instantiates all of the rooms in the mansion.
     */
    private void loadRooms() {
        RoomReader readRooms = new RoomReader();
        mansion = readRooms.readRoomsXML("./resources/newGame/rooms.xml");
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
        /* Change to real lose condition when possible */
        return player.getMovesMade() >= ALLOWED_MOVES
                || !player.isAlive();
    }

    public boolean checkGameOver() {
        /* Change to real game over condition when possible */

//        return player.getMovesMade() >= ALLOWED_MOVES
//                || !player.isAlive()
//                || !player.playerWon();
        return player.getCurrentRoom().getName().equals("Hidden Office")
                || player.getMovesMade() >= ALLOWED_MOVES
                || !player.isAlive()
                || player.playerWon();
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
