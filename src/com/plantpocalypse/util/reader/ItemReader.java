package com.plantpocalypse.util.reader;

import com.plantpocalypse.model.Room;
import com.plantpocalypse.model.items.*;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

public class ItemReader {
    static final String ITEM = "item";
    static final String TYPE = "type";
    static final String NAME = "name";
    public final String DESCRIPTION = "description";
    public final String BACK = "back";
    static final String LOCATION = "location";
    static final String HEALTHRESTORED = "healthRestored";
    static final String ROOMUNLOCK = "roomUnlock";

    @SuppressWarnings( {"null"})
    public void readItemsXML(String roomsFile, HashMap<String, Room> rooms) {
        try {
            // Create aa new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(roomsFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Item item = null;

            while  (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // if we have a room element, we create a new room
                    String elementName = startElement.getName().getLocalPart();


                    // we read attributes from this tag and toggle
                    // lock if isLocked is true
                    // downcast item and assign room to the keys roomKeyUnlocks variable
                    switch (elementName) {
                        case ITEM -> {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(TYPE)) {
                                    //System.out.println(attribute.getValue());
                                    //System.out.println(attribute.getValue());
                                    switch (attribute.getValue()) {
                                        case "food" -> item = new Food();
                                        case "key" -> item = new Key();
                                        case "floorPlan" -> item = new FloorPlan();
                                        case "weedKiller" -> item = new WeedKiller();
                                        case "elixir" -> item = new Elixir();
                                        case "journal" -> item = new Journal();
//                                        case "groundsKeeper" -> item = new GroundsKeeper();

                                    }
                                }
                            }
                        }
                        case NAME -> {
                            event = eventReader.nextEvent();
                            if (item != null) {
                                item.setName(event.asCharacters().getData());
                            } else {
                                System.out.println("Item value was null, check that types are correct in items.xml");
                            }
                        }
                        case DESCRIPTION -> {
                            event = eventReader.nextEvent();
                            if (item != null) {
                                item.setDescription(event.asCharacters().getData());
                            } else {
                                System.out.println("Item value was null, check that types are correct in items.xml");
                            }
                        }
                        case BACK -> {
                            event = eventReader.nextEvent();
                            if (item != null) {
                                item.setBack(event.asCharacters().getData());
                            } else {
                                System.out.println("Item value was null, check that types are correct in items.xml");
                            }
                        }
                        case HEALTHRESTORED -> {
                            event = eventReader.nextEvent();
                            if (item != null ) {
                                try {
                                    assert item instanceof Food;
                                    ((Food) item).setHealthRestored(Integer.parseInt(event.asCharacters().getData()));
                                } catch (ClassCastException e) {
                                    System.out.println("Cannot downcast to Food item, make sure items.xml is correct");
                                    e.printStackTrace();
                                }
                            }
                        }
                        case ROOMUNLOCK -> {
                            event = eventReader.nextEvent();
                            Room tempRoom = rooms.get(event.asCharacters().getData());
                            if (item != null ) {
                                try {
                                    assert item instanceof Key;
                                    ((Key) item).setRoomKeyUnlocks(tempRoom);
                                } catch (ClassCastException e) {
                                    System.out.println("Cannot downcast to Food item, make sure items.xml is correct");
                                    e.printStackTrace();
                                }
                            }
                        }
                        case LOCATION -> {
                            event = eventReader.nextEvent();
                            String roomName = event.asCharacters().getData();
                            if (item != null && rooms.containsKey(roomName)) {
                                rooms.get(event.asCharacters().getData()).addItem(item.getName(), item);
                            } else {
                                System.out.println("Check items.xml for error.");
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
