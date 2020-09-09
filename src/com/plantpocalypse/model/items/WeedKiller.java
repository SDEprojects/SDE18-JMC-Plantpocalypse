package com.plantpocalypse.model.items;

public class WeedKiller extends Item {
    /* CONSTRUCTORS */
    public WeedKiller() {}
    public WeedKiller(String name){
        super(name);
    }

    /* ABSTRACT METHODS */
    @Override
    public void use() {
        System.out.println(getName() + " used.");
    }

    /* BUSINESS METHODS */

    /* GETTERS AND SETTERS */
}
