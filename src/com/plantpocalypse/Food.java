package com.plantpocalypse;

public class Food extends Item {

    /* CONSTRUCTORS */
    public Food(String name) {
        super(name);
    }

    /* ABSTRACT METHODS */
    @Override
    public void use() {
        System.out.println(getName() + " used");
    }

    /* BUSINESS METHODS */

    /* GETTERS AND SETTERS */
}
