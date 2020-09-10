package com.plantpocalypse.util.reader;

import com.plantpocalypse.model.PlantMonster;
import com.plantpocalypse.model.Room;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class MonsterReader {
    static final String MONSTER = "monster";
    static final String NAME = "name";
    static final String BASEATTACK = "baseAttack";
    static final String LOCATION = "location";

    @SuppressWarnings( {"null"})
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
                        case MONSTER -> plantMonster = new PlantMonster();
                        case NAME -> {
                            event = eventReader.nextEvent();
                            if (plantMonster != null) {
                                plantMonster.setMonsterName(event.asCharacters().getData());
                                plantMonster.setMonsterDescription("This is a " + plantMonster.getMonsterName());
                            } else {
                                System.out.println("PlantMonster was initialized, check for typo in monsters.xml at <monster>");
                                System.exit(-1);
                            }
                        }
                        case BASEATTACK -> {
                            event = eventReader.nextEvent();
                            if (plantMonster != null) {
                                plantMonster.setBaseAttack(Integer.parseInt(event.asCharacters().getData()));
                            } else {
                                System.out.println("Failed to initialize plant monster, check monsters.xml");
                                System.exit(-1);
                            }
                        }
                        case LOCATION -> {
                            event = eventReader.nextEvent();
                            String roomName = event.asCharacters().getData();
                            if (rooms.containsKey(roomName)) {
                                if (plantMonster != null) {
                                    rooms.get(roomName).setMonster(plantMonster);
                                } else {
                                    System.out.println("Failed to initialize plant monster, check monsters.xml");
                                    System.exit(-1);
                                }
                            } else {
                                System.out.println("Room name does not exist in currently loaded rooms, check monsters.xml for error");
                                System.exit(-1);
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
