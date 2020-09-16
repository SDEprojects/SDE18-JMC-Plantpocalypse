package com.plantpocalypse.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TransparencyTool {

    // Read in a buffered image
    public BufferedImage readBuff() {
        return new BufferedImage();
    }



    // Create an image icon
    BufferedImage mapImage = ImageIO.read(new File(pathName));
    JLabel imageLabel = new JLabel(new ImageIcon(mapImage));
    JPanel imageHolder = new JPanel();

    public ImageIcon createImageIcon(BufferedImage image) {
        return new ImageIcon(image);
    }



    // StackOverflow foo to change all of the pixels of an image to a given transparency (alpha) value
    // https://stackoverflow.com/questions/16054596/change-color-of-non-transparent-parts-of-png-in-java
    public static BufferedImage changeAlpha(BufferedImage mapImage, double amount) {

        for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {
                //
                int argb = mapImage.getRGB(x, y); //always returns TYPE_INT_ARGB
                int alpha = (argb >> 24) & 0xff;  //isolate alpha

                alpha *= amount; //similar distortion to tape saturation (has scrunching effect, eliminates clipping)
                alpha &= 0xff;      //keeps alpha in 0-255 range

                argb &= 0x00ffffff; //remove old alpha info
                argb |= (alpha << 24);  //add new alpha info
                mapImage.setRGB(x, y, argb);
            }
        }
        return mapImage;
    }
}
