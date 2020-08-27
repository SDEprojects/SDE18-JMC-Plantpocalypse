package com.plantpocalypse.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextParser {
    public List<String> getInput() {
        Scanner readin = new Scanner(System.in);
        String input = readin.nextLine().toLowerCase();
        List<String> cmd = Arrays.asList(input.split("\\s+"));
        return cmd;
    }

    public void doCommand(List<String> command) {
        List<String> commands = Arrays.asList("go");
        if (command.size() < 2 || !commands.contains(command.get(0))) {
            System.out.println("Please enter command with correct format: command [option]");
        }
        if (command.get(0).equals("go")) {
            go(command.get(1));
        }
    }

    public void displayCommands() {
        System.out.println("Commands: ");
        System.out.println("go [north/east/south/west]");
    }

    private void go(String direction) {
        List<String> directions = Arrays.asList("north","south","west","east");
        if (directions.contains(direction)) {
            System.out.println("Going "+direction);
        } else {
            System.out.println("Please enter a valid direction.");
        }
    }

    public static void main(String[] args) {
        TextParser uip = new TextParser();
        while(true) {
            uip.displayCommands();
            List<String> cmd = uip.getInput();
            uip.doCommand(cmd);
        }
    }
}
