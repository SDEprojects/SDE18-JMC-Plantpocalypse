package com.plantpocalypse;

public class ActionFoyer implements Action {
    @Override
    public void entryEvent(Room room) {
        System.out.println("You look in awe at the multitude of exotic plants in the grand entry of the mansion.");
    }
}
