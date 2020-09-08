/**
 * This class is the entry point for Plantpocalypse.
 *
 * @author Hunter Clark, Jeffrey Haywood, Maya Marks
 * @date August 31, 2020
 * @version 0.1
 */
package com.plantpocalypse;

import com.plantpocalypse.model.Game;
import com.plantpocalypse.view.GameCLI;
import com.plantpocalypse.view.GameGUI;

public class GameClient {
    /**
     * This is the entry point for the game.
     * @param args This is arguments from the command line
     */
    public static void main(String[] args) {
        try {
            Game.GAME_INSTANCE.loadAssets();
            new GameGUI();
//            new GameCLI();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
