package com.plantpocalypse.view;

import com.plantpocalypse.model.Room;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComponentMap {
    HashMap<String, JPanel> componentMap;

    public ComponentMap() {
        this.componentMap = new LinkedHashMap<String, JPanel>();
    }

    // Initialize component
    public JPanel createComponent(BufferedImage mapImage) {
        JPanel component = new JPanel();
        component.setMaximumSize(new Dimension(200, 100));
        component.setOpaque(false);
        // Scale image to fit container
        Image map = mapImage.getScaledInstance(component.getMaximumSize().width, component.getMaximumSize().height, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(map));
        component.add(imageLabel);

        return component;

    }

    // Add components to map
    public void addComponent(String componentName, JPanel component) {
        componentMap.put(componentName, component);
    }

    // Retrieve component for modifying value
    public JPanel getComponent(String roomName) {
        return componentMap.get(roomName);
    }

    public HashMap<String, JPanel> getComponentMap() {
        return componentMap;
    }
}
