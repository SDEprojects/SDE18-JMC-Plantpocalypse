package com.plantpocalypse.util;

import com.plantpocalypse.model.Room;

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

public class StaxAdjacentRoomParser {
    static final String ROOM = "room";
    static final String NAME = "name";
    static final String ADJACENTROOM = "adjacentRoom";
    static final String DIRECTION = "direction";

    @SuppressWarnings( { "unchecked", "null"})
    public void readAdjacentRoomsXML(String roomsFile, HashMap<String, Room> rooms) {
        //HashMap<String, Room> adjacentRooms = new HashMap<String, Room>();
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
                    switch (elementName) {
                        case ROOM:
                            // we read attributes from this tag and toggle
                            // lock if isLocked is true
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(NAME)) {
                                    room = rooms.get(attribute.getValue()); //  setName(attribute.getValue());
                                }
                            }
                            break;
                        case ADJACENTROOM:
                            event = eventReader.nextEvent();
                            Iterator<Attribute> attributesDirection = startElement.getAttributes();
                            while (attributesDirection.hasNext()) {
                                Attribute attribute = attributesDirection.next();
                                if (attribute.getName().toString().equals(DIRECTION)) {
                                    //System.out.println(event.asCharacters().getData());
                                    //adjacentRooms.put(attribute.getValue(),rooms.get(event.asCharacters().getData()));
                                    room.addNeighboringRoom(attribute.getValue(), rooms.get(event.asCharacters().getData()));
                                }
                            }
                            break;
                    }
                }
//                if (event.isEndElement()) {
//                    EndElement endElement = event.asEndElement();
//                    if (endElement.getName().getLocalPart().equals(ROOM)) {
//                        room.setNeighboringRooms(adjacentRooms);
//                    }
//                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}