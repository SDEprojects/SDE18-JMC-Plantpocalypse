package com.plantpocalypse.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextParser {

    public static List<String> getInputFromCLI() {
        Scanner readIn = new Scanner(System.in);

        return parseInput(readIn.nextLine());
    }

    public static List<String> getInputFromGUI(String input) {
        return parseInput(input);
    }

    private static List<String> parseInput(String input) {
        input = input.toLowerCase().strip();
        List<String> cmd = new ArrayList<String>(Arrays.asList(input.split("\\s+")));

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

        List<String> oneWordCommands = Arrays.asList("inventory","help","look","quit","save","load");
        List<String> twoWordCommands = Arrays.asList("go","eat","use","examine", "get", "open");

        if (input.size() == 0) {
            System.out.println("Please enter command with correct format: command [arg]");
        } else {
            input.set(0,checkAndReplaceSynonyms(input.get(0)));
            if (input.size() == 1) {
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
        }
        return isValid;
    }

    private static String checkAndReplaceSynonyms(String word) {
        String result = word;
        List<String> goWords = Arrays.asList("walk","run","jump","hop","skip");
        List<String> quitWords = Arrays.asList("exit","leave");
        List<String> getWords = Arrays.asList("grab","take");
        if (goWords.contains(word)) {
            result = "go";
        } else if (quitWords.contains(word)) {
            result = "quit";
        } else if (getWords.contains(word)) {
            result = "get";
        }
        return result;
    }

}
