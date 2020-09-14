package com.plantpocalypse.util.reader;


import org.xml.sax.SAXException;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class NpcReader {

    File file = new File("./resources/newGame/NPC.xml");
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document document = (Document) db.parse(file);

    public NpcReader() throws ParserConfigurationException, IOException, SAXException {
    }
}
