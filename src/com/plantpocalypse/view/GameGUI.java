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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class GameGUI implements ActionListener {
    private final Game game = Game.GAME_INSTANCE;

    // TODO:
//        private final JPanel top, mid, bottom;
    JPanel floor1Panel, floor2Panel;
    //TODO:
    private final JFrame gameFrame;
    private final JPanel userInputPanel;
    private final JPanel HUD_CONTAINER;
    private final JPanel HUD;
//    private final JPanel[][] testPanelHolderInput;
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
        gameFrame.setSize(1600,1000);

        // Add a component container and heads up display component to the main frame
        HUD_CONTAINER = new JPanel(new BorderLayout());
        HUD = new JPanel(new BorderLayout());
        HUD_CONTAINER.add(HUD, BorderLayout.NORTH);
        gameFrame.add(HUD_CONTAINER, BorderLayout.WEST);
        gameFrame.add(userInputPanel, BorderLayout.SOUTH);

        /* Instantiate components for User Input section */
        inputFieldLabel = new JLabel("Enter command: ");
        inputField = new JTextField(16);
        inputField.setForeground(Color.white);
        inputField.setBackground(Color.black);
        currentRoomLabel = new JLabel();
        currentHealthLabel = new JLabel();
        movesMadeLabel = new JLabel();

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

        panelHolderInput[0][0].add(currentRoomLabel);
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

        }
        //TODO: remove between lines
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
        HUD_CONTAINER.add(floor1Panel, BorderLayout.SOUTH);


        //TODO: remove between lines

        /* Attributes to set after all components added to Window */
        gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);

        dialogueText.setText("\t\tSelect Menu > New Game to start a new game.\n\t\tSelect Menu > Load Game to load a save game.");
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
            if(result.contains("Moved to")) {
                dialogueText.setText("");
                dialogueText.setForeground(Color.getHSBColor(new Random().nextInt(256),new Random().nextInt(256),new Random().nextInt(256)));
            }
//            if(result.contains("You opened the")) {
//                // Gets current room name and returns it in snake case
//                String parsedRoom = TextParser.parseRoomName(game.getPlayer().getCurrentRoom().getName());
//                String pathName = "./resources/map_" + parsedRoom + ".png";
//                try {
//                    result = "You opened the map.";
//                    BufferedImage mapImage = ImageIO.read(new File(pathName));
//                    JLabel imageLabel = new JLabel(new ImageIcon(mapImage));
//                    JPanel imageHolder = new JPanel();
//                    imageHolder.add(imageLabel);
//                    JOptionPane.showMessageDialog(gameFrame, imageHolder);
//                }
//                catch (MapFileNotFoundException exc) {
//                    System.err.println("Map file not found in " + pathName);
//                }
//                catch(Exception exc) {
//                    System.out.println("no");
//                }
//            }

            if(result == null || result == "")
                result = "Not a valid command. Type help if you need a list of possible commands";
            displayDialogue(result);
            displayStatus();
//            refreshMapPanel(game.floor1);

            if (game.checkGameOver()) {
                if (game.checkLostGame()) lost(); else won();
                gameOver();
            }
        }
    }

//    public void displayCurrentRoomMap(String roomName){
//        String parsedRoom = TextParser.parseRoomName(game.getPlayer().getCurrentRoom().getName());
//        String pathName = "./resources/map_" + parsedRoom + ".png";
//
//        if (game.getPlayer().retrieveItemFromInventory("floor plan") != null) {
//            try {
//                BufferedImage mapImage = ImageIO.read(new File(pathName));
//                Image map = mapImage.getScaledInstance(HUD.getPreferredSize().width, HUD.getPreferredSize().height, Image.SCALE_SMOOTH);
//                JLabel imageLabel = new JLabel(new ImageIcon(map));
//                // After establishing file exists, remove previous component and replace with the new image
//                HUD.remove(HUD.getComponent(0));
//                HUD.add(imageLabel);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public static class ImageRender {
//        public static BufferedImage changeAlpha(BufferedImage mapImage, double amount) {
//
//            for (int x = 0; x < mapImage.getWidth(); x++) {
//                for (int y = 0; y < mapImage.getHeight(); y++) {
//                    //
//                    int argb = mapImage.getRGB(x, y); //always returns TYPE_INT_ARGB
//                    int alpha = (argb >> 24) & 0xff;  //isolate alpha
//
//                    alpha *= amount; //similar distortion to tape saturation (has scrunching effect, eliminates clipping)
//                    alpha &= 0xff;      //keeps alpha in 0-255 range
//
//                    argb &= 0x00ffffff; //remove old alpha info
//                    argb |= (alpha << 24);  //add new alpha info
//                    mapImage.setRGB(x, y, argb);
//                }
//            }
//        }
//
//    }


    public void displayCurrentRoomMap(BufferedImage map) {
//        BufferedImage temp = ImageRender.changeAlpha(map, .1);
//        ImageRender.changeAlpha(map, 1);
        HUD.remove(HUD.getComponent(0));
        JLabel imageLabel = new JLabel(new ImageIcon(map));
        HUD.add(imageLabel);
    }

    /**
     * Changes the text on the currentRoomLabel to Player's current room.
     * @param currentRoom The current room the Player is in.
     */
    public void displayCurrentRoom(String currentRoom) {
        currentRoomLabel.setText("<html>"+"Current Room: " + "<font color = red>"+ currentRoom + "</html>");
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
//        displayCurrentRoomMap(game.getPlayer().getCurrentRoom().getPath());
        displayCurrentRoomMap(game.getPlayer().getCurrentRoom().getMapImage());
    }

    /**
     * Calls methods to display beginning of story and game data to
     * the GUI.
     */
    public void refreshMapPanel(ComponentMap componentMap) {
//        componentMap.removeComponent();
        //TODO update this to handle floor 2
        componentMap.getComponentMap().forEach((entry, component) -> {
            floor1Panel.add(component);
        });
    }
    public void startGame() {
        dialogueText.setText("\t\t");
        game.loadAssets();
        //TODO
        refreshMapPanel(game.floor1);
//        refreshMapPanel(game.floor2);

        //TODO
        title();
        intro();
        displayStatus();
        scrollPane.setVisible(true);
        userInputPanel.setVisible(true);
        HUD_CONTAINER.setVisible(true);
    }

    public void loadSavedGame() {
        dialogueText.setText("");
        game.loadGame();
        displayStatus();
        scrollPane.setVisible(true);
        userInputPanel.setVisible(true);
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

}
