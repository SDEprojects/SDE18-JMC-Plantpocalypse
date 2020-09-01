package com.plantpocalypse;

import java.util.List;

public abstract class Item {
    private String name;
    private String Description;
    private boolean isEdible;

    public Item(String name){
        this.name = name;
    }

    public abstract void use();


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return Description; }
    public void setDescription(String description) { Description = description; }


//    public String showDescription(String name){
//    }

}

