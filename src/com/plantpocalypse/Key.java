package com.plantpocalypse;

public class Key extends Item{

    /* CONSTRUCTORS */
    public Key(String name){
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
