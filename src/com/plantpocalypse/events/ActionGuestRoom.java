package com.plantpocalypse.events;

import com.plantpocalypse.PlantMonster;
import com.plantpocalypse.Player;
import com.plantpocalypse.Room;

public class ActionGuestRoom implements Action{
    @Override
    public void entryEvent(Player player, Room room) {
        PlantMonster monster = new PlantMonster("Poison Ivy", 2);
        monster.attackPlayer(player);
    }

}
