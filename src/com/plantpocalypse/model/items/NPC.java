package com.plantpocalypse.model.items;

import java.io.Serializable;

public class NPC implements Serializable {
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NPC(String name) {
        this.name = name;

    }

    public String respond(){
        return "some words";

    }




}

