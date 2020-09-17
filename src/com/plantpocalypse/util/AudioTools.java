package com.plantpocalypse.util;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioTools {
    // Handles sound for JFrame
    public static void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    // Handles looped  for JFrame
    public static void play(String filename, boolean loop, int loopCount) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.loop(loopCount - 1);
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    // Static Nested Classes for more organized method calling
    public static class Music {
        private static final String THEME_MUSIC = "../Plantpocalypse/audio/1.wav";

        public static void playTheme() {
            play(THEME_MUSIC, true, 10);
        }
    }

    public static class SFX {
        private static final String DOOR_CREAK_SOUND = "../Plantpocalypse/audio/door-creak.wav";
        private static final String DOOR_HANDLE_SOUND = "../Plantpocalypse/audio/door-handle-jiggle.wav";
        private static final String DOOR_UNLOCKING_SOUND = "../Plantpocalypse/audio/door-locking.wav";
        private static final String LEAVES_SOUND = "../Plantpocalypse/audio/leaves.wav";

        public static void playDoorOpen() {
            play(DOOR_CREAK_SOUND);
        }
        public static void playDoorHandleJiggle() {
            play(DOOR_HANDLE_SOUND);
        }
        public static void playDoorUnlocking() {
            play(DOOR_UNLOCKING_SOUND);
        }
        public static void playLeaves() {
            play(LEAVES_SOUND);
        }
    }






}
