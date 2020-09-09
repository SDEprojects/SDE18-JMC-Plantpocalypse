/**
 * This class contains the GUI elements for the Game.
 *
 * Only one GUI or CLI is meant to run at any given time.
 *
 *  * @author Jeffrey Haywood
 *  * @date September 8th, 2020
 *  * @version 0.1
 */
package com.plantpocalypse.view;

import com.plantpocalypse.model.Game;
import com.plantpocalypse.controller.GameDirector;
import com.plantpocalypse.util.Dialogue;
import com.plantpocalypse.util.TextParser;

import javax.swing.*;
import java.awt.*;

public class GameGUI {
    private final Game game = Game.GAME_INSTANCE;

    private final JLabel currentRoomLabel, currentHealthLabel, movesMadeLabel;
    private final JTextArea dialogueText;

    /**
     * CTOR for the GUI.
     * Renders all of the Components needed, sets up
     * ActionListener for when Player presses enter in InputField.
     */
    public GameGUI() {
        // LOAD GAME ASSETS
        game.loadAssets();

        /* Instantiate Window and Containers */
        JFrame applicationWindow = new JFrame();

        /* Create JPanel placeholder so component can be put in specific Grid cell */
        int rows = 2;
        int cols = 3;
        JPanel userInputPanel = new JPanel(new GridLayout(rows, cols));
        JPanel[][] panelHolderInput = new JPanel[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                panelHolderInput[i][j] = new JPanel();
                userInputPanel.add(panelHolderInput[i][j]);
            }
        }

        /* Set attributes for Window */
        applicationWindow.setLayout(new BorderLayout());
        applicationWindow.setTitle("Plantpocalypse");
        applicationWindow.setSize(700,600);
        applicationWindow.add(userInputPanel, BorderLayout.SOUTH);

        /* Instantiate components for User Input section */
        JLabel inputFieldLabel = new JLabel("Enter command: ");
        JTextField inputField = new JTextField(16);
        currentRoomLabel = new JLabel();
        currentHealthLabel = new JLabel();
        movesMadeLabel = new JLabel();

        /* Instantiate TextArea for dialogue and set attributes */
        dialogueText = new JTextArea();
        dialogueText.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(dialogueText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        applicationWindow.add(scrollPane);

        /* Event listener for when Player press enter in the input field */
        inputField.addActionListener(e -> {
            if (!Game.GAME_INSTANCE.checkGameOver()) {
                String inputString = inputField.getText();
                inputField.setText("");

                displayDialogue(GameDirector.interact(TextParser.getInputFromGUI(inputString)));

                displayCurrentRoom(game.getPlayer().getCurrentRoom().getName());
                displayPlayerHealth(game.getPlayer().getCurrentHealth(), game.getPlayer().getMaxHealth());
                displayMovesMade(game.getPlayer().getMovesMade(), game.getAllowedMoves());

                if (game.checkGameOver()) {
                    if (game.checkLostGame()) lost(); else won();
                    gameOver();
                }
            }
        });

        /* Add related components to user input Grid */
        panelHolderInput[0][0].add(currentRoomLabel);
        panelHolderInput[0][1].add(currentHealthLabel);
        panelHolderInput[0][2].add(movesMadeLabel);
        panelHolderInput[1][0].add(inputFieldLabel);
        panelHolderInput[1][1].add(inputField);

        /* Attributes to set after all components added to Window */
        applicationWindow.setDefaultCloseOperation(applicationWindow.EXIT_ON_CLOSE);
        applicationWindow.setVisible(true);

        /* Initial loading of Gama data to UI elements */
        startGame();
    }

    /**
     * Changes the text on the currentRoomLabel to Player's current room.
     * @param currentRoom The current room the Player is in.
     */
    public void displayCurrentRoom(String currentRoom) {
        currentRoomLabel.setText("Current Room: " + currentRoom);
    }

    /**
     * Changes the text on the currentHealthLabel to Player's current health
     * out of their total health.
     * @param currentHealth The Player's current health.
     * @param totalHealth The total amount of health a Player can have.
     */
    public void displayPlayerHealth(int currentHealth, int totalHealth) {
        currentHealthLabel.setText("Health: " + currentHealth + "/" + totalHealth);
    }

    /**
     * Changes the text on the movesMadeLabel to show the amount of times the
     * Player has moved between rooms.
     * @param movesMade Number of moves between rooms player has made.
     */
    public void displayMovesMade(int movesMade, int totalMoves) {
        movesMadeLabel.setText("Moves Made: " + movesMade + "/" + totalMoves);
    }

    /**
     * Appends to dialogueText String from action or dialogue.
     * @param dialogue Story Dialogue or results from when command performed.
     */
    public void displayDialogue(String dialogue) {
        dialogueText.append(dialogue + "\n");
    }

    /**
     * Calls methods to display beginning of story and game data to
     * the GUI.
     */
    public void startGame() {
        title();
        intro();
        displayCurrentRoom(game.getPlayer().getCurrentRoom().getName());
        displayPlayerHealth(game.getPlayer().getCurrentHealth(), game.getPlayer().getMaxHealth());
        displayMovesMade(game.getPlayer().getMovesMade(), game.getAllowedMoves());
    }

    /**
     * Appends the title of the game to dialogueArea.
     */
    public void title() {
        displayDialogue(Dialogue.titleScreenDialogue());
    }

    /**
     * Appends the introduction to the game to dialogueArea.
     */
    public void intro() {
        displayDialogue(Dialogue.introDialogue());
    }

    /**
     * Appends losing dialogue to dialogueArea.
     */
    public void lost() {
        displayDialogue(Dialogue.losingDialogue());
    }

    /**
     * Appends winning dialogue to dialogueArea.
     */
    public void won() {
        displayDialogue(Dialogue.winningDialogue());
    }

    /**
     * Appends ending dialogue to dialogueArea.
     */
    public void gameOver() {
        displayDialogue(Dialogue.endingDialogue());
    }

}
