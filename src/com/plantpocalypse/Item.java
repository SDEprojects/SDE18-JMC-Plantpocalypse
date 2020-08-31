package com.plantpocalypse;

import java.util.List;

public abstract class Item {




    private String name;
    private String Description;

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



//    public String showDescription(String name){
//    }

}

