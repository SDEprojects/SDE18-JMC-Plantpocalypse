package com.plantpocalypse;

public class ActionGreenHouseOne implements Action {
    @Override
    public void entryEvent(Player player, Room room) {
        PlantMonster monster = new PlantMonster("cactus", 5);
        monster.attackPlayer(player);
    }
}
