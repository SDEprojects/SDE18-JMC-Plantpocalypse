package com.plantpocalypse.model.items;

public class TutorialOrb extends Item{
    public TutorialOrb() {}

    public TutorialOrb(String name) {
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
