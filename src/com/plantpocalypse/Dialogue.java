package com.plantpocalypse;

public class Dialogue {
    public static void helpDialogue() {
        System.out.println("Valid commands and directions: \n" +
                "\t MOVE - \n" +
                "\t GO - \n" +
                "\t INSPECT - \n" +
                "\t GRAB - \n"+
                "\t TAKE - \n" +
                "\t NORTH - \n" +
                "\t SOUTH - \n " +
                "\t EAST - \n" +
                "\t WEST - \n" +
                "\t UP - \n" +
                "\t DOWN - \n");
    }

    public static void titleScreenDialogue() {
        System.out.println("\uD83C\uDF31 PLANTOCALYPSE \uD83C\uDF31");
    }

    public static void introDialogue() {
        System.out.println(" Recently, a team well heeled lawyers arrived at you house and informed you\n" +
                "that your little known, eccentric uncle had disappeared without a trace.  His\n" +
                "last known sighting was near his estate in the countryside.\n" +
                " As the sole heir in your uncle's will, the task is up to you to investigate\n" +
                "his disappearance. After a difficult trip, you reach the small town\n" +
                "nearest your uncle's estate. The townspeople whisper strange and fantastical\n" +
                "rumors of large plant-like forms lurking about your uncle's property. Eager to find some\n" +
                "answers, you ignore these superstitious ramblings and head toward the estate.\n" +
                " Upon your arrival, you see that his secluded mansion has been overun by\n" +
                "all sorts of strange plants. Undeterred, you proceed onto the property, hoping you\n" +
                "will find the answers you are looking for...\n");
    }

    public static void endingDialogue() {
        System.out.println("WOW! You did great!" +
                "\nThank you for playing 'PLANTOCALYPSE' be the Rambutan Game Studios!\n" +
                "\uD83C\uDF31 Jeffrey Haywood, Hunter Clark, Maya Marks \uD83C\uDF31");
    }
}
