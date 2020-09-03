package com.plantpocalypse;

public class ActionGuestRoom implements Action{
    @Override
    public void entryEvent(Player player, Room room) {
        PlantMonster monster = new PlantMonster("Poison Ivy", 2);
        monster.attackPlayer(player);
    }

}
