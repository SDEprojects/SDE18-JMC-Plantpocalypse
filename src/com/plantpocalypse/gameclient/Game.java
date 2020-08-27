package com.plantpocalypse.gameclient;

import com.plantpocalypse.Player;
import com.plantpocalypse.Room;

import java.util.HashMap;

public enum Game {
    GAME_INSTANCE;

    Player player;
    Room outside;
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
