package com.plantpocalypse.util;

import java.util.*;

public class TextParser {

    // good enough for govt work
    public static List<String> getInput() {
        Scanner readin = new Scanner(System.in);
        String input = readin.nextLine().toLowerCase().strip();
        List<String> cmd = new LinkedList<String>(Arrays.asList(input.split("\\s+")));

        if (cmd.size() == 3) {
            String word1 = cmd.get(1);
            String word2 = cmd.get(2);

            String item = String.join(" ",word1,word2);
            cmd.remove(2);
            cmd.remove(1);
            cmd.add(1,item);
        }
        if (checkValidInput(cmd)) {
            return cmd;
        } else {
            return null;
        }
    }

    private static boolean checkValidInput(List<String> input) {
        boolean isValid = false;
        List<String> oneWordCommands = Arrays.asList("help","quit");
        List<String> twoWordCommands = Arrays.asList("go","eat","use","examine", "get");
        if (input.size() == 0) {
            System.out.println("Please enter command with correct format: command [arg]");
        } else if (input.size() == 1) {
            if(oneWordCommands.contains(input.get(0))) {
                isValid = true;
            } else {
                System.out.println("Not a valid command");
            }
        } else if (input.size() == 2) {
            if(twoWordCommands.contains(input.get(0))) {
                isValid = true;
            } else {
                System.out.println("Not a valid command");
            }
        } else {
            System.out.println("You entered too many arguments...");
        }
        return isValid;
    }

//    public static void main(String[] args) {
//         while (true) {
//             System.out.println("Enter a Command: ");
//             List<String> input = getInput();
//             System.out.println(input);
//         }
//    }
}
