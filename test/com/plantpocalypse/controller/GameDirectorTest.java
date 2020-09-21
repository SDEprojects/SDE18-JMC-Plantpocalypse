package com.plantpocalypse.controller;

import com.plantpocalypse.model.PlantMonster;
import com.plantpocalypse.model.Player;
import com.plantpocalypse.model.Room;
import com.plantpocalypse.model.items.FloorPlan;
import com.plantpocalypse.model.items.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameDirectorTest {
    Player player;
    Room room;

    @BeforeEach
    void setUp() {
        room = new Room("room");
        room.addItem("rambutan", new Food("rambutan"));
        player = new Player(room);
        player.pickUpItem("rambutan");
    }

    @Test
    void testUse() {
        assertEquals(true,  GameDirector.use("nothing", player).contains("You do not have that item"));
        assertEquals(true,  GameDirector.use("rambutan", player).contains("The best way to Use Food"));
    }

    @Test
    void testOpen() {
        assertEquals(true,  GameDirector.open("rambutan", player).contains("You have nothing to open!"));
        room.addItem("floor plan", new FloorPlan("floor plan"));
        player.pickUpItem("floor plan");
        assertEquals(true,  GameDirector.open("floor plan", player).contains("You opened the floor plan"));
    }

    @Test
    void testPickup() {
        room.addItem("apple", new Food("apple"));
        assertEquals("Picked up a apple",  GameDirector.pickup("apple", player));
    }

    @Test
    void testLook() {
        assertEquals("\nThis is the room\nFrom here, you can G. ",  GameDirector.look(player));
        room.setMonster(new PlantMonster("monster"));
        assertEquals(true,  GameDirector.look(player).contains("You should run away!!"));
    }

    @Test
    void testExamine() {
        assertEquals("You do not have that item!", GameDirector.examine("book",player));
        assertEquals("You examine the rambutan\nThis is a rambutan", GameDirector.examine("rambutan",player));
    }

    @Test
    void testEat() {
        assertEquals("You do not have one of those!", GameDirector.eat("book",player));
        assertEquals(true, GameDirector.eat("rambutan",player).contains("Omnomnom!"));
    }

    @Test
    void testInventory() {
        assertEquals("Player Inventory:\n1. rambutan\n", GameDirector.inventory(player));
        assertEquals("There are no items in your inventory.", GameDirector.inventory(new Player(room)));
    }

    @Test
    void testHelp() {
        assertEquals(false, GameDirector.help().equals(null));
    }
}