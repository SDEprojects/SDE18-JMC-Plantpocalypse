package com.plantpocalypse;

public abstract class Item {
    private String name;
    private String Description;

    /* CONSTRUCTORS */
    public Item(String name){
        setName(name);
        setDescription("This is a " + name);
    }

    /* ABSTRACT METHODS */
    public abstract void use();

    /* GETTERS AND SETTERS */
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

