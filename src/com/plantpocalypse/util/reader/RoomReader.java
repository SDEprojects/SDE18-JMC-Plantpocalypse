package com.plantpocalypse.util.reader;

import com.plantpocalypse.model.Room;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

public class RoomReader {
    static final String ROOM = "room";
    static final String NAME = "name";
    static final String ISLOCKED = "isLocked";
    static final String DESCRIPTION = "description";
    static final String NPC = "NPC";
    static final String NPCdialogue = "NPCdialogue";

    @SuppressWarnings( {"null"})
    public HashMap<String, Room> readRoomsXML(String roomsFile) {
        HashMap<String, Room> rooms = new HashMap<>();
        try {
            // Create aa new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(roomsFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Room room = null;

            while  (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // if we have a room element, we create a new room
                    String elementName = startElement.getName().getLocalPart();
                    // we read attributes from this tag and toggle
                    // lock if isLocked is true
                    switch (elementName) {
                        case ROOM -> {
                            room = new Room();
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(NAME)) {
                                    room.setName(attribute.getValue());
                                }
                            }
                        }
                        case ISLOCKED -> {
                            event = eventReader.nextEvent();
                            if (event.asCharacters().getData().equals("true")) {
                                if (room != null) {
                                    room.toggleLock();
                                } else {
                                    System.out.println("Room not initialized, check rooms.xml for error");
                                    System.exit(-1);
                                }
                            }
                        }
                        case DESCRIPTION -> {
                            event = eventReader.nextEvent();
                            if (room != null) {
                                room.setDescription(event.asCharacters().getData());
                            } else {
                                System.out.println("Room not initialized, check rooms.xml for error");
                                System.exit(-1);
                            }
                        }
                        case NPC -> {
                            event = eventReader.nextEvent();
                            if (room != null) {
                                room.setCharacter(event.asCharacters().getData());
                            } else {
                                System.out.println("Room not initialized, check rooms.xml for error");
                                System.exit(-1);
                            }
                        }

                        case NPCdialogue -> {
                            event = eventReader.nextEvent();
//                            System.out.println(event);
                            if (room != null) {
                                room.setNPCdialogue(event.asCharacters().getData());
                            } else {
                                System.out.println("Room not initialized, check rooms.xml for error");
                                System.exit(-1);
                            }
                        }
                    }
                }

                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(ROOM)) {
                        if (room != null) {
                            rooms.put(room.getName(),room);
                        } else {
                            System.out.println("Room not initialized, check rooms.xml for error");
                            System.exit(-1);
                        }
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
