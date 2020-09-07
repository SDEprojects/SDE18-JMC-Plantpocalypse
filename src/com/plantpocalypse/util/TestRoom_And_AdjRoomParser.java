package com.plantpocalypse.util;

import com.plantpocalypse.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRoom_And_AdjRoomParser {
    public static void main(String[] args) {
        StaxRoomParser readRooms = new StaxRoomParser();
        StaxAdjacentRoomParser readAdjacentRooms = new StaxAdjacentRoomParser();

        HashMap<String, Room> rooms = readRooms.readRoomsXML("./resources/rooms.xml");
        readAdjacentRooms.readAdjacentRoomsXML("./resources/adjacentRooms.xml",rooms);

//        for (Map.Entry entry : rooms.entrySet()) {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }

        System.out.println(rooms.get("Foyer").getNeighboringRooms().get("south"));


    }
}
