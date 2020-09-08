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

    public GameGUI() {
        /* Instantiate Window and Containers */
        JFrame applicationWindow = new JFrame();
        int rows = 2;
        int cols = 3;
        JPanel userInputPanel = new JPanel(new GridLayout(rows, cols));
        JPanel dialoguePanel = new JPanel();

        JPanel[][] panelHolderInput = new JPanel[rows][cols];

        /* Set attributes for Window */
        applicationWindow.setLayout(new BorderLayout());
        applicationWindow.setTitle("Plantpocalypse");
        applicationWindow.setSize(600,600);
        applicationWindow.add(userInputPanel, BorderLayout.SOUTH);
        applicationWindow.add(dialoguePanel, BorderLayout.NORTH);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                panelHolderInput[i][j] = new JPanel();
                userInputPanel.add(panelHolderInput[i][j]);
            }
        }

        /* Instantiate and set attributes for User Input section */
        JLabel inputFieldLabel = new JLabel("Enter command: ");
        JTextField inputField = new JTextField(16);

        /* Instantiate Label for current room, health, moves */
        currentRoomLabel = new JLabel();
        currentHealthLabel = new JLabel();
        movesMadeLabel = new JLabel();

        /* Instantiate TextArea for dialogue and set attributes */
        dialogueText = new JTextArea();
        dialogueText.setEditable(false);

        inputField.addActionListener(e -> {
            if (!Game.GAME_INSTANCE.checkGameOver()) {
                String inputString = inputField.getText();
                inputField.setText("");

                displayDialogue(GameDirector.interact(TextParser.getInputFromGUI(inputString)));

                displayCurrentRoom(game.getPlayer().getCurrentRoom().getName());
                displayPlayerHealth(game.getPlayer().getCurrentHealth(), game.getPlayer().getMaxHealth());
                displayMovesMade(game.getPlayer().getMovesMade());

                if (game.checkGameOver()) {
                    if (game.checkLostGame()) lost(); else won();
                    gameOver();
                }
            }
        });

        /* Add related components to dialogue container */
        dialoguePanel.add(dialogueText);

        /* Add related components to userInput container */
        panelHolderInput[0][0].add(currentRoomLabel);
        panelHolderInput[0][1].add(currentHealthLabel);
        panelHolderInput[0][2].add(movesMadeLabel);
        panelHolderInput[1][0].add(inputFieldLabel);
        panelHolderInput[1][1].add(inputField);

        /* Attributes to set after all components added to Window */
        applicationWindow.setDefaultCloseOperation(applicationWindow.EXIT_ON_CLOSE);
        applicationWindow.setVisible(true);

        startGame();
    }

    public void displayCurrentRoom(String currentRoom) {
        currentRoomLabel.setText("Current Room: " + currentRoom);
    }

    public void displayPlayerHealth(int currentHealth, int totalHealth) {
        currentHealthLabel.setText("Health: " + currentHealth + "/" + totalHealth);
    }

    public void displayMovesMade(int movesMade) {
        movesMadeLabel.setText("Moves Made: " + movesMade);
    }

    public void displayDialogue(String dialogue) {
        dialogueText.append(dialogue + "\n");
    }

    public void startGame() {
        title();
        intro();
        displayCurrentRoom(game.getPlayer().getCurrentRoom().getName());
        displayPlayerHealth(game.getPlayer().getCurrentHealth(), game.getPlayer().getMaxHealth());
        displayMovesMade(game.getPlayer().getMovesMade());
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

}
