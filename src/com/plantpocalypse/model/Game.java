package com.plantpocalypse.model;

import com.plantpocalypse.util.TransparencyTool;
import com.plantpocalypse.util.reader.AdjacentRoomReader;
import com.plantpocalypse.util.reader.ItemReader;
import com.plantpocalypse.util.reader.MonsterReader;
import com.plantpocalypse.util.reader.RoomReader;
import com.plantpocalypse.view.ComponentMap;

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
        loadComponentMaps();
    }

    // Instantiates HashMaps of JPanel components for easy lookup of each room/floor's mini map overlays
    private void loadComponentMaps() {
        floor1 = new ComponentMap();
        floor2 = new ComponentMap();
        // Load outline overlay into each floor's map

        JPanel tempComponent = TransparencyTool.createJPanelFromPath("./resources/map_labels_floor_1.png");
        floor1.addComponent("labels", tempComponent);
        tempComponent = TransparencyTool.createJPanelFromPath("./resources/map_labels_floor_2.png");
        floor2.addComponent("labels", tempComponent);

        // Load rooms overlays into map
        mansion.forEach((roomName, room) -> {
            JPanel component = TransparencyTool.createJPanelFromPath(room.getPath());
            if (room.getFloorNumber() == 1) {
                floor1.addComponent(room.getName(), component);
            }
            else if (room.getFloorNumber() == 2 ) {
                floor2.addComponent(room.getName(), component);
            }
        });

        // Load background images into maps

        tempComponent = TransparencyTool.createJPanelFromPath("./resources/map_background_floor_1.png");
        floor1.addComponent("background", tempComponent);

        tempComponent = TransparencyTool.createJPanelFromPath("./resources/map_background_floor_2.png");
        floor2.addComponent("background", tempComponent);

    }

    public JPanel createRoomOverlayComponent(BufferedImage mapImage, boolean isOpaque) {
        JPanel component = new JPanel();
        component.setMaximumSize(new Dimension(600, 375));
        component.setOpaque(isOpaque);
        // Scale image to fit container
        Image map = mapImage.getScaledInstance(component.getMaximumSize().width, component.getMaximumSize().height, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(map));
        component.add(imageLabel);
        return component;
    }

    // Scales image for updating a previously created room overlay component
    public Image scaleImage(BufferedImage img) {
        return img.getScaledInstance(600,375, Image.SCALE_SMOOTH);
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

    public boolean checkGameOver() {
        return player.getMovesMade() >= ALLOWED_MOVES
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
