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
import com.plantpocalypse.util.ConsoleDisplay;
import com.plantpocalypse.util.Dialogue;
import com.plantpocalypse.util.TextParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameGUI implements ActionListener {
    private final Game game = Game.GAME_INSTANCE;

    private final JFrame gameFrame;
    private final JPanel userInputPanel;
    private final JPanel[][] panelHolderInput;
    private final JScrollPane scrollPane;

    private final JButton newGameButton, loadGameButton;
    private final JLabel inputFieldLabel, currentRoomLabel, currentHealthLabel, movesMadeLabel;
    private final JTextArea dialogueText;
    private final JTextField inputField;

    private final JMenu menu;
    private final JMenuItem newGame, save, load, help, about, quit;
    private final JMenuBar menuBar;

    /**
     * CTOR for the GUI.
     * Renders all of the Components needed, sets up
     * ActionListener for when Player presses enter in InputField.
     */
    public GameGUI() {
        /* Instantiate Window and Containers */
        gameFrame = new JFrame();

        /* Instantiate Menu Components */
        menu = new JMenu("Menu");
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(this);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        load = new JMenuItem("Load");
        load.addActionListener(this);
        help = new JMenuItem("Help");
        help.addActionListener(this);
        about = new JMenuItem("About");
        about.addActionListener(this);
        quit = new JMenuItem("Quit");
        quit.addActionListener(this);
        menuBar = new JMenuBar();
        menu.add(newGame);
        menu.add(save);
        menu.add(load);
        menu.add(help);
        menu.add(about);
        menu.add(quit);
        menuBar.add(menu);
        gameFrame.setJMenuBar(menuBar);

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(this);

        /* Create JPanel placeholder so component can be put in specific Grid cell */
        int rows = 2;
        int cols = 3;
        userInputPanel = new JPanel(new GridLayout(rows, cols));
        panelHolderInput = new JPanel[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                panelHolderInput[i][j] = new JPanel();
                userInputPanel.add(panelHolderInput[i][j]);
            }
        }

        /* Set attributes for Window */
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setTitle("Plantpocalypse");
        gameFrame.setSize(800,600);
        gameFrame.add(userInputPanel, BorderLayout.SOUTH);

        /* Instantiate components for User Input section */
        inputFieldLabel = new JLabel("Enter command: ");
        inputField = new JTextField(16);
        currentRoomLabel = new JLabel();
        currentHealthLabel = new JLabel();
        movesMadeLabel = new JLabel();

        /* Instantiate TextArea for dialogue and set attributes */
        dialogueText = new JTextArea();
        dialogueText.setEditable(false);
        scrollPane = new JScrollPane(dialogueText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setAutoscrolls(true);
        gameFrame.add(scrollPane);

        /* Event listener for when Player press enter in the input field */
        inputField.addActionListener(this);

        /* Add related components to user input Grid */
        panelHolderInput[0][0].add(currentRoomLabel);
        panelHolderInput[0][1].add(currentHealthLabel);
        panelHolderInput[0][2].add(movesMadeLabel);
        panelHolderInput[1][0].add(inputFieldLabel);
        panelHolderInput[1][1].add(inputField);

        /* Attributes to set after all components added to Window */
        gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);

        dialogueText.setText("Select Menu > New Game to start a new game.\nSelect Menu > Load Game to load a save game.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame || e.getSource() == newGameButton) {
            startGame();
        }
        else if (e.getSource() == save) {
            game.saveGame();
        }
        else if (e.getSource() == load || e.getSource() == loadGameButton) {
            loadSavedGame();
        }
        else if (e.getSource() == help) {
            help();
        }
        else if (e.getSource() == about) {
            about();
        }
        else if (e.getSource() == quit) {
            System.exit(0);
        }
        else if (e.getSource() == inputField) {
            String inputString = inputField.getText();
            inputField.setText("");
            // 1) Formats user input into one or two string commands with TextParser
            // 2) Validates user input command with TextParser, returning command as List<Strings>
            // 3) Uses GameDirector to enact command, returning result string to show user
            String result = GameDirector.interact(TextParser.getInputFromGUI(inputString));

            if(result.contains("You opened the")) {
                try {
                    result = "You opened the map.";
                    String parsedRoom = TextParser.parseRoomName(game.getPlayer().getCurrentRoom().getName());
                    BufferedImage mapImage = ImageIO.read(new File("./resources/map_" + parsedRoom + ".png"));
                    JLabel imageLabel = new JLabel(new ImageIcon(mapImage));
                    JPanel imageHolder = new JPanel();
                    imageHolder.add(imageLabel);
                    JOptionPane.showMessageDialog(gameFrame, imageHolder);
                }
                catch(Exception exc) {
                    System.out.println("no");
                }
                try {
                    result = "You opened the map.";
                    BufferedImage mapImageF2 = ImageIO.read(new File("./resources/mapf2.png"));
                    JLabel imageLabelf2 = new JLabel(new ImageIcon(mapImageF2));
                    JPanel imageHolder = new JPanel();
                    imageHolder.add(imageLabelf2);
                    JOptionPane.showMessageDialog(gameFrame, imageHolder);
                }
                catch(Exception exc) {
                    System.out.println("no");
                }
            }

            displayDialogue(result);
            displayStatus();

            if (game.checkGameOver()) {
                if (game.checkLostGame()) lost(); else won();
                gameOver();
            }
        }
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

    public void displayStatus() {
        displayCurrentRoom(game.getPlayer().getCurrentRoom().getName());
        displayPlayerHealth(game.getPlayer().getCurrentHealth(), game.getPlayer().getMaxHealth());
        displayMovesMade(game.getPlayer().getMovesMade(), game.getAllowedMoves());
    }

    /**
     * Calls methods to display beginning of story and game data to
     * the GUI.
     */
    public void startGame() {
        dialogueText.setText("");
        game.loadAssets();
        title();
        intro();
        displayStatus();
        scrollPane.setVisible(true);
        userInputPanel.setVisible(true);
    }

    public void loadSavedGame() {
        dialogueText.setText("");
        game.loadGame();
        displayStatus();
        scrollPane.setVisible(true);
        userInputPanel.setVisible(true);
    }

    public void about() {
        JOptionPane.showMessageDialog(gameFrame, "Plantpocalypse\nMade by Hunter Clark | Jeffrey Haywood | Maya Marks");
    }

    public void help() {
        JOptionPane.showMessageDialog(gameFrame, Dialogue.helpDialogueGUI());
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
        userInputPanel.setVisible(false);
    }

}
