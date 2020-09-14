package com.plantpocalypse.util;

public class Dialogue {

    public static String helpDialogue() {
        return "Valid commands and directions: \n" +
                "\n" +
                "\t=== AVAILABLE VERBS ===        \t=== AVAILABLE DIRECTIONS ===            \n" +
                "\tGO [DIRECTION]                           \tNORTH                                   \n" +
                "\tGET [ITEM NAME]                          \tNORTHEAST                               \n" +
                "\tUSE [ITEM NAME]                          \tEAST                                    \n" +
                "\tEAT [ITEM NAME]                          \tSOUTHEAST                               \n" +
                "\tEXAMINE [ITEM NAME]                  \tSOUTH                                   \n" +
                "\tOPEN [ITEM NAME]                       \tSOUTHWEST                               \n" +
                "\tINVENTORY                                  \tWEST                                    \n" +
                "\tLOOK                                           \tNORTHWEST                             \n" +
                "\t                                                    \tUP                                    \n" +
                "\t                                                    \tDOWN                                  \n" +
                "Objective: Traverse throughout the mansion finding keys and journal entries to help you find what happened to your uncle!";
    }

    public static String helpDialogueGUI() {
        return "Valid commands and directions: \n" +
                "\n" +
                "=== AVAILABLE VERBS ===\n" +
                "GO [DIRECTION]\n" +
                "GET [ITEM NAME]\n" +
                "USE [ITEM NAME]\n" +
                "EAT [ITEM NAME]\n" +
                "EXAMINE [ITEM NAME]\n" +
                "OPEN [ITEM NAME]\n" +
                "INVENTORY\n" +
                "LOOK\n\n" +
                "=== AVAILABLE DIRECTIONS ===\n" +
                "NORTH\n" +
                "NORTHEAST\n" +
                "EAST\n" +
                "SOUTHEAST\n" +
                "SOUTH\n" +
                "SOUTHWEST\n" +
                "WEST\n" +
                "NORTHWEST\n" +
                "UP\n" +
                "DOWN\n" +
                "\n" +
                "Objective: Traverse throughout the mansion finding keys and journal entries to help you find what happened to your uncle!\n";

    }

    public static String titleScreenDialogue() {
//        return "\uD83C\uDF31 PLANTPOCALYPSE \uD83C\uDF31";
        return "PLANTPOCALYPSE\n";
    }

    public static String introDialogue() {
        return "Recently, a team well heeled lawyers arrived at you house and informed you\n" +
                "that your little known, eccentric uncle had disappeared without a trace.  His\n" +
                "last known sighting was near his estate in the countryside.\n\n" +
                "As the sole heir in your uncle's will, the task is up to you to investigate\n" +
                "his disappearance. After a difficult trip, you reach the small town\n" +
                "nearest your uncle's estate. The townspeople whisper strange and fantastical\n" +
                "rumors of large plant-like forms lurking about your uncle's property. Eager to find some\n" +
                "answers, you ignore these superstitious ramblings and head toward the estate.\n\n" +
                "Upon your arrival, you see that his secluded mansion has been overrun by\n" +
                "all sorts of strange plants. Undeterred, you proceed onto the property, hoping you\n" +
                "will find the answers you are looking for...\n";
    }



    public static String endingDialogue() {
        return "\nThank you for playing \uD83C\uDF31 PLANTPOCALYPSE \uD83C\uDF31 by the Rambutan Game Studios!\n" +
                "\t Jeffrey Haywood, Hunter Clark, Maya Marks";
    }

    public static String losingDialogue() {
        return "\nThat's it. Game over. \n You might want to consider leaving your uncle's search to the professionals.";
    }

    public static String winningDialogue() {
        return "\nYES!!! YOU DID IT! You uncovered the mystery and rescued your beloved uncle.";
    }

//    public static void printMap() {
//        System.out.println("\nfloor plan goes here");
//    }
}
