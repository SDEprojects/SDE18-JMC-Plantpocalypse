package com.plantpocalypse.model;

import com.plantpocalypse.model.items.Food;
import com.plantpocalypse.model.items.Item;
import com.plantpocalypse.model.items.Journal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomTest {
    Room testRoom;
    Item map;
    Food penguin = new Food();



    @BeforeEach
    void setUp() {
        testRoom = new Room();
        map = new Journal();
        testRoom.addItem("map", map);


    }

    @Test
    void test_roomItemsListAddItemsShouldNotBeEmpty() {
        Boolean actual = testRoom.getItems().isEmpty();
        Boolean expected = false;
        assertEquals(expected, actual);
    }


    @Test
    void test_getItemOutOfRoom() {
        testRoom.getItem("map");
        System.out.println(testRoom.getItem("map"));
    }

    @Test
    void test_isThisDoorLocked() {
        Boolean actual = testRoom.isLocked();
        Boolean expected = false;
        assertEquals(expected,actual);
    }



}