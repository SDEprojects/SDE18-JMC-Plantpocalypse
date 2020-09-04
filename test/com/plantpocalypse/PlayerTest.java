package com.plantpocalypse;

import com.plantpocalypse.gameclient.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;
    HashMap<String, Room> mansion;

    @Before
    public void setUp() throws Exception {
        Game.GAME_INSTANCE.loadAssets();
        player = Game.GAME_INSTANCE.getPlayer();
        mansion = Game.GAME_INSTANCE.getMansion();
    }

    @Test
    public void testMoveToConnectedRoom_Success() {
        Room foyer = mansion.get("Foyer");
        foyer.toggleLock();
        player.move(foyer);

        assertSame(player.getCurrentRoom(), foyer);
    }

    // Logic for not entering locked room is in GameDirector
    // Maybe move logic to player.move()?
    @Test
    public void testLockedDoor_Success() {
        Room foyer = mansion.get("Foyer");
        player.move(foyer);

        assertSame(player.getCurrentRoom(), mansion.get("Outside"));
    }

    @Test
    public void testPickUpItem_Success() {
        player.pickUpItem("brass key");

        assertEquals(player.getInventory().get(0).getName(), "brass key");
    }

    @Test
    public void testPickUpItem_itemDoesNotExist_Success() {


        assertEquals(player.getInventory().size(), 0);
    }
}