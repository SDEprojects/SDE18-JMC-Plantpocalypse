package com.plantpocalypse.util;

import com.plantpocalypse.model.Room;
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

public class StaxItemParser {
    static final String ITEM = "item";
    static final String TYPE = "type";
    static final String NAME = "name";
    static final String LOCATION = "location";
    static final String HEALTHRESTORED = "healthRestored";
    static final String ROOMUNLOCK = "roomUnlock";

    @SuppressWarnings( { "unchecked", "null"})
    public void readItemsXML(String roomsFile, HashMap<String, Room> rooms) {
        try {
            // Create aa new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(roomsFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Item item = null;
            boolean itemInitialized = false;

            while  (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // if we have a room element, we create a new room
                    String elementName = startElement.getName().getLocalPart();


                    switch (elementName) {
                        case ITEM:
                            // we read attributes from this tag and toggle
                            // lock if isLocked is true
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(TYPE)) {
                                    if (attribute.getValue().equals("food")) {
                                        itemInitialized = true;
                                        item = new Food();
                                    } else if (attribute.getValue().equals("key")) {
                                        //System.out.println(attribute.getValue());
                                        itemInitialized = true;
                                        item = new Key();
                                    }
                                }
                            }
                            break;
                        case NAME:
                            event = eventReader.nextEvent();
                            item.setName(event.asCharacters().getData());
                            item.setDescription("This is a "+item.getName());
                            break;

                        case HEALTHRESTORED:
                            event = eventReader.nextEvent();
                            ((Food) item).setHealthRestored(Integer.parseInt(event.asCharacters().getData()));
                            break;
                        case ROOMUNLOCK:
                            event = eventReader.nextEvent();
                            Room tempRoom = rooms.get(event.asCharacters().getData());
                            // downcast item and assign room to the keys roomKeyUnlocks variable
                            ((Key) item).setRoomKeyUnlocks(tempRoom);
                            break;

                        case LOCATION:
                            event = eventReader.nextEvent();
                            rooms.get(event.asCharacters().getData()).addItem(item.getName(), item);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
