package com.plantpocalypse.gameclient;

import com.plantpocalypse.util.Dialogue;

import javax.swing.*;
import java.awt.*;

public class GameGUI {
    JFrame applicationWindow;
    JPanel userInputPanel, oldCommandPanel;
    JLabel currentRoomLabel;

    GameGUI() {
        applicationWindow = new JFrame();
        userInputPanel = new JPanel();
        oldCommandPanel = new JPanel();

        applicationWindow.setLayout(new BorderLayout());
        userInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        oldCommandPanel.setLayout((new FlowLayout(FlowLayout.LEFT)));


        applicationWindow.setTitle("Plantpocalypse");
        applicationWindow.setSize(600,600);
        applicationWindow.add(userInputPanel, BorderLayout.SOUTH);
        applicationWindow.add(oldCommandPanel, BorderLayout.NORTH);

        JLabel inputFieldLabel = new JLabel("Enter command: ");
        JTextField inputField = new JTextField(16);

        JLabel oldCommandLabel = new JLabel();

        JTextArea gameInfoTextArea = new JTextArea();

        currentRoomLabel = new JLabel();

        inputField.addActionListener(e -> {
            if (!Game.GAME_INSTANCE.gameOver()) {
                String inputString = inputField.getText();
                inputField.setText("");
                oldCommandLabel.setText("Previously entered command: " + inputString);

//            gameInfoTextArea.append(inputString);
                System.out.println(inputString);
                GameDirector.interactGUI(Game.GAME_INSTANCE.getPlayer(), inputString);
                printToTextArea();
            }
        });

        userInputPanel.add(inputFieldLabel);
        userInputPanel.add(inputField);

        userInputPanel.add(currentRoomLabel);

        userInputPanel.add(oldCommandLabel, BorderLayout.PAGE_END);
//        userInputPanel.add(gameInfoTextArea);
//        userInputPanel.add(oldCommandLabel);

        applicationWindow.setDefaultCloseOperation(applicationWindow.EXIT_ON_CLOSE);
        applicationWindow.setVisible(true);
    }

    public void printToTextArea() {
        currentRoomLabel.setText(Dialogue.enterCommandDialogue());
    }

}
