package com.plantpocalypse.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class GameTest {
    Game game = Game.GAME_INSTANCE;
    @Before
    public void setup() {
        game.setAdjacentRoomsPath("./test/resources/newGame/testAdjacentRooms.xml");
        game.setItemsPath("./test/resources/newGame/testItems.xml");
        game.setMonstersPath("./test/resources/newGame/testMonsters.xml");
        game.setRoomsPath("./test/resources/newGame/testRooms.xml");
    }

    @Test
    public void test_loadAssets_shouldLoadCorrectNumberOfRooms_intoMansionHashMap(){
        game.loadAssets();
        int expected = 5;
        int actual = game.getMansion().size();
        assertEquals(expected, actual);
    }

    @Test
    public void test_loadAssets_shouldLoadCorrectRooms_intoMansionHashMap(){
        game.loadAssets();
        HashMap mansion = game.getMansion();
        assertNotNull(mansion.get("Outside"));
        assertNotNull(mansion.get("Foyer"));
        assertNotNull(mansion.get("Living Room"));
        assertNotNull(mansion.get("Library"));
        assertNotNull(mansion.get("Hidden Office"));
    }

    @Test
    public void test_loadAssets() {
        game.loadAssets();
    }

    @Test
    public void test_checkGameOver_shouldReturnTrue_whenPlayerIsNotAlive_whenPlayerHasWonGame_whenPlayerCompletedTutorial() {
        game.loadAssets();
        game.setPlayer("Hidden Office");
        Player player = game.getPlayer();
        assertFalse(game.checkGameOver());
        player.setAlive(false);
        assertTrue(game.checkGameOver());

        game.setPlayer("Hidden Office");
        player = game.getPlayer();
        assertFalse(game.checkGameOver());
        player.setWon(true);
        assertTrue(game.checkGameOver());

        game.setPlayer("Hidden Office");
        player = game.getPlayer();
        assertFalse(game.checkGameOver());
        player.setTutorialComplete(true);
        assertTrue(game.checkGameOver());

    }

    @Test
    public void test_checkGameOver_shouldReturnTrue_whenPlayerMoves_equals40(){
        game.loadAssets();
        game.setPlayer("Hidden Office");
        Player player = game.getPlayer();
        player.setMovesMade(40);
        assertTrue(game.checkGameOver());
    }

    @Test
    public void test_checkGameOver_shouldReturnTrue_whenPlayerMoves_isGreaterThan40(){
        game.loadAssets();
        game.setPlayer("Hidden Office");
        Player player = game.getPlayer();
        player.setMovesMade(41);
        assertTrue(game.checkGameOver());
    }

    @Test
    public void test_checkGameOver_shouldReturnFalse_whenPlayerMoves_isLessThan40(){
        game.loadAssets();
        game.setPlayer("Hidden Office");
        Player player = game.getPlayer();
        player.setMovesMade(0);
        assertFalse(game.checkGameOver());
        player.setMovesMade(39);
        assertFalse(game.checkGameOver());
    }

}