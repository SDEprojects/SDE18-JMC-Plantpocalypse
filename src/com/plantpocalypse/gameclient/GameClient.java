/**
 * This class is the entry point for Plantpocalypse.
 *
 * @author Hunter Clark, Jeffrey Haywood, Maya Marks
 * @date August 31, 2020
 * @version 0.1
 */
package com.plantpocalypse.gameclient;

public class GameClient {
    /**
     * This is the entry point for the game.
     * @param args This is arguments from the command line
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
