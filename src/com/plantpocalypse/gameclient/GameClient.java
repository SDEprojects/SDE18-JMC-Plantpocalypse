package com.plantpocalypse.gameclient;

public class GameClient {
    /**
     * This is the entry point for the game.
     * @param args
     */
    public static void main(String[] args) {
        try {
            Game.GAME_INSTANCE.loadAssets();
            Game.GAME_INSTANCE.startGame();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
