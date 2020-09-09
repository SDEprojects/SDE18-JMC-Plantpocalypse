package com.plantpocalypse.util;


import com.plantpocalypse.model.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestParsing {
    public static void main(String[] args) {
        StaxRoomParser readRooms = new StaxRoomParser();
        StaxAdjacentRoomParser readAdjacentRooms = new StaxAdjacentRoomParser();
        StaxItemParser readItems = new StaxItemParser();

        HashMap<String, Room> rooms = readRooms.readRoomsXML("./resources/newGame/rooms.xml");
        readAdjacentRooms.readAdjacentRoomsXML("./resources/newGame/adjacentRooms.xml",rooms);
        readItems.readItemsXML("./resources/newGame/items.xml",rooms);
        for (Map.Entry entry : rooms.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        //System.out.println(rooms.get("Foyer").getNeighboringRooms());
        rooms.get("Outside").getItems().values().forEach( item -> System.out.println(item.getName()));


    }
}
