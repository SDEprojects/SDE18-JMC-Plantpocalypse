package com.plantpocalypse.controller;

import com.plantpocalypse.model.Game;
import com.plantpocalypse.model.Player;
import com.plantpocalypse.model.Room;
import com.plantpocalypse.model.items.Item;
import com.plantpocalypse.model.items.Key;
import com.plantpocalypse.util.ConsoleDisplay;
import com.plantpocalypse.util.Dialogue;
import com.plantpocalypse.view.GameGUI;

import java.util.HashMap;
import java.util.List;

public class GameDirector {

    private static Object NpcReader;

    /* Return a string so GUI can print action to TextArea? */
    public static String interact(List<String> input) {
        String result = "";

        if (input != null) {
            String command = input.get(0);
            String argument = input.size() == 2 ? input.get(1) : null;
            Player player = Game.GAME_INSTANCE.getPlayer();

            switch (command) {
                case "talk" -> result = talk(argument, player);
                case "go" -> result = go(argument, player);
                case "eat" -> result = eat(argument, player);
                case "use" -> result = use(argument, player);
                case "examine" -> result = examine(argument, player);
                case "get" -> result = pickup(argument, player);
                case "look" -> result = look(player);
                case "inventory" -> result = inventory(player);
                case "help" -> result = help();
                case "quit" -> quit();
                case "open" -> result = open(argument, player);
                case "save" -> Game.GAME_INSTANCE.saveGame();
            }
        }

        return result;
    }
    private static String talk (String NPCname, Player player) {

        String result = "He is not with you";
        if (player.getCurrentRoom().getCharacter() != null){
            result = player.talk(NPCname);
        }
        return result;
    }



    private static String go(String direction, Player player) {
        String result = "Please enter a valid direction.";
        HashMap<String, Room> adjacentRooms = player.getCurrentRoom().getNeighboringRooms();

        if (adjacentRooms.containsKey(direction)) {
            if (player.move(adjacentRooms.get(direction))) {
                result = "Moved to " + player.getCurrentRoom().getName();
                try {
                    GameGUI.play("../Plantpocalypse/audio/door-creak.wav");
                } catch (Exception e){}
                if (player.getCurrentRoom().getMonster() != null) {
                    try {
                        GameGUI.play("../Plantpocalypse/audio/leaves.wav");
                    } catch (Exception e){}
                    result += "\nYou were attacked by a monstrous " + player.getCurrentRoom().getMonster().getMonsterName();
                    result += "\nYou lost " + player.getCurrentRoom().getMonster().getBaseAttack() + " health points.";
                    player.getCurrentRoom().getMonster().attackPlayer(player);
                }
            } else {
                try {
                    GameGUI.play("../Plantpocalypse/audio/door-handle-jiggle.wav");
                } catch (Exception e){}
                result = "The door is locked.";
            }
        }

        return result;
    }

    private static String eat(String itemName, Player player) {
        String result = "You do not have one of those!";
        Item item = player.retrieveItemFromInventory(itemName);

        if (itemName != null && player.eat(itemName)) {
            result = "Omnomnom! Must have been organic";
            result += "\nYou ate the " + item.getName();
        }

        return result;
    }

    private static String use(String itemName, Player player) {
        String result = "You do not have that item!";
        Item item = player.retrieveItemFromInventory(itemName);

        if (itemName != null && player.use(itemName)) {
            result = "You used the " + item.getName();

            if (itemName.contains("key")) {
                try {
                    GameGUI.play("../Plantpocalypse/audio/door-locking.wav");
                } catch (Exception e){}
                Key key = (Key) item;
                result += "\nYou unlocked the " + key.getRoomKeyUnlocks().getName();
            } else if (itemName.contains("killer")) {
                if (player.getCurrentRoom().getName().equals("Green House Floor 2")) {
                    result += "\nYou killed all plant monsters in the mansion";
                } else result = "\nWeed killer is more useful when work with plants from the right angle.";
            }
        }
            return result;
        }

    private static String open(String itemName, Player player) {
        String result = "You have nothing to open!";
        Item item = player.retrieveItemFromInventory(itemName);

        if (itemName != null && player.open(itemName)) {
            result = ("You opened the " + item.getName() + "\n");
            result += ConsoleDisplay.printFloorPlan();
        }

        return result;
    }

    private static String examine(String itemName, Player player) {
        String result = "You do not have that item!";
        Item item = player.retrieveItemFromInventory(itemName);

        if (itemName != null && player.examine(itemName)) {
            result = ("You examine the " + item.getName());
            result += ("\n" + item.getDescription());
        }

        return result;
    }

    private static String pickup(String itemName, Player player) {
        String result = "That item is not in this room.";

        if (itemName != null && player.pickUpItem(itemName)) {
            result = "Picked up a " + itemName;
        }

        return result;
    }

    private static String look(Player player) {
        StringBuilder result = new StringBuilder(player.getCurrentRoom().getDescription());
        Room currentRoom = player.getCurrentRoom();

        result.append("\nYou notice a door to the ");

        for (String key : currentRoom.getNeighboringRooms().keySet()) {
            result.append(key).append(", ");
        }

        result.setCharAt(result.length() - 2, '.');

        if (currentRoom.getMonster() != null) {
            result.append("\nThere is a scary ").append(player.getCurrentRoom().getMonster().getMonsterName()).append(" in here!");
            result.append("\nYou should run away!!!");
            return result.toString();
        }

        if (currentRoom.getItems().size() > 0) {
            result.append("\nYou notice a ");

            for (Item item : currentRoom.getItems().values()) {
                result.append(item.getName()).append(", ");
            }

            result.deleteCharAt(result.length() - 2);

            result.append("on the ground. Maybe they are useful?");
        }

        return result.toString();
    }

    private static String inventory(Player player) {
        StringBuilder result = new StringBuilder("There are no items in your inventory.");
        List<Item> inventory = player.getInventory();

        if (player.displayInventory()) {
            result = new StringBuilder("Player Inventory:\n");

            for (int i = 0; i < inventory.size(); i++) {
                result.append(i + 1).append(". ").append(inventory.get(i).getName()).append("\n");
            }
        }

        return result.toString();
    }



    private static void quit() {
        System.exit(0);
    }

    private static String help() {
        return Dialogue.helpDialogue();
    }

}
