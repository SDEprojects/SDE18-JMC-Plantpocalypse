package com.plantpocalypse.view;

import com.plantpocalypse.controller.GameDirector;
import com.plantpocalypse.model.Game;
import com.plantpocalypse.util.ConsoleDisplay;
import com.plantpocalypse.util.Dialogue;
import com.plantpocalypse.util.TextParser;

public class GameCLI {
    private final Game game = Game.GAME_INSTANCE;

    public GameCLI() {
        startGame();
    }

    public void displayCurrentRoom(String currentRoom) {
        System.out.println("Current Room: " + currentRoom);
    }

    public void displayPlayerHealth(int currentHealth, int totalHealth) {
        System.out.println("Health: " + currentHealth + "/" + totalHealth);
    }

    public void displayMovesMade(int movesMade) {
        System.out.println("Moves Made: " + movesMade);
    }

    public void displayDialogue(String dialogue) {
        System.out.println(dialogue + "\n");
    }

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

    private String nextCommand() {
        return GameDirector.interact(TextParser.getInputFromCLI());
    }

    public void title() {
        displayDialogue(Dialogue.titleScreenDialogue());
    }

    public void intro() {
        displayDialogue(Dialogue.introDialogue());
    }

    public void lost() {
        displayDialogue(Dialogue.losingDialogue());
    }

    public void won() {
        displayDialogue(Dialogue.winningDialogue());
    }

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
