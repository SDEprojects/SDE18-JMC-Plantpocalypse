package com.plantpocalypse.model.items;

public class NPC {
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

