/**
 * This class is the entry point for Plantpocalypse.
 *
 * @author Hunter Clark, Jeffrey Haywood, Maya Marks
 * @date August 31, 2020
 * @version 0.1
 */
package com.plantpocalypse;

import com.plantpocalypse.view.GameGUI;

public class GameClient {
    /**
     * This is the entry point for the game.
     * Will run either the CLI or GUI version of the game.
     * @param args This is arguments from the command line
     */
    public static void main(String[] args) {
        try {
            new GameGUI();
//            new GameCLI();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
