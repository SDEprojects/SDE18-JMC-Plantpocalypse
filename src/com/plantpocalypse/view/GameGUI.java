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

import com.plantpocalypse.controller.GameDirector;
import com.plantpocalypse.model.Game;
import com.plantpocalypse.util.Dialogue;
import com.plantpocalypse.util.TextParser;
import com.plantpocalypse.util.TransparencyTool;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class GameGUI implements ActionListener {
    private final Game game = Game.GAME_INSTANCE;

    // Main window
    private final JFrame gameFrame;
    // Container for user input and other status displays
    private final JPanel userInputPanel;
    // Helper panel to organize status components
    private final JPanel[][] panelHolderInput;
    private final JScrollPane scrollPane;

    private final JButton newGameButton, loadGameButton, tutorialButton;
    private final JLabel inputFieldLabel, currentRoomLabel, currentHealthLabel, movesMadeLabel;
    private final JTextArea dialogueText;
    private final JTextField inputField;

    private final JMenu menu;
    private final JMenuItem newGame, save, load, help, about, quit, tutorial;
    private final JMenuBar menuBar;

    // Containers for mini map and title screen
    private final JPanel HUD_CONTAINER, HUD, floor1Panel, floor2Panel, floor0Panel;
    private final JPanel currentRoomIcon, roomStatusContainer;

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
        tutorial = new JMenuItem("Tutorial");
        tutorial.addActionListener(this);
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
        menu.add(tutorial);
        menu.add(load);
        menu.add(help);
        menu.add(about);
        menu.add(quit);
        menuBar.add(menu);
        gameFrame.setJMenuBar(menuBar);

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        tutorialButton = new JButton("Tutorial");
        tutorialButton.addActionListener(this);
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
        gameFrame.setSize(1600,1000);

        // Add a component container and heads up display component to the main frame
        HUD_CONTAINER = new JPanel(new BorderLayout());
        HUD = new JPanel(new BorderLayout());
        HUD_CONTAINER.add(HUD, BorderLayout.NORTH);
        gameFrame.add(HUD_CONTAINER, BorderLayout.WEST);
        gameFrame.add(userInputPanel, BorderLayout.SOUTH);
        roomStatusContainer = new JPanel(){
            @Override
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        roomStatusContainer.setLayout(new OverlayLayout(roomStatusContainer));


        /* Instantiate components for User Input section */
        inputFieldLabel = new JLabel("Enter command: ");
        inputField = new JTextField(16);
        inputField.setForeground(Color.white);
        inputField.setBackground(Color.black);
        currentRoomLabel = new JLabel();
        currentHealthLabel = new JLabel();
        movesMadeLabel = new JLabel();

        // Instantiate a background color panel for current room label
        currentRoomIcon  = new JPanel();
        currentRoomIcon.setPreferredSize(new Dimension(125,50));
        currentRoomIcon.setMaximumSize(currentRoomIcon.getPreferredSize());
        currentRoomIcon.setMinimumSize(currentRoomIcon.getPreferredSize());


        roomStatusContainer.add(currentRoomLabel);
        roomStatusContainer.add(currentRoomIcon);

        /* Instantiate TextArea for dialogue and set attributes */
        dialogueText = new JTextArea();
        dialogueText.setEditable(false);
        dialogueText.setBackground(Color.black);
        dialogueText.setForeground(Color.white);
        dialogueText.setLineWrap(true);
        scrollPane = new JScrollPane(dialogueText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setAutoscrolls(true);
        gameFrame.add(scrollPane);

        /* Event listener for when Player press enter in the input field */
        inputField.addActionListener(this);

        /* Add related components to user input Grid */
        panelHolderInput[0][0].add(roomStatusContainer);
        panelHolderInput[0][1].add(currentHealthLabel);
        panelHolderInput[0][2].add(movesMadeLabel);
        panelHolderInput[1][0].add(inputFieldLabel);
        panelHolderInput[1][1].add(inputField);

        // Initialize HUD with title screen image
        try {
            HUD.setPreferredSize(new Dimension(600,375));
            BufferedImage mapImage = ImageIO.read(new File("./resources/plantpocalypse_title.png"));
            Image map = mapImage.getScaledInstance(HUD.getPreferredSize().width, HUD.getPreferredSize().height, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(map));
            HUD.add(imageLabel);
        }
        catch (Exception e) {
            System.err.println("title screen image file does not exist or is improperly named");
        }

        // Set up floor1 and floor2 containers to allow overlays in mini map drawing
         floor1Panel = new JPanel() {
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        LayoutManager overlay = new OverlayLayout(floor1Panel);
        floor1Panel.setLayout(overlay);

        floor2Panel = new JPanel() {
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        overlay = new OverlayLayout(floor2Panel);
        floor2Panel.setLayout(overlay);

        floor0Panel = new JPanel() {
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        overlay = new OverlayLayout(floor0Panel);
        floor0Panel.setLayout(overlay);



        /* Attributes to set after all components added to Window */
        gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);

        dialogueText.setText("\tSelect Menu > New Game to start a new game.\n\tSelect Menu > Load Game to load a save game.\n\tSelect Menu > Tutorial to play the tutorial.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame || e.getSource() == newGameButton) {
            startGame();
        }
        else if (e.getSource() == save) {
            game.saveGame();
        }
        else if (e.getSource() == tutorial || e.getSource() == tutorialButton) {
            startTutorial();
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
            if(result.contains("Moved to")) {
//                play("../Plantpocalypse/audio/1.wav");
                dialogueText.setText("");
                dialogueText.setForeground(Color.getHSBColor(new Random().nextInt(256),new Random().nextInt(256),new Random().nextInt(256)));
            }
            if(result.contains("You opened the")) {
                int currentFloor = game.getPlayer().getCurrentRoom().getFloorNumber();
                result = "You opened the map.";
                // Point at the map file that corresponds to the current floor and display in a pop up
                String pathName = "./resources/map_background_floor_" + currentFloor + ".png";
                JPanel imageHolder = TransparencyTool.createJPanelFromPath(pathName, 800, 500);
                JOptionPane.showMessageDialog(gameFrame, imageHolder);

            }

            if(result == null || result == "")
                result = "Not a valid command. Type help if you need a list of possible commands";
            // If player moved to a new floor, toggle mini map visibility for both floors (one on, the other off)
            if(result.contains("Moved to Floor ")) {
                swapFloorPanels();
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
        currentRoomLabel.setText("<html>"+
//                "Current Room: " +
                "<font color = black>"+ currentRoom + "</html>");
        // Set background color here according to room
        int roomColor = Game.GAME_INSTANCE.getPlayer().getCurrentRoom().getColor();
        currentRoomIcon.setBackground(new Color(roomColor));
    }

    /**
     * Changes the text on the currentHealthLabel to Player's current health
     * out of their total health.
     * @param currentHealth The Player's current health.
     * @param totalHealth The total amount of health a Player can have.
     */
    public void displayPlayerHealth(int currentHealth, int totalHealth) {
        currentHealthLabel.setText("<html>"+ "Health: " + "<font color = red>" + currentHealth + "/" + totalHealth + "</html>");
    }

    /**
     * Changes the text on the movesMadeLabel to show the amount of times the
     * Player has moved between rooms.
     * @param movesMade Number of moves between rooms player has made.
     */
    public void displayMovesMade(int movesMade, int totalMoves) {
        movesMadeLabel.setText("<html>"+ "Moves Made: " + "<font color = red>" + movesMade + "/" + totalMoves + "</html>");
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
    public void initializeFloorPanels(ComponentMap componentMap, JPanel panel) {
        // Add each component in the ComponentMap to the proper floor's JPanel container
        componentMap.getComponentMap().forEach((entry, component) -> {
            panel.add(component);
        });
    }
    public void startGame() {
        dialogueText.setText("\t\t");
        game.loadAssets();
        initializeFloorPanels(game.floor1, floor1Panel);
        initializeFloorPanels(game.floor2, floor2Panel);
        title();
        intro();
        displayStatus();
        scrollPane.setVisible(true);
        userInputPanel.setVisible(true);
        HUD_CONTAINER.setVisible(true);
        HUD_CONTAINER.remove(0);
        HUD_CONTAINER.add(floor1Panel, BorderLayout.NORTH);
        HUD_CONTAINER.add(floor2Panel, BorderLayout.SOUTH);
        floor2Panel.setVisible(false);
        play("../Plantpocalypse/audio/1.wav");          //play's song

    }

    public void startTutorial() {
        dialogueText.setText("\t\t");
        game.loadAssetsTutorial();
        initializeFloorPanels(game.floor1, floor1Panel);
        initializeFloorPanels(game.floor2, floor2Panel);
        initializeFloorPanels(game.floor0, floor0Panel);

        title();
        intro();
        displayStatus();
        scrollPane.setVisible(true);
        userInputPanel.setVisible(true);
        HUD_CONTAINER.setVisible(true);
//        HUD_CONTAINER.remove(0);
//        HUD_CONTAINER.add(floor1Panel, BorderLayout.NORTH);
//        HUD_CONTAINER.add(floor2Panel, BorderLayout.SOUTH);
//        HUD_CONTAINER.add(floor0Panel, BorderLayout.SOUTH);

//        floor2Panel.setVisible(false);
        play("../Plantpocalypse/audio/1.wav");          //play's song

    }
    public void swapFloorPanels(){
        // Toggles visibility for each floor mini maps
        floor1Panel.setVisible(!floor1Panel.isVisible());
        floor2Panel.setVisible(!floor2Panel.isVisible());
    }

    public void loadSavedGame() {
        dialogueText.setText("");
        game.loadGame();
        initializeFloorPanels(game.floor1, floor1Panel);
        initializeFloorPanels(game.floor2, floor2Panel);
        displayStatus();
        scrollPane.setVisible(true);
        userInputPanel.setVisible(true);
        HUD_CONTAINER.setVisible(true);
        HUD_CONTAINER.remove(0);
        HUD_CONTAINER.add(floor1Panel, BorderLayout.NORTH);
        HUD_CONTAINER.add(floor2Panel, BorderLayout.SOUTH);
        floor2Panel.setVisible(false);
        if (game.getPlayer().getCurrentRoom().getFloorNumber() == 2) {
            swapFloorPanels();
        }
        play("../Plantpocalypse/audio/1.wav");          //play's song
    }
//    add more details in the about section
    public void about() {
        JOptionPane.showMessageDialog(gameFrame, "Plantpocalypse A maze mystery game set to test your detective skills. Solve the mystery behind what happen to your Uncle. \nMade by Hunter Clark | Jeffrey Haywood | Maya Marks");
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

    // Handles sound for jframe
    public static void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
}
