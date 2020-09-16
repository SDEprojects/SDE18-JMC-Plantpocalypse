package com.plantpocalypse.view;

import com.plantpocalypse.model.Room;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComponentMap implements Serializable {
    HashMap<String, JPanel> componentMap;

    public ComponentMap() {
        this.componentMap = new LinkedHashMap<String, JPanel>();
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
