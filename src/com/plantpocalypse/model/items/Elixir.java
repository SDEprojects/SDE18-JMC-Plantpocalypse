package com.plantpocalypse.model.items;

public class Elixir extends Item {

    /* CONSTRUCTORS */
    public Elixir() {}

    public Elixir(String name) {
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
