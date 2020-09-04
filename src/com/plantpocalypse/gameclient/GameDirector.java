package com.plantpocalypse.gameclient;

import com.plantpocalypse.util.Dialogue;
import com.plantpocalypse.Player;
import com.plantpocalypse.Room;
import com.plantpocalypse.util.TextParser;

import java.util.HashMap;
import java.util.List;

public class GameDirector {

    public static void interact(Player player) {
        List<String> input = TextParser.getInput();

        if (input != null) {
            String command = input.get(0);
            String argument = input.size() == 2 ? input.get(1) : null;

            switch (command) {
                case "go" -> go(argument, player);
                case "eat" -> eat(argument, player);
                case "use" -> use(argument, player);
                case "examine" -> examine(argument, player);
                case "get" -> pickup(argument, player);
                case "inventory" -> inventory(player);
                case "help" -> help();
                case "quit" -> quit();
            }
        }
    }

    private static void go(String direction, Player player) {
        HashMap<String, Room> adjacentRooms = player.getCurrentRoom().getNeighboringRooms();

        if (adjacentRooms.containsKey(direction)) {
            if (player.move(adjacentRooms.get(direction))) {
                System.out.println("Moved to " + player.getCurrentRoom().getName());

                if (player.getCurrentRoom().getMonster() != null) {
                    player.getCurrentRoom().getMonster().attackPlayer(player);
                }

            } else {
                System.out.println("The door is locked.");
            }
        } else {
            System.out.println("Please enter a valid direction.");
        }
    }

    private static void eat(String itemName, Player player) {
        if (itemName != null && player.eat(itemName)) {
            System.out.println("You ate the " + itemName);
        } else {
            System.out.println("You do not have that item!");
        }
    }

    private static void use(String itemName, Player player) {
        if (itemName != null && player.use(itemName)) {
            System.out.println("You used " + itemName);
        } else {
            System.out.println("You do not have that item!");
        }
    }

    private static void examine(String itemName, Player player) {
        if (itemName != null && player.examine(itemName)) {
            System.out.println("Examined: " + itemName);
        } else {
            System.out.println("You do not have that item!");
        }
    }

    private static void pickup(String itemName, Player player) {
        if (itemName != null && player.pickUpItem(itemName)) {
            System.out.println("Picked up " + itemName);
        } else {
            System.out.println("That item is not in this room.");
        }
    }

    private static void inventory(Player player) {
        player.displayInventory();
    }

    private static void quit() {
        System.exit(0);
    }

    private static void help() {
        Dialogue.helpDialogue();
    }
}
