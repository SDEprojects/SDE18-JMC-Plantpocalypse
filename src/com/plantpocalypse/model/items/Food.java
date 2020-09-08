package com.plantpocalypse.model.items;

public class Food extends Item {
    private int healthRestored;

    /* CONSTRUCTORS */
    public Food() {}

    public Food(String name, int healthRestored) {
        super(name);
        setHealthRestored(healthRestored);
    }

    /* ABSTRACT METHODS */
    @Override
    public void use() {
        System.out.println(getName() + " used");
    }

    /* BUSINESS METHODS */

    /* GETTERS AND SETTERS */
    public int getHealthRestored() {
        return healthRestored;
    }

    public void setHealthRestored(int healthRestored) {
        this.healthRestored = healthRestored;
    }
}
