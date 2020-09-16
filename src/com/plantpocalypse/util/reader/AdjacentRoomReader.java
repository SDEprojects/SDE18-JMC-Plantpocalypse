package com.plantpocalypse.util.reader;

import com.plantpocalypse.model.Room;

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

public class AdjacentRoomReader {
    static final String ROOM = "room";
    static final String NAME = "name";
    static final String ADJACENTROOM = "adjacentRoom";
    static final String DIRECTION = "direction";

    @SuppressWarnings( {"null"})
    public void readAdjacentRoomsXML(String roomsFile, HashMap<String, Room> rooms) {
        //HashMap<String, Room> adjacentRooms = new HashMap<String, Room>();
        try {
            // Create a new XMLInputFactory
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
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                String roomName = attribute.getValue();
                                if (attribute.getName().toString().equals(NAME) && rooms.containsKey(roomName)) {
                                    room = rooms.get(roomName); //  setName(attribute.getValue());
                                } else {
                                    System.out.println("Check adjacentRooms.xml for misspelled <room name='"+roomName+"'");
                                    System.exit(-1);
                                }
                            }
                        }
                        case ADJACENTROOM -> {
                            event = eventReader.nextEvent();
                            Iterator<Attribute> attributesDirection = startElement.getAttributes();
                            while (attributesDirection.hasNext()) {
                                Attribute attribute = attributesDirection.next();
                                if (attribute.getName().toString().equals(DIRECTION)) {
                                    // the direction attribute is used as a key, and the event is used to get the room
                                    String roomToAdd = event.asCharacters().getData();
                                    if (rooms.containsKey(roomToAdd)) {
                                        room.addNeighboringRoom(attribute.getValue(), rooms.get(roomToAdd));
                                    } else {
                                        System.out.println("Check adjacentRooms.xml for misspelled <adjacentRoomName>" + roomToAdd);
                                        System.exit(-1);
                                    }
                                }
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