package com.plantpocalypse.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleDisplay {

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
            {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }
        catch (final Exception e)
        {
            System.out.println("didn't work");
        }
    }


    private static String getAsciiArt(String filename) {
        try {
            return Files.lines(Path.of("./resources", filename))
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            return "getAsciiArt() failed to retrieve String";
        }
    }

    public static void welcomeScreen() {
        //clearConsole();
        String output = getAsciiArt("banner.txt");
        slowPrint(output,output.length());
        System.out.println("      Press Enter to Continue...");
        String enter = getUserString();
        //clearConsole();
    }

    public static void printFloorPlan() {
        try {
            File bluePrint = new File("resources/floorPlan.txt");
            Scanner reader = new Scanner(bluePrint);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void slowPrint(String output, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(output.charAt(i));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Failed to start new thread");
            }
        }
    }

    public static String getUserString() {
        Scanner readin = new Scanner(System.in);
        String input = readin.nextLine();
        return input;
    }
}

