package com.plantpocalypse.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TransparencyTool {

    // Read in a buffered image
    public static BufferedImage readBuff(String filePath) {
        BufferedImage result = null;
        try {
             result = ImageIO.read(new File(filePath));
        } catch (Exception e) {
            System.err.println(e);
        }

        return result;
    }



    // Create an image icon
//    BufferedImage mapImage = ImageIO.read(new File(pathName));
//    JLabel imageLabel = new JLabel(new ImageIcon(mapImage));
//    JPanel imageHolder = new JPanel();
    public static JPanel createJPanelFromPath(String filePath) {
        ImageIcon imageIcon = createImageIcon(readBuff(filePath));
        return createJPanelFromImageIcon(imageIcon);
    }

    public static JPanel createJPanelFromPath(String filePath, int width, int height) {
        ImageIcon imageIcon = createImageIcon(readBuff(filePath), width, height);
        return createJPanelFromImageIcon(imageIcon);
    }
    public static ImageIcon createImageIcon(BufferedImage image) {
        Image scaledImage = image.getScaledInstance(600,375, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    public static ImageIcon createImageIcon(BufferedImage image, int width, int height) {
        Image scaledImage = image.getScaledInstance(width,height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public static JPanel createJPanelFromImageIcon(ImageIcon imageIcon) {
        JLabel imageLabel = new JLabel(imageIcon);
        JPanel imagePanel = new JPanel();
        imagePanel.setMaximumSize(new Dimension(600, 375));
        imagePanel.setOpaque(false);
        imagePanel.add(imageLabel);
        return imagePanel;
    }

    public static JLabel createJLabelFromImageIcon(ImageIcon imageIcon){
        JLabel imageLabel = new JLabel(imageIcon);
        return imageLabel;
    }

    public static JPanel createRoomOverlayComponent(BufferedImage mapImage, boolean isOpaque) {
        JPanel component = new JPanel();
        component.setMaximumSize(new Dimension(600, 375));
        component.setOpaque(isOpaque);
        // Scale image to fit container
        Image map = mapImage.getScaledInstance(component.getMaximumSize().width, component.getMaximumSize().height, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(map));
        component.add(imageLabel);
        return component;
    }

    // Scales image for updating a previously created room overlay component
    public static Image scaleImage(BufferedImage img) {
        return img.getScaledInstance(600,375, Image.SCALE_SMOOTH);
    }





    // StackOverflow foo to change all of the pixels of an image to a given transparency (alpha) value
    // https://stackoverflow.com/questions/16054596/change-color-of-non-transparent-parts-of-png-in-java
    public static BufferedImage changeAlpha(BufferedImage mapImage) {

        for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {
                //
                int argb = mapImage.getRGB(x, y); //always returns TYPE_INT_ARGB
                int alpha = (argb >> 24) & 0xff;  //isolate alpha

                alpha *= .6; //similar distortion to tape saturation (has scrunching effect, eliminates clipping)
                alpha &= 0xff;      //keeps alpha in 0-255 range

                argb &= 0x00ffffff; //remove old alpha info
                argb |= (alpha << 24);  //add new alpha info
                mapImage.setRGB(x, y, argb);
            }
        }
        return mapImage;
    }
}
