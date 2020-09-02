package com.plantpocalypse.gameclient;

import com.plantpocalypse.Item;
import com.plantpocalypse.Player;
import com.plantpocalypse.Room;
import com.plantpocalypse.util.TextParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GameDirector {

    public static void interact(Player player) {
        List<String> input = TextParser.getInput();

        if (input != null) {
            if (input.get(0).equals("go")) {
                go(input.get(1), player);
            } else if (input.get(0).equals("eat")) {
                eat(input.get(1), player);
            } else if(input.get(0).equals("use")) {
                use(input.get(1), player);
            } else if (input.get(0).equals("examine")) {
                examine(input.get(1), player);
            } else if (input.get(0).equals("get")) {
                pickup(input.get(1), player);
            } else if (input.get(0).equals("quit")) {
                quit();
            }
        }
    }

    private static void go(String direction, Player player) {
        //List<String> directions = Arrays.asList("north","northwest","up","west","east");
        HashMap<String, Room> adjacentRooms = player.getCurrentRoom().getNeighboringRooms();
        if (adjacentRooms.containsKey(direction)) {
            player.move(adjacentRooms.get(direction));
        } else {
            System.out.println("Please enter a valid direction.");
        }
    }

    private static void eat(String itemName, Player player) {
        if (itemName != null) {
            player.use(itemName);
        } else {
            System.out.println("You do not have that item!");
        }
    }

    private static void use(String itemName, Player player) {
        if (itemName != null) {
            player.use(itemName);
        } else {
            System.out.println("You do not have that item!");
        }
    }

    private static void examine(String itemName, Player player) {
        if (itemName != null) {
            player.examine(itemName);
        } else {
            System.out.println("You do not have that item!");
        }
    }

    private static void pickup(String itemName, Player player) {
        if (itemName != null) {
            player.pickUpItem(itemName);
        } else {
            System.out.println("That item is not in this room.");
        }
    }


    private static void quit() {
        System.exit(0);
    }


}
