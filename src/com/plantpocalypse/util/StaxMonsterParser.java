package com.plantpocalypse.util;

import com.plantpocalypse.model.PlantMonster;
import com.plantpocalypse.model.Room;
import com.plantpocalypse.model.items.FloorPlan;
import com.plantpocalypse.model.items.Food;
import com.plantpocalypse.model.items.Item;
import com.plantpocalypse.model.items.Key;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxMonsterParser {
    static final String MONSTER = "monster";
    static final String NAME = "name";
    static final String BASEATTACK = "baseAttack";
    static final String LOCATION = "location";

    @SuppressWarnings( { "unchecked", "null"})
    public void readMonstersXML(String roomsFile, HashMap<String, Room> rooms) {
        try {
            // Create aa new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(roomsFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            PlantMonster plantMonster = null;

            while  (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // if we have a room element, we create a new room
                    String elementName = startElement.getName().getLocalPart();


                    switch (elementName) {
                        case MONSTER:
                            plantMonster = new PlantMonster();
                            break;

                        case NAME:
                            event = eventReader.nextEvent();
                            plantMonster.setMonsterName(event.asCharacters().getData());
                            plantMonster.setMonsterDescription("This is a "+plantMonster.getMonsterName());
                            break;

                        case BASEATTACK:
                            event = eventReader.nextEvent();
                            plantMonster.setBaseAttack(Integer.parseInt(event.asCharacters().getData()));
                            break;

                        case LOCATION:
                            event = eventReader.nextEvent();
                            rooms.get(event.asCharacters().getData()).setMonster(plantMonster);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
