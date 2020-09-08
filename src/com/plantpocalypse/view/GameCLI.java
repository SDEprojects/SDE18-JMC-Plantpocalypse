/**
 * This class contains the CLI elements for the game.
 *
 * Only one GUI or CLI is meant to run at any given time.
 *
 * @author Hunter Clark, Jeffrey Haywood, Maya Marks
 * @date August 31, 2020
 * @version 0.1
 */
package com.plantpocalypse.view;

import com.plantpocalypse.controller.GameDirector;
import com.plantpocalypse.model.Game;
import com.plantpocalypse.util.ConsoleDisplay;
import com.plantpocalypse.util.Dialogue;
import com.plantpocalypse.util.TextParser;

public class GameCLI {
    private final Game game = Game.GAME_INSTANCE;

    /**
     * CTOR for Game CLI.
     * Starting sequence for the game.
     */
    public GameCLI() {
        startGame();
    }

    /**
     * Prints out the Player's current room.
     * @param currentRoom The current room the Player is in.
     */
    public void displayCurrentRoom(String currentRoom) {
        System.out.println("Current Room: " + currentRoom);
    }

    /**
     * Prints out the Player's health status.
     * @param currentHealth The current amount of health the Player has.
     * @param totalHealth The total amount of health the Player can have.
     */
    public void displayPlayerHealth(int currentHealth, int totalHealth) {
        System.out.println("Health: " + currentHealth + "/" + totalHealth);
    }

    /**
     * Prints out the amount of times the Player has moved between rooms.
     * @param movesMade The amount of times the Player has moved between rooms.
     */
    public void displayMovesMade(int movesMade) {
        System.out.println("Moves Made: " + movesMade);
    }

    /**
     * Prints out story dialogue or results from commands.
     * @param dialogue Dialogue to be printed.
     */
    public void displayDialogue(String dialogue) {
        System.out.println(dialogue + "\n");
    }

    /**
     * Presents the Player with title screen and introduction then
     * loops until the game is over.
     * Every iteration through loop Player is asked to enter input.
     */
    public void startGame() {
        ConsoleDisplay.welcomeScreen();
        intro();

        while(!game.checkGameOver()) {
            displayCurrentRoom(game.getPlayer().getCurrentRoom().getName());
            displayPlayerHealth(game.getPlayer().getCurrentHealth(), game.getPlayer().getMaxHealth());
            displayMovesMade(game.getPlayer().getMovesMade());

            System.out.println(nextCommand());
        }

        if (game.checkLostGame()) lost(); else won();
        gameOver();
    }

    /**
     * Gets input from the Player.
     * @return Result from the entered command.
     */
    private String nextCommand() {
        return GameDirector.interact(TextParser.getInputFromCLI());
    }

    /**
     * Prints small version of title.
     */
    public void title() {
        displayDialogue(Dialogue.titleScreenDialogue());
    }

    /**
     * Introduces the Player to the game's setting.
     */
    public void intro() {
        displayDialogue(Dialogue.introDialogue());
    }

    /**
     * Notifies the Player that they have lost the game.
     */
    public void lost() {
        displayDialogue(Dialogue.losingDialogue());
    }

    /**
     * Notifies the Player that they have won the game.
     */
    public void won() {
        displayDialogue(Dialogue.winningDialogue());
    }

    /**
     * Thanks the Player for playing the game.
     */
    public void gameOver() {
        displayDialogue(Dialogue.endingDialogue());
    }

    /* METHODS FOR TESTING AND OTHER TEMPORARY METHODS*/
    private void itemsInRoom() {
        System.out.println("\nItems in " + game.getPlayer().getCurrentRoom().getName() + ":");
        game.getPlayer().getCurrentRoom().displayItems();
    }

    private void neighboringRooms() {
        System.out.println("\nConnected Rooms: ");
        game.getPlayer().getCurrentRoom().getNeighboringRooms().forEach( (k,v) -> System.out.println(k + " => " + v.getName()));
    }

    private void showItemsAndRooms() {
        itemsInRoom();
        neighboringRooms();
    }
}
