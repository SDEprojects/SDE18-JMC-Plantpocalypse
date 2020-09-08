package com.plantpocalypse.items;

import com.plantpocalypse.Room;

public class FloorPlan extends Item{

    /* CONSTRUCTORS */
    public FloorPlan(String name){
        super(name);
    }

    /* ABSTRACT METHODS */
    @Override
    public void use() {
        System.out.println(getName() + " opened");
    }

    /* BUSINESS METHODS */

    /* GETTERS AND SETTERS */

}
